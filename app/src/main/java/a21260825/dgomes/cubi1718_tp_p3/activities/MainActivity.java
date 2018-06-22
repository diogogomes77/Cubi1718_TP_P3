
package a21260825.dgomes.cubi1718_tp_p3.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;

import a21260825.dgomes.cubi1718_tp_p3.R;
import a21260825.dgomes.cubi1718_tp_p3.analise.Analyser;
import a21260825.dgomes.cubi1718_tp_p3.utils.Recolha;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;
import a21260825.dgomes.cubi1718_tp_p3.utils.Permissoes;
import a21260825.dgomes.cubi1718_tp_p3.utils.Transferencia;
import arsystem.ARSystem;

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
    private String modo;
    private Transferencia transferencia;
    private int ficheirosNovos=0;
    private Chronometer simpleChronometer;
    private long timeWhenStopped = 0;
    private boolean recolhaPausada = false;
    private CheckBox autoMode;


    private RadioButton rbAct1,rbAct2,rbAct3,rbAct4,rbAct5,rbAct6,rbAct7,rbAct8,rbAct9,rbAct10;
    private RadioGroup mode;
    private RadioButton modeLearn,modeAuto,modeSave;

    public LinearLayout getSensorTvs() {
        return sensorTvs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        mode = (RadioGroup) findViewById(R.id.mode);
        btRecolha = (Button) findViewById(R.id.btRecolha);
        ttRecolhaPausa = (ToggleButton) findViewById(R.id.ttRecolhaPausa);
        btTransferirDados = (Button) findViewById(R.id.btTransferirDados);
        btTransferirDados.setEnabled(false);
        if (permissoes.temPermissoes()){
            addLog("temPermissoes = true\n");
            ativarBotaoRecolha();
            contarFicheirosNovos();
        } else {
            addLog("temPermissoes = false\n");
            btRecolha.setEnabled(false);
        }


        ttRecolhaPausa.setEnabled(false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            if (ficheirosNovos>0){
                btTransferirDados.setEnabled(true);
            }
        }

        setActividadesLabels();
        setButtonListenners();


    }
    private void setActividadesLabels(){
        rbAct1 = findViewById(R.id.rbAct1);
        rbAct1.setText(Config.ACT1);
        rbAct2 = findViewById(R.id.rbAct2);
        rbAct2.setText(Config.ACT2);
        rbAct3 = findViewById(R.id.rbAct3);
        rbAct3.setText(Config.ACT3);
        rbAct4 = findViewById(R.id.rbAct4);
        rbAct4.setText(Config.ACT4);
        rbAct5 = findViewById(R.id.rbAct5);
        rbAct5.setText(Config.ACT5);
        rbAct6 = findViewById(R.id.rbAct6);
        rbAct6.setText(Config.ACT6);
        rbAct7 = findViewById(R.id.rbAct7);
        rbAct7.setText(Config.ACT7);
        rbAct8 = findViewById(R.id.rbAct8);
        rbAct8.setText(Config.ACT8);
        rbAct9 = findViewById(R.id.rbAct9);
        rbAct9.setText(Config.ACT9);
        rbAct10 = findViewById(R.id.rbAct10);
        rbAct10.setText(Config.ACT10);

        modeLearn = findViewById(R.id.modeLearn);
        modeLearn.setText(Config.TRAIN);
        modeAuto = findViewById(R.id.modeAuto);
        modeAuto.setText(Config.AUTO);
        modeSave = findViewById(R.id.modeSave);
        modeSave.setText(Config.SAVE);
    }
    private void setButtonListenners(){
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
        mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                modo = escolherModo(checkedId);
            }
        });
        btRecolha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (atividade==null){
                    Toast.makeText(MainActivity.this, "Escolha uma atividade!", Toast.LENGTH_LONG).show();
                }else if (modo==null){
                    Toast.makeText(MainActivity.this, "Escolha um modo!", Toast.LENGTH_LONG).show();
                } else {
                    if (recolhaIniciada) {
                        terminarRecolha();
                    } else {
                        iniciarRecolha();
                    }
                    mScrollView.fullScroll(View.FOCUS_DOWN);
                }
            }
        });
        ttRecolhaPausa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pausarRecolha();
                } else {
                    continuarRecolha();
                }
            }
        });
        btTransferirDados.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recolhaIniciada) {
                    terminarRecolha();
                }

                transferirFicheirosNovos();

                btTransferirDados.setEnabled(false);

            }
        });
    }
    private void transferirFicheirosNovos(){
        File[] list = ficheiro.getPasta().listFiles();
        ficheiro.addFicheirosTransferencia(list);
        String log = "Transferir ficheiros" + list.length + "\n";
        addLog(log);
        new Transferencia(ficheiro).execute();

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
    private void setMode(){
        mode.setOnCheckedChangeListener(null);
        mode.clearCheck();
        mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                modo = escolherModo(checkedId);
                recolha.getRegisto().setAtividade(atividade);
            }
        });
    }
    private String escolherModo(int checkedId) {
        RadioButton rb = (RadioButton) findViewById(checkedId);
        String modo = rb.getText().toString();
        addLog("Modo: " + modo +"\n");
        return modo;
    }
    private String escolherAtividade(int checkedId) {
        RadioButton rb = (RadioButton) findViewById(checkedId);
        String atividade = rb.getText().toString();
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
        recolha.iniciar(modo);
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start();
        contarFicheirosNovos();

        // iniciar treino

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
        tvFilesNovos.setText("Ficheiros novos: " + ficheirosNovos);
        btTransferirDados.setEnabled(true);
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
