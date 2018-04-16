package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public Transferencia(Ficheiro ficheiro) {
        this.ficheiro=ficheiro;
        this.file = ficheiro.getFicheiro();

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
        if (file!=null){
            String localFile = file.getAbsolutePath();
            if (localFile!=null){
                try {
                    String host = Config.HOST;
                    String user = Config.USER;
                    String passwd = Config.PASSWORD;

                    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyhhmmss");
                    String remoteFile = Config.PASTA_REMOTA.concat("/").concat(Config.FICHEIRO).concat("_").concat(formatter.format(new Date().getTime())).concat(Config.EXTENCAO);
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
                    sftp.put(localFile,remoteFile);
                    channel.disconnect();
                    session.disconnect();
                    transferenciaConcluida = true;
                    return Config.TRANSFERIDO;
                } catch (JSchException e) {
                    ficheiro.addLog(e.getMessage().toString());
                    e.printStackTrace();
                    transferenciaConcluida = false;
                    return e.toString();
                } catch (SftpException e) {
                    ficheiro.addLog(e.getMessage().toString());
                    e.printStackTrace();
                    transferenciaConcluida = false;
                    return e.toString();
                }
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        if (result!=null) {
            if (result.equals(Config.TRANSFERIDO)) {
                ficheiro.setTransferido(true);
                ficheiro.arquivarFicheiro();
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
