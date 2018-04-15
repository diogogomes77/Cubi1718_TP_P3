
package a21260825.dgomes.cubi1718_tp_p3.activities;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import a21260825.dgomes.cubi1718_tp_p3.R;
import a21260825.dgomes.cubi1718_tp_p3.utils.Recolha;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;
import a21260825.dgomes.cubi1718_tp_p3.utils.Permissoes;
import a21260825.dgomes.cubi1718_tp_p3.utils.Transferencia;

public class MainActivity extends AppCompatActivity {

    private Permissoes permissoes;
    private Recolha recolha;
    private Ficheiro ficheiro;
    private Button btRecolha, btTransferirDados;
    private TextView tvLog,tvFicheiro;
    private boolean recolhaIniciada = false;
    private ScrollView mScrollView;
    private LinearLayout sensorTvs;
    private RadioGroup atividades;
    private String atividade;
    private Transferencia transferencia;
    public LinearLayout getSensorTvs() {
        return sensorTvs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLog = (TextView) findViewById(R.id.tvLog);
        tvFicheiro = (TextView) findViewById(R.id.tvFicheiro);
        mScrollView = (ScrollView) findViewById(R.id.SCROLLER_ID);
        sensorTvs = (LinearLayout) findViewById(R.id.sensorTvs);
        //tvLog.setMovementMethod(new ScrollingMovementMethod());
        tvLog.setText("Hello!\n");



        ficheiro = Ficheiro.getInstance(tvLog);
        permissoes = Permissoes.getInstance(this,tvLog);
        recolha = Recolha.getInstance(this, ficheiro);

        atividades = (RadioGroup) findViewById(R.id.atividades);

        atividades.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ;
                switch(checkedId){
                    case R.id.rbAndar:
                        atividade = Config.ANDAR;
                        break;
                    case R.id.rbCorrer:
                        atividade = Config.CORRER;
                        break;
                    case R.id.rbDescer:
                        atividade = Config.DESCER;
                        break;
                    case R.id.rbSubir:
                        atividade = Config.SUBIR;
                        break;
                    case R.id.rbOutra:
                        atividade = Config.OUTRA;
                        break;
                }
                recolha.getRegisto().setAtividade(atividade);
            }
        });

        btRecolha = (Button) findViewById(R.id.btRecolha);

        if (permissoes.temPermissoes()){
            tvLog.append("temPermissoes = true\n");
            ativarBotaoRecolha();
        } else {
            tvLog.append("temPermissoes = false\n");
            btRecolha.setEnabled(false);
        }

        btRecolha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (atividade==null){
                    Toast.makeText(MainActivity.this, "Escolha uma atividade!", Toast.LENGTH_LONG).show();
                }else {
                    if (recolhaIniciada) {
                        terminarRecolha();
                    } else {
                        iniciarRecolha();
                    }
                    mScrollView.fullScroll(View.FOCUS_DOWN);
                }


            }
        });

        btTransferirDados = (Button) findViewById(R.id.btTransferirDados);
        btTransferirDados.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recolhaIniciada) {
                    terminarRecolha();
                }
                //transferencia = Transferencia.getInstance(ficheiro.getFicheiro());
                tvLog.append("Iniciada a transferencia de ficheiro\n");
                new Transferencia(ficheiro).execute();

            }
        });

    }
    private void iniciarRecolha(){
        tvLog.append("Recolha iniciada\n");
        recolhaIniciada = true;
        btRecolha.setText("Terminar Recolha");
        recolha.iniciar();
    }
    private void terminarRecolha(){
        tvLog.append("Recolha terminada\n");
        recolhaIniciada = false;
        btRecolha.setText("Iniciar Recolha");
        recolha.terminar();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Config.MY_PERMMISSIONS_ALL: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    tvLog.append("temPermissoes = true\n");
                    ativarBotaoRecolha();
                } else {
                    tvLog.append("temPermissoes = false\n");
                }
                mScrollView.fullScroll(View.FOCUS_DOWN);
                break;
            }

        }
    }
    private void ativarBotaoRecolha(){
        if(ficheiro.criarPasta()){
            tvLog.append("criarPasta = true\n");
            recolha.preparar();
            btRecolha.setEnabled(true);
            tvFicheiro.setText(ficheiro.getPath());
        }else{
            tvLog.append("criarPasta = false\n");
            btRecolha.setEnabled(false);
        }
        mScrollView.fullScroll(View.FOCUS_DOWN);
    }

    public void addLog(String s){
        tvLog.append(s);
    }
}
