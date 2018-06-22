
package a21260825.dgomes.cubi1718_tp_p3.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.analise.Analyser;
import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;
import arsystem.ARSystem;

/**
 * Created by diogo on 14-04-2018.
 */

public class Registo {
    private static Registo instance;
    private List<CubiSensor> cubiSensores;
    private TreeMap<String,String> valoresRegisto;
    private int cardinalValores = 0;
    private int contador = 0;
    private Ficheiro ficheiro;
    private String atividade;
    private long lastUpdate;
    private boolean novo = true;
    private List<TreeMap<String,String>> registos64;
    private List<String> keys;
    private ARSystem ars;

    protected Registo(List<CubiSensor> cubiSensores, Ficheiro ficheiro) {
        this.cubiSensores = cubiSensores;
        this.ficheiro = ficheiro;
        valoresRegisto = new TreeMap<>();
        registos64 = new ArrayList<>();
        this.ars = Analyser.getInstance().getArs();
    }

    public static Registo getInstance(List<CubiSensor> cubiSensores, Ficheiro ficheiro){
        if (instance == null){
            instance = new Registo(cubiSensores,ficheiro);
        }
        return instance;
    }
    public static Registo getInstance() {
        if (instance != null){
            return instance;
        }
        return null;
    }

    private void carregaValores(){
        //Log.d("Registo","carregaValores");

        for (CubiSensor sensor: cubiSensores) {
            TreeMap valores =  sensor.getValores();
            Iterator it = valores.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry valor = (Map.Entry)it.next();
                String key = (String)valor.getKey();
                String value = (String)valor.getValue();
                //if (value!=null && value!="") {
                    cardinalValores++;
                    valoresRegisto.put(key, value);
                //}
                    it.remove();

            }
        }

        //Log.d("cardinalValores",Integer.toString(cardinalValores));
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public void addValores(TreeMap<String,String> valores){
        //Log.d("Registo","addValores");
        if (contador==cardinalValores){
            carregaValores();
        }else {
            //long curTime = System.currentTimeMillis();
            for(Map.Entry<String,String> valor : valores.entrySet()) {
                String key = (String)valor.getKey();
                String value = (String)valor.getValue();
                if (value==null || value ==""){
                    Log.d(key,value);
                }
                //Log.d("addValores", key + "=" + value);
                valoresRegisto.put(key,value);
                contador--;
                //if ((curTime - lastUpdate) > 5){
                terminaRegisto();
            }
            //while (true) { //add your own logic instead
                if (ars.isFull() && ars.getMode()==ARSystem.MODE_TRAINING) {
                    ars.extractFeatures(atividade);
                    //Log.d("data window", "ars.isFull()");
                }else {
                    if (valoresRegisto.get("acc_x") != null && valoresRegisto.get("acc_y") != null && valoresRegisto.get("acc_z") != null) {
                        //Log.d("data window", "acc");
                        //String accc = valoresRegisto.get("acc_x") + " - " + valoresRegisto.get("acc_y") + " - " + valoresRegisto.get("acc_z");
                        //Log.d("data window", accc);
                        //String magg = valoresRegisto.get("mag_x") + " - " + valoresRegisto.get("mag_y") + " - " + valoresRegisto.get("mag_z");
                        //Log.d("data window", magg);
                        if (valoresRegisto.get("mag_x") != null && valoresRegisto.get("mag_y") != null && valoresRegisto.get("mag_z") != null){
                            //Log.d("data window", "mag_x,y,z");
                            if (valoresRegisto.get("mag_mag") != null){
                                //Log.d("data window", "mag");
                                Float acc_x = Float.parseFloat(valoresRegisto.get("acc_x"));
                                Float acc_y = Float.parseFloat(valoresRegisto.get("acc_y"));
                                Float acc_z = Float.parseFloat(valoresRegisto.get("acc_z"));
                               // if (acc_x!=0.0 && acc_y!=0.0 && acc_z!=0.0)
                                    ars.addData(0,acc_x , acc_y, acc_z); //add data from acc
                                Float mag_mag = Float.parseFloat(valoresRegisto.get("mag_mag"));
                                Float mag_x = Float.parseFloat(valoresRegisto.get("mag_x"));
                                Float mag_y = Float.parseFloat(valoresRegisto.get("mag_y"));
                                Float mag_z = Float.parseFloat(valoresRegisto.get("mag_z"));
                               // if (mag_x!=0.0 && mag_y!=0.0 && mag_z!=0.0 && mag_mag!=0.0)
                                    ars.addData(1, mag_mag,mag_x, mag_y, mag_z); //add data from mag
                            }
                        }
                    }
                }
            /*
            Iterator it = valores.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry valor = (Map.Entry)it.next();
                String key = (String)valor.getKey();
                String value = (String)valor.getValue();
                if (value==null || value =="")
                    Log.d(key,value);
                valoresRegisto.put(key,value);
                contador--;
                //if ((curTime - lastUpdate) > 5){
                terminaRegisto();
                // }

                 //   if (contador==0){
                 //       terminaRegisto();
                 //   }

                it.remove();
            }*/
        }
    }

    private void iniciaRegisto(){
        carregaValores();
        contador = cardinalValores;
    }

    private void terminaRegisto(){
        lastUpdate = System.currentTimeMillis();
        //Log.d("Registo","terminaRegisto");
        //valoresRegisto.clear();
        ficheiro.saveValores(this);

        //iniciaRegisto();
    }

    private String timestamp(){
        Long tsLong = System.currentTimeMillis();
        return tsLong.toString();
    }

    @Override
    public String toString() {
        if (novo){
            return header();
        }else {
            StringBuilder result = new StringBuilder();
            result.append(atividade);
            result.append(",");
            for (String key: keys) {
                result.append(valoresRegisto.get(key));
                result.append(",");
            }
            /*
            Iterator it = valoresRegisto.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry valor = (Map.Entry) it.next();
                //String key = (String)valor.getKey();
                String value = (String) valor.getValue();
                result.append(value);
                result.append(",");
                it.remove();
            }*/
            result.append(timestamp());
            return result.toString();
        }
    }

    public void setNovo() {
        this.novo = true;
    }



    private String header() {
        novo = false;
        StringBuilder result = new StringBuilder();
        result.append("activity");
        result.append(",");
        //Iterator it = valoresRegisto.entrySet().iterator();
        keys = new ArrayList<>();
        for(Map.Entry<String,String> entry : valoresRegisto.entrySet()) {
            String key = entry.getKey();
            result.append(key);
            result.append(",");
            keys.add(key);
        }/*
        while (it.hasNext()) {
            Map.Entry valor = (Map.Entry)it.next();
            String key = (String)valor.getKey();
            //String value = (String)valor.getValue();
            result.append(key);
            result.append(",");
            keys.add(key);
            it.remove();
        }*/
        result.append("timestamp");
        return result.toString();
    }

    public String getAtividade() {
        return atividade;
    }
}
