package a21260825.dgomes.cubi1718_tp_p3.arsystem;

import android.util.Log;

import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import arsystem.ARSystem;

/**
 * Created by dgomes on 05-07-2018.
 */

public class ArsLib {

    private static ArsLib instance;
    private ARSystem ars;
    private MainActivity activity;
    private String atividade;

    protected  ArsLib() {
        this.ars = Analyser.getInstance().getArs();

    }

    public static ArsLib getInstance(){
        if(instance==null){
            instance=new ArsLib();
        }
        return instance;
    }

    public void addValores(TreeMap<String, Float> valoresRegisto) {
        if (ars.isFull()) {
            if (ars.getMode() == ARSystem.MODE_TRAINING) {
                ars.extractFeatures(atividade);
            } else {
                try {
                    String label = ars.classify(); //
                    // int i = ars.getFeaturesInstanceList().numberOfFeatures();
                    int i = ars.getDataInputStream(1).getNumberOfDimensions();
                    activity.addLog(" ---> Classification: " + label + "=" + i + "\n");

                    //ars.clearInstances();
                } catch (Exception e) {
                    activity.addLog("Exception: " + e.toString());
                }

                // String lastLabel = ars.getLastClassifiedLabel();
                //  Log.d("lastLabel","label=" + lastLabel);
                //  ars.clearInstances();
            }


        } else {
            // if (ars.getMode() == ARSystem.MODE_TRAINING) {
            double acc_x, acc_y, acc_z, mag_mag, mag_x, mag_y, mag_z;
            acc_x = valoresRegisto.get("acc_x");
            acc_y = valoresRegisto.get("acc_y");
            acc_z = valoresRegisto.get("acc_z");
            mag_mag = valoresRegisto.get("mag_mag");
            mag_x = valoresRegisto.get("mag_x");
            mag_y = valoresRegisto.get("mag_y");
            mag_z = valoresRegisto.get("mag_z");
            String data = getData(acc_x) + getData(acc_y) + getData(acc_z);
            Log.d("ars.addData", data);
            data = getData(mag_mag) + getData(mag_x) + getData(mag_y) + getData(mag_z);
            Log.d("ars.addData", data);
            ars.addData(0, acc_x, acc_y, acc_z); //add data from acc
            ars.addData(1, mag_mag, mag_x, mag_y, mag_z); //add data from mag
            Log.d("instances", Integer.toString(ars.getFeaturesInstanceList().size()));
            //   }
        }
    }
    private String getData(double data){
        return ", " + Double.toString(data);
    }
    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }
}
