package a21260825.dgomes.cubi1718_tp_p3.preprocessamento;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;
import a21260825.dgomes.cubi1718_tp_p3.weka.WekaArff;

/**
 * Created by dgomes on 05-07-2018.
 */

public class PreProc {

    private static PreProc instance;
    private final Ficheiro ficheiro;
    private TreeMap<String, Double> calculados;
    private WekaArff wekaArff;
    private List<String> extras;
    private String mode;

    protected PreProc(Ficheiro ficheiro){
        valoresRegistoCalculadora = new TreeMap<>();
        listRegistosCalculadora = new TreeMap<>();
        resultCalculadora = new StringBuilder();
        this.ficheiro=ficheiro;
        wekaArff = WekaArff.getInstance();
        keysCalculadora = new ArrayList<>();
        extras = new ArrayList<>();
        extras.add("mean");
        extras.add("median");
        extras.add("sd");
        extras.add("fft");
        //wekaArff.setFeatures(extras);
        //for(String extra : extras){
        //    Log.d("extras","added: " + extra);
        //}
    }

    public static PreProc getInstance(Ficheiro ficheiro){
        if(instance==null){
            instance=new PreProc(ficheiro);
        }
        return instance;
    }

    private TreeMap<String, Float> valoresRegisto;
    private TreeMap<Integer, TreeMap<String, Double>> listRegistosCalculadora;
    private TreeMap<String,Double> valoresRegistoCalculadora;
    private int preProcCounter;
    private String atividade;
    private List<String> keys;
    private MainActivity activity;
    private boolean novoPreProc = true;
    private TreeMap<String,Calculadora> forCalculadora = new TreeMap<>();
    private ArrayList<String> keysCalculadora;

    private StringBuilder resultCalculadora;

    public void init(){
        Log.d("Registo","preProcInit");
        valoresRegistoCalculadora.clear();
        listRegistosCalculadora.clear();
        preProcCounter = Config.PREPROC_COUNTER;
    }
    private void resetCalculadoras(){
        Log.d("Registo","resetCalculadoras");
        Calculadora preProc;
        for (String key: keys) {
            preProc = forCalculadora.get(key);
            preProc.resetValues();
        }
    }
    public void setKeys(List<String> keys){
        this.keys=keys;
    }
    public void addCalculadora() {

        for (String key: keys) {
            double value = valoresRegisto.get(key);
            valoresRegistoCalculadora.put(key,value);
            // Log.d("valoresRegistoCalculadora", "key:" + key + " put: " + Double.toString(value));
        }
        int index = Config.PREPROC_COUNTER-preProcCounter;
        //TreeMap<String,Double> registo = new TreeMap<>();
        TreeMap<String, Double> valorNovo = new TreeMap<>();
        for (Map.Entry<String,Double> valor : valoresRegistoCalculadora.entrySet()) {
            valorNovo.put(valor.getKey(),valor.getValue());
        }
        listRegistosCalculadora.put(index,valorNovo);
        //listRegistosCalculadora.put(index,valoresRegistoCalculadora);
        preProcCounter--;
        if (preProcCounter ==0){
            preProcCalculate(); // <-- problem aqui
            init();
        }
    }
    private void preProcCalculate(){
        // Log.d("preProcCalculate", "start");
        Log.d("preProcCalculate", "listRegistosCalculadora: " + listRegistosCalculadora.size());
        for (Map.Entry<Integer,TreeMap<String,Double>> registo : listRegistosCalculadora.entrySet()) {
            int c = registo.getKey();
            TreeMap<String,Double> reg = registo.getValue();
            //Log.d("listRegistosCalculadora", "registo: " + Integer.toString(c));// + " reg:" + reg.toString());
            for (Map.Entry<String, Double> entry : reg.entrySet()) {
                String key = entry.getKey();

                if (!forCalculadora.containsKey(key)){
                    forCalculadora.put(key,new Calculadora());// cria o Calculadora para começar a encher de dados
                    //Log.d("forCalculadora", "put: " + key);
                }
                Calculadora preProc = forCalculadora.get(key);
                preProc.addValue(entry.getValue());
                //Log.d("preProc", "key:" + key + " addValue: " + Double.toString(entry.getValue()));
            }
        }
        if (keysCalculadora.size()==0)
            setKeysCalculadora();
        calculados = calcular(keysCalculadora);
        Log.d("keysCalculadora-1",Integer.toString(keysCalculadora.size()));
        wekaArff.setFeatures(keysCalculadora);
       // wekaArff.setAtividade(atividade);
        wekaArff.addInstance(calculados);
        ficheiro.saveValoresCalculadora(this);
        resetCalculadoras();
    }

    @Override
    public String toString(){
        if (novoPreProc){
            String headerCalculadora = headerCalculadora();
            Log.d("toStringCalculadora", "header: " + headerCalculadora);
            return headerCalculadora;
        }else {
            //Log.d("toStringCalculadora", "keysCalculadora: " + keysCalculadora.size());

            resultCalculadora.setLength(0);
            resultCalculadora.append(atividade);
            resultCalculadora.append(",");

            for(Map.Entry<String,Double> calculado : calculados.entrySet()) {
                //String key = calculado.getKey();
                resultCalculadora.append(calculado.getValue());
                resultCalculadora.append(",");
            }
            /*
            for (String key: keysCalculadora) {
                // Log.d("toStringCalculadora", "key: " + key);

                String value = Double.toString(getCalculado(key));
                resultCalculadora.append(value);
                resultCalculadora.append(",");

            }*/

            resultCalculadora.append(timestamp());
            return resultCalculadora.toString();
        }
    }

    private Boolean checkCalculadora(Calculadora preProc, String key){
        int preProcSize = preProc.getValues().size();
        if (preProcSize<Config.PREPROC_COUNTER){
            Log.d("checkCalculadora", key + ": " + Integer.toString(preProcSize) + " <Config.PREPROC_COUNTER " + Integer.toString(Config.PREPROC_COUNTER));
            return false;
        }else {
            //Log.d("checkCalculadora", key + ": " + Integer.toString(preProcSize));
            return true;
        }
    }

    private String headerCalculadora() {
        novoPreProc = false;
        StringBuilder result = new StringBuilder();
        result.append("activity");
        result.append(",");
        //Iterator it = valoresRegisto.entrySet().iterator();

        for(String extraKey : keysCalculadora){
            Log.d("keysCalculadora","added: " + extraKey);
            result.append(extraKey);
            result.append(",");
        }
        result.append("timestamp");
        return result.toString();
    }

    private void setKeysCalculadora(){
        for (Map.Entry<String, Calculadora> registo : forCalculadora.entrySet()) {
            //Calculadora preProcCounter = registo.getValue();

            String key = registo.getKey();
            Log.d("forCalculadora","key: " + key);
            for(String extra : extras){
                String extraKey = extra + "_" + key;
                keysCalculadora.add(extraKey);
                Log.d("setKeysCalculadora","extraKey: " + extraKey);
            }
        }
    }

    private String timestamp(){
        Long tsLong = System.currentTimeMillis();
        return tsLong.toString();
    }

    private TreeMap<String,Double> calcular(List<String> keys){
        TreeMap<String,Double> calculados = new TreeMap<>();
        for (String key: keys) {
            calculados.put(key,getCalculado(key));
        }
        return calculados;
    }

    private double getCalculado(String key) {
        /*
        String mean = "mean";
        String median = "median";
        String sd ="sd";
        String fft ="fft";
        String del ="";
        List<String> remain = new ArrayList<>();
        remain.add(mean);
        remain.add(median);
        remain.add(sd);
        remain.add(fft);
        */
        String del ="";
        double preProcValue=0.0;
        Calculadora preProc = null;

        for (String calc:extras){
            if(key.contains(calc)){
                del=calc+"_";
                String cleanKey = key.replace(del,"");
                preProc = forCalculadora.get(cleanKey);
                if (checkCalculadora(preProc,cleanKey))
                    preProcValue = preProc.getCalculated(calc);
                else Log.d("checkCalculadora", "false");
            }
        }

        if(preProc==null){
            Log.d("toStringCalculadora", "preProc: null key:"+ key);
        }
        return preProcValue;
    }
    public void setNovoCalculadora() {
        this.novoPreProc =true;
    }

    public void setValores(TreeMap<String,Float> valores) {
        this.valoresRegisto = valores;
    }

    public void setNovoPreProc() {
        this.novoPreProc = true;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
        wekaArff.setAtividade(atividade);
    }

    public void setMode(String mode) {
        this.mode = mode;
        wekaArff.setMode(mode);
    }
}
