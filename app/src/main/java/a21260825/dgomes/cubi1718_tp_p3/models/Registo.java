
package a21260825.dgomes.cubi1718_tp_p3.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.analise.Analyser;
import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;
import a21260825.dgomes.cubi1718_tp_p3.utils.PreProc;
import arsystem.ARSystem;

/**
 * Created by diogo on 14-04-2018.
 */

public class Registo {
    private static Registo instance;
    private List<CubiSensor> cubiSensores;
    private TreeMap<String,Float> valoresRegisto;
    private int cardinalValores = 0;
    private int contador = 0;
    private Ficheiro ficheiro;
    private String atividade;
    private long lastUpdate;
    private boolean novo = true;
    private TreeMap<String,Float> valoresRegistoPreProc;
    private List<TreeMap<String,Float>> listRegistosPreProc;
    private int preProcCounter;

    private List<String> keys;
    private ARSystem ars;
    private MainActivity activity;
    private boolean novoPreProc= true;
    private TreeMap<String,PreProc> forPreProc = new TreeMap<>();
    private ArrayList<String> keysPreProc;
    private double preProcValue;
    private StringBuilder result,resultPreProc;

    protected Registo(List<CubiSensor> cubiSensores, Ficheiro ficheiro) {
        this.cubiSensores = cubiSensores;
        this.ficheiro = ficheiro;
        valoresRegisto = new TreeMap<>();
        valoresRegistoPreProc = new TreeMap<>();
        this.ars = Analyser.getInstance().getArs();
        listRegistosPreProc = new ArrayList<>();
        resultPreProc = new StringBuilder();
        result = new StringBuilder();
    }

    private void preProcInit(){
        valoresRegistoPreProc.clear();
        listRegistosPreProc.clear();
        preProcCounter = Config.PREPROC_COUNTER;
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
                Float value = (float)valor.getValue();
                //if (value!=null && value!="") {
                    cardinalValores++;
                    valoresRegisto.put(key, value);
                //}
                    it.remove();

            }
        }
        preProcInit();
        //Log.d("cardinalValores",Integer.toString(cardinalValores));
    }

    private void addPreProc(){
        Boolean ok=false;
        for (String key: keys) {
            Float value = valoresRegisto.get(key);
                valoresRegistoPreProc.put(key,value);
                ok=true;
        }
        if (ok){
            listRegistosPreProc.add(valoresRegistoPreProc);
            preProcCounter--;
        }

        if (preProcCounter ==0){
            preProcCalculate(); // <-- problem aqui
            preProcInit();
        }
    }

    private void preProcCalculate(){
       // Log.d("preProcCalculate", "start");
       // Log.d("preProcCalculate", "listRegistosPreProc: " + listRegistosPreProc.size());
        for (TreeMap<String,Float> registo : listRegistosPreProc) {
           // Log.d("listRegistosPreProc", "registo: " + registo);
            for (Map.Entry<String, Float> entry : registo.entrySet()) {
                String key = entry.getKey();

                //Log.d("registo", key+": "+value);

                if (!forPreProc.containsKey(key)){
                    forPreProc.put(key,new PreProc());
                    Log.d("forPreProc", "put: " + key);
                }

                    PreProc preProc = forPreProc.get(key);

                    preProc.addValue(entry.getValue());
                //Log.d("preProc", "key:" + key + " addValue: " + Double.toString(entry.getValue()));


            }
        }


        ficheiro.saveValoresPreProc(this);

    }
    public String toStringPreProc(){
        if (novoPreProc){
            String headerPreProc = headerPreProc();
            Log.d("toStringPreProc", "header: " + headerPreProc);
            return headerPreProc;
        }else {
            //Log.d("toStringPreProc", "keysPreProc: " + keysPreProc.size());
            PreProc preProc = null;
            resultPreProc.setLength(0);
            resultPreProc.append(atividade);
            resultPreProc.append(",");
            String mean = "mean_";
            String median = "median_";
            String sd ="sd_";
            String del ="";
            preProcValue = 0.0;
            for (String key: keysPreProc) {
                // Log.d("toStringPreProc", "key: " + key);

                if(key.contains(mean)){
                    del = mean;
                    String cleanKey = key.replace(del,"");
                    preProc = forPreProc.get(cleanKey);
                    if (checkPreProc(preProc,cleanKey))
                        preProcValue = preProc.mean();
                    //preProcValue = 22.22;
                }
                else if(key.contains(median)){
                    del = median;
                    String cleanKey = key.replace(del,"");
                    preProc = forPreProc.get(cleanKey);
                    if (checkPreProc(preProc,cleanKey))
                        preProcValue = preProc.median();
                    //preProcValue = 22.22;
                }
                else if(key.contains(sd)){
                    del = sd;
                    String cleanKey = key.replace(del,"");
                    preProc = forPreProc.get(cleanKey);
                    if (checkPreProc(preProc,cleanKey))
                        preProcValue = preProc.sd();
                    //preProcValue = 22.22;
                }else{
                    preProcValue = 0.0;
                }
                if(preProc==null){
                    Log.d("toStringPreProc", "preProc: null key:"+ key);
                }else{
                    preProc.resetValues();
                }
                //preProcValue = 22.22;
                resultPreProc.append(Double.toString(preProcValue));
                resultPreProc.append(",");
            }

            resultPreProc.append(timestamp());
            return resultPreProc.toString();
        }
    }

    private Boolean checkPreProc(PreProc preProc, String key){
        int preProcSize = preProc.getValues().size();
        if (preProcSize!=Config.PREPROC_COUNTER){
            //Log.d("checkPreProc", key + ": " + Integer.toString(preProcSize) + " !=Config.PREPROC_COUNTER " + Integer.toString(Config.PREPROC_COUNTER));
            return false;
        }else {
            //Log.d("checkPreProc", key + ": " + Integer.toString(preProcSize));
            return true;
        }
    }
    private String headerPreProc() {
        novoPreProc = false;
        StringBuilder result = new StringBuilder();
        result.append("activity");
        result.append(",");
        //Iterator it = valoresRegisto.entrySet().iterator();
        keysPreProc = new ArrayList<>();
        List<String> extras = new ArrayList<>();
        extras.add("mean");
        extras.add("median");
        extras.add("sd");
        // extras.add("_fft");
        for(String extra : extras){
            Log.d("extras","added: " + extra);
        }
        for (Map.Entry<String, PreProc> registo : forPreProc.entrySet()) {
            //PreProc preProcCounter = registo.getValue();

            String key = registo.getKey();
            Log.d("forPreProc","key: " + key);
            for(String extra : extras){
                String extraKey = extra + "_" + key;
                result.append(extraKey);
                result.append(",");
                keysPreProc.add(extraKey);
            }

        }
        for(String extraKey : keysPreProc){
            Log.d("keysPreProc","added: " + extraKey);
        }
        result.append("timestamp");
        return result.toString();
    }


    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public void addValores(TreeMap<String,Float> valores){
        //Log.d("Registo","addValores");
        if (contador==cardinalValores){
            carregaValores();
        }else {
            //long curTime = System.currentTimeMillis();
            for(Map.Entry<String,Float> valor : valores.entrySet()) {
                String key = (String)valor.getKey();
                Float value = (float)valor.getValue();
                    valoresRegisto.put(key,value);
                    contador--;

                //Log.d("addValores", key + "=" + Float.toString(value));

                //if ((curTime - lastUpdate) > 5){
                terminaRegisto();
            }
          //  wekaAdd(valores);


        }
    }

    private void wekaAdd(TreeMap<String, Float> valores) {
        if (ars.isFull()){
            if (ars.getMode()==ARSystem.MODE_TRAINING) {
                ars.extractFeatures(atividade);
            }else {
                try{
                    String label = ars.classify(); //
                    // int i = ars.getFeaturesInstanceList().numberOfFeatures();
                    int i = ars.getDataInputStream(1).getNumberOfDimensions();
                    activity.addLog(" ---> Classification: " + label + "=" + i + "\n");

                    //ars.clearInstances();
                }catch (Exception e){
                    activity.addLog("Exception: " + e.toString() );
                }

                // String lastLabel = ars.getLastClassifiedLabel();
                //  Log.d("lastLabel","label=" + lastLabel);
                //  ars.clearInstances();
            }


        }else {
            // if (ars.getMode() == ARSystem.MODE_TRAINING) {
            if (valoresRegisto.get("acc_x") != null && valoresRegisto.get("acc_y") != null && valoresRegisto.get("acc_z") != null) {
                if (valoresRegisto.get("mag_x") != null && valoresRegisto.get("mag_y") != null && valoresRegisto.get("mag_z") != null) {
                    if (valoresRegisto.get("mag_mag") != null) {
                                /*
                                Float acc_x = Float.parseFloat(valoresRegisto.get("acc_x"));
                                Float acc_y = Float.parseFloat(valoresRegisto.get("acc_y"));
                                Float acc_z = Float.parseFloat(valoresRegisto.get("acc_z"));
                                */
                        Float acc_x = valoresRegisto.get("acc_x");
                        Float acc_y = valoresRegisto.get("acc_y");
                        Float acc_z = valoresRegisto.get("acc_z");
                        ars.addData(0, acc_x, acc_y, acc_z); //add data from acc
                                /*
                                Float mag_mag = Float.parseFloat(valoresRegisto.get("mag_mag"));
                                Float mag_x = Float.parseFloat(valoresRegisto.get("mag_x"));
                                Float mag_y = Float.parseFloat(valoresRegisto.get("mag_y"));
                                Float mag_z = Float.parseFloat(valoresRegisto.get("mag_z"));
                                */
                        Float mag_mag = valoresRegisto.get("mag_mag");
                        Float mag_x = valoresRegisto.get("mag_x");
                        Float mag_y = valoresRegisto.get("mag_y");
                        Float mag_z = valoresRegisto.get("mag_z");
                        ars.addData(1, mag_mag, mag_x, mag_y, mag_z); //add data from mag
                    }
                }
            }
            //   }
        }
    }


    /*
        private void iniciaRegisto(){
            carregaValores();
            contador = cardinalValores;
        }
    */
    private void terminaRegisto(){
        lastUpdate = System.currentTimeMillis();
        //Log.d("Registo","terminaRegisto");
        //valoresRegisto.clear();
        ficheiro.saveValores(this);

        addPreProc();
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
        keys = new ArrayList<>();
        for(Map.Entry<String,Float> entry : valoresRegisto.entrySet()) {
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

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public void setNovoPreProc() {
        this.novoPreProc=true;
    }
}
