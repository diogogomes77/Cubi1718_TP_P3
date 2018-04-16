
package a21260825.dgomes.cubi1718_tp_p3.activities;

import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
    private ToggleButton ttRecolhaPausa;
    private TextView tvLog,tvFicheiro,tvFilesNovos,tvTempo;
    private boolean recolhaIniciada = false;
    private ScrollView mScrollView;
    private LinearLayout sensorTvs;
    private RadioGroup atividades1,atividades2;
    private String atividade;
    private Transferencia transferencia;
    private int ficheirosNovos;
    private Chronometer simpleChronometer;
    private long timeWhenStopped = 0;
    private boolean recolhaPausada = false;
    private CheckBox autoMode;

    public LinearLayout getSensorTvs() {
        return sensorTvs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoMode = (CheckBox) findViewById(R.id.cbAutomatico);
        autoMode.setEnabled(false);
        tvLog = (TextView) findViewById(R.id.tvLog);
        tvFicheiro = (TextView) findViewById(R.id.tvFicheiro);

        tvFilesNovos = (TextView) findViewById(R.id.tvFilesNovos);
        mScrollView = (ScrollView) findViewById(R.id.SCROLLER_ID);
        sensorTvs = (LinearLayout) findViewById(R.id.sensorTvs);
        //tvLog.setMovementMethod(new ScrollingMovementMethod());
        addLog("Hello!\n");

        ficheiro = Ficheiro.getInstance(this);
        permissoes = Permissoes.getInstance(this,tvLog);
        recolha = Recolha.getInstance(this, ficheiro);

        simpleChronometer = (Chronometer) findViewById(R.id.chrono);
        simpleChronometer.setFormat("Crono: %s");
        atividades1 = (RadioGroup) findViewById(R.id.atividades1);
        atividades2 = (RadioGroup) findViewById(R.id.atividades2);

        atividades1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setAtividades2();
                atividade = escolherAtividade(checkedId);
                recolha.getRegisto().setAtividade(atividade);
            }
        });

        atividades2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setAtividades1();
                atividade = escolherAtividade(checkedId);
                recolha.getRegisto().setAtividade(atividade);
            }
        });

        btRecolha = (Button) findViewById(R.id.btRecolha);

        if (permissoes.temPermissoes()){
            addLog("temPermissoes = true\n");
            ativarBotaoRecolha();
            contarFicheirosNovos();
        } else {
            addLog("temPermissoes = false\n");
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
        ttRecolhaPausa = (ToggleButton) findViewById(R.id.ttRecolhaPausa);
        ttRecolhaPausa.setEnabled(false);
        ttRecolhaPausa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pausarRecolha();
                } else {
                    continuarRecolha();
                }
            }
        });

        btTransferirDados = (Button) findViewById(R.id.btTransferirDados);
        btTransferirDados.setEnabled(false);
        btTransferirDados.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recolhaIniciada) {
                    terminarRecolha();
                }
                addLog("Iniciada a transferencia de ficheiro\n");
                new Transferencia(ficheiro).execute();

                btTransferirDados.setEnabled(false);

            }
        });



    }
    private void setAtividades1(){
        atividades1.setOnCheckedChangeListener(null);
        atividades1.clearCheck();
        atividades1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setAtividades2();
                atividade = escolherAtividade(checkedId);
                recolha.getRegisto().setAtividade(atividade);
            }
        });
    }
    private void setAtividades2(){
        atividades2.setOnCheckedChangeListener(null);
        atividades2.clearCheck();
        atividades2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setAtividades1();
                atividade = escolherAtividade(checkedId);
                recolha.getRegisto().setAtividade(atividade);
            }
        });
    }
    private String escolherAtividade(int checkedId) {
        String atividade;
        switch(checkedId){
            case R.id.rbAndar:
                atividade = Config.WALKING;
                break;
            case R.id.rbCorrer:
                atividade = Config.RUNNING;
                break;
            case R.id.rbDescer:
                atividade = Config.GO_DOWNSTAIRS;
                break;
            case R.id.rbSubir:
                atividade = Config.GO_UPSTAIRS;
                break;
            case R.id.rbConduzir:
                atividade = Config.DRIVING;
                break;
            case R.id.rbPatinarEstrada:
                atividade = Config.SKATING_FLAT;
                break;
            case R.id.rbPatinarPark:
                atividade = Config.SKATING_PARK;
                break;
            case R.id.rbSaltar:
                atividade = Config.JUMPING;
                break;
            case R.id.rbAgachar:
                atividade = Config.SQUATTING;
                break;
            case R.id.rbSentar:
                atividade = Config.SITTING;
                break;
            default:
                atividade = null;
                break;
        }

        addLog("Atividade: " + atividade+"\n");
        return atividade;
    }

    private void iniciarRecolha(){
        addLog("Recolha iniciada\n");
        recolhaIniciada = true;
        recolhaPausada = false;
        btRecolha.setText("Stop");
        ttRecolhaPausa.setEnabled(true);
        btTransferirDados.setEnabled(true);
        recolha.iniciar();
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start();
        contarFicheirosNovos();
    }

    private void terminarRecolha(){
        addLog("Recolha terminada\n");
        ttRecolhaPausa.setChecked(false);
        ttRecolhaPausa.setEnabled(false);
        recolhaIniciada = false;
        recolhaPausada = false;
        btRecolha.setText("Start");
        recolha.terminar();
        simpleChronometer.stop();
        contarFicheirosNovos();
    }

    private void pausarRecolha(){
            recolhaPausada = true;
            addLog("Recolha pausada\n");
            recolha.pausar();
            timeWhenStopped = simpleChronometer.getBase() - SystemClock.elapsedRealtime();
            simpleChronometer.stop();
    }

    private void continuarRecolha(){
            recolhaPausada = false;
            addLog("Recolha continuada\n");
            recolha.continuar();
            simpleChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            simpleChronometer.start();
    }

    public void contarFicheirosNovos(){
        ficheirosNovos=ficheiro.contar();
        tvFilesNovos.setText("Ficheiros novos: " + ficheirosNovos);;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Config.MY_PERMMISSIONS_ALL: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addLog("temPermissoes = true\n");
                    ativarBotaoRecolha();
                    contarFicheirosNovos();
                } else {
                    addLog("temPermissoes = false\n");
                }
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
            addLog("criarPasta = false\n");
            btRecolha.setEnabled(false);
        }
    }

    public void addLog(String s){
        tvLog.append(s);
        mScrollView.fullScroll(View.FOCUS_DOWN);
    }
}
