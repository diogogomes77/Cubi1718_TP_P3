package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;

/**
 * Created by diogo on 14-04-2018.
 */

public class Ficheiro {

    private static Ficheiro instance;
    private TextView tv;
    private String path;
    private boolean pastaCriada = false;
    private boolean saving = false;
    private File pasta;
    private File ficheiro;
    private BufferedWriter bw;
    private PrintWriter saveRecolha;

    protected Ficheiro(TextView tv) {
        this.tv = tv;
        path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + Config.PASTA_FICHEIRO;
    }

    public static Ficheiro getInstance(TextView tv){
        if(instance == null) {
            instance = new Ficheiro(tv);
        }
        return instance;
    }

    private String timestamp(){
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }
    public boolean startSave(){
        if (pastaCriada) {
            ficheiro = new File(pasta, Config.FICHEIRO+"_" + timestamp() +Config.EXTENCAO);
            if (saveFicheiro(ficheiro)){
                tv.append("Ficheiro a registar\n");
                return true;
            }else {
                tv.append("Ficheiro problema\n");
                return false;
            }
        }else{
            tv.append("Pasta nao criada ainda\n");
            return false;
        }
    }
    public boolean saveValores(Registo registo){
        if (saving){
            /*if (ficheiro.length() == 0) {
                saveRecolha.println(registo.header());
            }*/
            saveRecolha.println(registo.toString());
            return true;
        }
        return false;
    }

    private boolean saveFicheiro(File ficheiro){
        try {
            FileWriter fw = new FileWriter(ficheiro, true);
            saving = true;
            bw = new BufferedWriter(fw);
            saveRecolha = new PrintWriter(bw);



        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    public boolean stopSaving(){
        if(saving){
            saving = false;
            try {
                // bw.flush();
                bw.close();
                saving = false;
                tv.append("Registo em ficheiro interrompido\n");
            } catch (IOException e) {
                e.printStackTrace();
                tv.append("Problema na interrupcao de registo em ficheiro\n");
            }
            return true;
        }else {
            return false;
        }
    }
    public boolean criarPasta() {
        pasta = new File(path);
        if (!pasta.exists() && !pasta.mkdirs()) {
            tv.append("Erro a criar pasta\n");
            pastaCriada = false;
            return false;
        } else {
            tv.append(("Ficheiro: " + path + "\n"));
            pastaCriada = true;
            return true;
        }
    }

    public String getPath() {
        return "Ficheiro: " + path + "\n";
    }
}
