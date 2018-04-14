package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Created by diogo on 14-04-2018.
 */

public class Ficheiro {

    private static Ficheiro instance;

    protected Ficheiro() {
    }

    public static Ficheiro getInstance(){
        if(instance == null) {
            instance = new Ficheiro();
        }
        return instance;
    }

    public boolean criarPasta() {
        String path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + Config.PASTA_FICHEIRO;
        File storageDir = new File(path);
        //tv.append("Criar pastas "+storageDir.getAbsolutePath()+"\n");
        if (!storageDir.exists() && !storageDir.mkdirs()) {
            //Toast.makeText(this, "ERRO: nao criou pastas\n", Toast.LENGTH_LONG).show();
            //tvPermStorage.setText("erro");
            return false;
        } else {
            //Toast.makeText(this, "Pasta criada: " + path + "\n", Toast.LENGTH_LONG).show();
            //tvPermStorage.setText(path);
            //prepararRecolha();
            return true;
        }
    }
}
