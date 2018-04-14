package a21260825.dgomes.cubi1718_tp_p3.activities;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import a21260825.dgomes.cubi1718_tp_p3.R;
import a21260825.dgomes.cubi1718_tp_p3.utils.Recolha;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;
import a21260825.dgomes.cubi1718_tp_p3.utils.Permissoes;

public class MainActivity extends AppCompatActivity {

    private Permissoes permissoes;
    private Recolha recolha;
    private Ficheiro ficheiro;
    private Button btRecolha;
    private TextView tvLog,tvXyz;
    private boolean recolhaIniciada = false;
    private ScrollView mScrollView;
    private LinearLayout sensorTvs;

    public LinearLayout getSensorTvs() {
        return sensorTvs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLog = (TextView) findViewById(R.id.tvLog);
        mScrollView = (ScrollView) findViewById(R.id.SCROLLER_ID);
        sensorTvs = (LinearLayout) findViewById(R.id.sensorTvs);
        //tvLog.setMovementMethod(new ScrollingMovementMethod());
        tvLog.setText("Hello!\n");



        ficheiro = Ficheiro.getInstance();
        permissoes = Permissoes.getInstance(this,tvLog);
        recolha = Recolha.getInstance(this, ficheiro);


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
                if (recolhaIniciada) {
                    tvLog.append("Recolha terminada\n");
                    recolhaIniciada = false;
                    btRecolha.setText("Iniciar Recolha");
                    recolha.terminar();
                } else {
                    tvLog.append("Recolha iniciada\n");
                    recolhaIniciada = true;
                    btRecolha.setText("Terminar Recolha");
                    recolha.iniciar();
                }
                mScrollView.fullScroll(View.FOCUS_DOWN);

            }
        });

    }

    public TextView getTvXyz() {
        return tvXyz;
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
