
package a21260825.dgomes.cubi1718_tp_p3.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.arsystem.ArsLib;
import a21260825.dgomes.cubi1718_tp_p3.preprocessamento.Calculadora;
import a21260825.dgomes.cubi1718_tp_p3.preprocessamento.PreProc;
import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;

import arsystem.ARSystem;

/**
 * Created by diogo on 14-04-2018.
 */

public class Registo {

    private static Registo instance;
    private final PreProc preProc;
    private final ArsLib arsLib;
    private List<CubiSensor> cubiSensores;
    private TreeMap<String,Float> valoresRegisto;
    private int cardinalValores = 0;
    private int contador = 0;
    private Ficheiro ficheiro;
    private String atividade;
    private long lastUpdate;
    private boolean novo = true;

    private List<String> keys,keysRemain;

    private MainActivity activity;
    
    private StringBuilder result;

    protected Registo(List<CubiSensor> cubiSensores, Ficheiro ficheiro) {
        this.cubiSensores = cubiSensores;
        this.ficheiro = ficheiro;
        valoresRegisto = new TreeMap<>();
        preProc = PreProc.getInstance(ficheiro);
        //this.ars = Analyser.getInstance().getArs();
       
        result = new StringBuilder();
        arsLib = ArsLib.getInstance();

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

    private void carregaValoresIniciais(){
        //Log.d("Registo","carregaValores");
        keys = new ArrayList<>();
        keysRemain = new ArrayList<>();
        for (CubiSensor sensor: cubiSensores) {

            TreeMap<String, Float> valores =  sensor.getValores();
            for (Map.Entry<String, Float> entry : valores.entrySet()) {
                String key = entry.getKey();
                Float value = entry.getValue();
                keys.add(key);
                keysRemain.add(key);
                cardinalValores++;
                valoresRegisto.put(key, value);
                Log.d("valoresRegisto", "key:" + key + " put: " + Double.toString(value));
            }
            contador = cardinalValores;
        }
        preProc.setValores(valoresRegisto);
        preProc.setKeys(keys);
        preProc.init();
        //Log.d("cardinalValores",Integer.toString(cardinalValores));
    }


    public void setAtividade(String atividade) {
        this.atividade = atividade;
        preProc.setAtividade(atividade);

        arsLib.setAtividade(atividade);
    }

    public void addValores(TreeMap<String,Float> valores){
        //Log.d("Registo","addValores");
        if (0==cardinalValores){
            carregaValoresIniciais();
        }else {
            long curTime = System.currentTimeMillis();
            long passed = curTime - lastUpdate;
            if (passed > Config.REGISTO_INTERVALO){

                for(Map.Entry<String,Float> valor : valores.entrySet()) {
                    String key = valor.getKey();
                    Float value = valor.getValue();
                    valoresRegisto.put(key,value);
                    keysRemain.remove(key);
                    //  Log.d("valoresRegisto", "cardinal:" +Integer.toString(cardinalValores) + " keysRemain.size:"+Integer.toString(keysRemain.size())+" " +key + " put: " + Float.toString(value));

                    if (keysRemain.size()==0){
                        terminaRegisto();
                    }
                }
                arsLib.addValores(valoresRegisto);
            }

        }
    }




    private void terminaRegisto(){
        lastUpdate = System.currentTimeMillis();
        //Log.d("Registo","terminaRegisto");
        //valoresRegisto.clear();
        for(String key :keys){
            keysRemain.add(key);
        }
        ficheiro.saveValores(this);

        preProc.addCalculadora();
        //iniciaRegisto()
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
            result.setLength(0);
            result.append(atividade);
            result.append(",");
            for (String key: keys) {
                result.append(valoresRegisto.get(key));
                result.append(",");
            }
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

        for(Map.Entry<String,Float> entry : valoresRegisto.entrySet()) {
            String key = entry.getKey();
            result.append(key);
            result.append(",");

        }
        result.append("timestamp");
        return result.toString();
    }

    public String getAtividade() {
        return atividade;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
        arsLib.setActivity(activity);
    }

    public void setNovoCalculadora() {
        preProc.setNovoCalculadora();
    }
}
