package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Created by diogo on 15-04-2018.
 */

public class Transferencia extends AsyncTask<Void, Integer, String> {

    private static Transferencia instance;
    private File file;
    private Ficheiro ficheiro;
    private boolean transferenciaConcluida;
    private File[] ficheirosTransferencia;

    public Transferencia(Ficheiro ficheiro) {
        this.ficheiro=ficheiro;
        this.file = ficheiro.getFicheiro();
        this.ficheirosTransferencia = ficheiro.getFicheirosTransferencia();
        /*File[] list = ficheiro.getPasta().listFiles();
        int count = 0;
        for (File f: list){
            count++;
        }
        return count;
*/
    }

    public static Transferencia getInstance(Ficheiro ficheiro){
        if (instance==null){
            instance = new Transferencia(ficheiro);
        }
        return instance;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d("Trasnferir","doInBackground ");
        if (ficheirosTransferencia.length>0){
            Log.d("Trasnferir","doInBackground " + ficheirosTransferencia.length);
                try {
                    String host = Config.HOST;
                    String user = Config.USER;
                    String passwd = Config.PASSWORD;

                    JSch ssh = new JSch();
                    Session session = ssh.getSession(user, host, 22);
                    // Remember that this is just for testing and we need a quick access, you can add an identity and known_hosts file to prevent
                    // Man In the Middle attacks
                    java.util.Properties config = new java.util.Properties();
                    config.put("StrictHostKeyChecking", "no");
                    session.setConfig(config);
                    session.setPassword(passwd);

                    session.connect();
                    Channel channel = session.openChannel("sftp");
                    channel.connect();

                    ChannelSftp sftp = (ChannelSftp) channel;

                    // sftp.cd("data");
                    Log.d("Trasnferir","FOR");
                    for (File f:ficheirosTransferencia) {
                        if (f.isFile()){
                            ficheiro.setFicheiro(f);
                            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyhhmmss");
                            String localFile = f.getAbsolutePath();
                            //String remoteFile = Config.PASTA_REMOTA.concat("/").concat(Config.FICHEIRO).concat("_").concat(formatter.format(new Date().getTime())).concat(Config.EXTENCAO);
                            String remoteFile = Config.PASTA_REMOTA.concat("/").concat(f.getName());

                            if (localFile!=null){
                                Log.d("Trasnferir", localFile);
                                sftp.put(localFile,remoteFile);
                                SystemClock.sleep(2000);
                            }
                        }

                    }
                    channel.disconnect();
                    session.disconnect();
                    transferenciaConcluida = true;
                    return Config.TRANSFERIDO;
                } catch (JSchException e) {

                    e.printStackTrace();
                    Log.d("JSchException", e.getMessage().toString());
                    //ficheiro.addLog(e.getMessage().toString());
                    transferenciaConcluida = false;
                    return Config.NAO_TRANSFERIDO;
                } catch (SftpException e) {

                    Log.d("SftpException", e.getMessage().toString());
                    e.printStackTrace();
                    //ficheiro.addLog(e.getMessage().toString());
                    transferenciaConcluida = false;
                    return Config.NAO_TRANSFERIDO;
                }

        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        if (result!=null) {
            if (result.equals(Config.TRANSFERIDO)) {
                ficheiro.setTransferido(true);
                for (File f:ficheirosTransferencia) {
                    if (f.isFile()){
                        ficheiro.setFicheiro(f);
                        ficheiro.arquivarFicheiro();
                    }

                }

            /*PrintWriter pw = null;
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException e) {

            }
            pw.close();*/
            }
            ficheiro.addLog(result + "\n");

            Log.d("PostExecuted", result);
        }else{
            ficheiro.addLog("Transferencia null\n");
        }
        ficheiro.contarFicheirosNovos();
    }


}
