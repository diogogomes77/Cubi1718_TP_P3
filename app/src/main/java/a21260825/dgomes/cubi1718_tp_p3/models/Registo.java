
package a21260825.dgomes.cubi1718_tp_p3.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;

/**
 * Created by diogo on 14-04-2018.
 */

public class Registo {
    private static Registo instance;
    private List<CubiSensor> cubiSensores;
    private HashMap<String,String> valoresRegisto;
    private int cardinalValores = 0;
    private int contador = 0;
    private Ficheiro ficheiro;
    private String atividade;
    private long lastUpdate;
    private boolean novo = true;

    protected Registo(List<CubiSensor> cubiSensores, Ficheiro ficheiro) {
        this.cubiSensores = cubiSensores;
        this.ficheiro = ficheiro;
        valoresRegisto = new HashMap<>();
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
            HashMap valores =  sensor.getValores();
            Iterator it = valores.entrySet().iterator();
            while (it.hasNext()) {
                cardinalValores++;
                Map.Entry valor = (Map.Entry)it.next();
                valoresRegisto.put((String)valor.getKey(),(String)valor.getValue());
                it.remove();
            }
        }
        //Log.d("cardinalValores",Integer.toString(cardinalValores));
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public void addValores(HashMap<String,String> valores){
        //Log.d("Registo","addValores");
        if (contador==cardinalValores){
            carregaValores();
        }else {
            long curTime = System.currentTimeMillis();
            Iterator it = valores.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry valor = (Map.Entry)it.next();
                String key = (String)valor.getKey();
                String value = (String)valor.getValue();
                //Log.d(key,value);
                //if (valoresRegisto.get(key) == null){
                    //Log.d("Registo","null");
                    valoresRegisto.put(key,value);
                    contador--;
                if ((curTime - lastUpdate) > 125){
                    terminaRegisto();
                }

                 //   if (contador==0){
                 //       terminaRegisto();
                 //   }
                //}else {
                    //Log.d("Registo",valoresRegisto.get(key));
               // }
                it.remove();
            }
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


    private List<String> keys;

    private String header() {
        novo = false;
        StringBuilder result = new StringBuilder();
        result.append("activity");
        result.append(",");
        Iterator it = valoresRegisto.entrySet().iterator();
        keys = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry valor = (Map.Entry)it.next();
            String key = (String)valor.getKey();
            //String value = (String)valor.getValue();
            result.append(key);
            result.append(",");
            keys.add(key);
            it.remove();
        }
        result.append("timestamp");
        return result.toString();
    }
}
