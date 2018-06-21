
package a21260825.dgomes.cubi1718_tp_p3.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;

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

    protected Registo(List<CubiSensor> cubiSensores, Ficheiro ficheiro) {
        this.cubiSensores = cubiSensores;
        this.ficheiro = ficheiro;
        valoresRegisto = new TreeMap<>();
        registos64 = new ArrayList<>();
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

                    cardinalValores++;
                    valoresRegisto.put(key,value);
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
                Log.d("addValores", key + "=" + value);
                valoresRegisto.put(key,value);
                contador--;
                //if ((curTime - lastUpdate) > 5){
                terminaRegisto();
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
}
