package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.models.Registo;

/**
 * Created by diogo on 14-04-2018.
 */

public class Ficheiro {

    private static Ficheiro instance;
    private MainActivity activity;
    private String path;
    private boolean pastaCriada = false;
    private boolean saving = false;
    private File pasta;
    private File ficheiro;
    private BufferedWriter bw;
    private PrintWriter saveRecolha;
    private boolean transferido = true;
    private File[] ficheirosTransferencia;

    protected Ficheiro(MainActivity activity) {
        this.activity = activity;
        path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + Config.PASTA_FICHEIRO_LOCAL;

    }

    public static Ficheiro getInstance(MainActivity activity){
        if(instance == null) {
            instance = new Ficheiro(activity);
        }
        return instance;
    }

    public File getFicheiro() {
        return ficheiro;
    }

    private String timestamp(){
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }
    public boolean startSave(){
        if (pastaCriada && transferido) {
            ficheiro = new File(pasta, Config.FICHEIRO+"_" + timestamp() +Config.EXTENCAO);
            if (saveFicheiro(ficheiro)){
                addLog("Ficheiro a registar\n");
                return true;
            }else {
                addLog("Ficheiro problema\n");
                return false;
            }
        }else{
            addLog("Pasta nao criada ou ficheiro nao transferido\n");
            return false;
        }
    }

    public void setTransferido(boolean transferido) {
        this.transferido = transferido;
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
           
            try {
                bw.close();
                saving = false;
                addLog("Registo em ficheiro interrompido\n");
            } catch (IOException e) {
                e.printStackTrace();
                addLog("Problema na interrupcao de registo em ficheiro\n");
            }
            return true;
        }else {
            return false;
        }
    }
    public boolean criarPasta() {
        pasta = new File(path);
        if (!pasta.exists() && !pasta.mkdirs()) {
            addLog("Erro a criar pasta\n");
            pastaCriada = false;
            return false;
        } else {
            addLog(("Ficheiro: " + path + "\n"));
            pastaCriada = true;
            return true;
        }
    }

    public String getPath() {
        return "Ficheiro em: " + path + "\n";
    }

    public int contar() {
        File[] list = pasta.listFiles();
        int count = 0;
        for (File f: list){
            if (f.isFile())
                count++;
        }
        return count;
    }

    public File getPasta() {
        return pasta;
    }

    public void addLog(String log) {
        activity.addLog(log);
    }

    public void arquivarFicheiro(){
        if (transferido){
            moveFile(pasta.getPath()+"/",ficheiro.getName(),path+Config.PASTA_ARQUIVO_LOCAL);
            
        }
    }
    private void moveFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File (outputPath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath + inputFile).delete();

            addLog("Ficheiro arquivado.\n");
        }

        catch (FileNotFoundException fnfe1) {
            addLog(fnfe1.getMessage()+"\n");
        }
        catch (Exception e) {
            addLog(e.getMessage()+"\n");
        }

    }

    public void contarFicheirosNovos(){
        activity.contarFicheirosNovos();
    }

    public void setFicheiro(File ficheiro) {
        this.ficheiro = ficheiro;
    }

    public void addFicheirosTransferencia(File[] list) {
        this.ficheirosTransferencia=list;
    }

    public File[] getFicheirosTransferencia() {
        return ficheirosTransferencia;
    }
}
