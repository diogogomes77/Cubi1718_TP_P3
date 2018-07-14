package a21260825.dgomes.cubi1718_tp_p3.preprocessamento;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
    private TreeMap<String, Float> valoresRegisto;
    private TreeMap<Integer, TreeMap<String, Double>> listRegistosCalculadora;
    private TreeMap<Integer, TreeMap<String, Double>> listRegistosCalculadoraFifo;
    private TreeMap<String,Double> valoresRegistoCalculadora;
    private int preProcCounter;
    private String atividade;
    private List<String> keys;
    private MainActivity activity;
    private boolean novoPreProc = true;
    private TreeMap<String,Calculadora> forCalculadora = new TreeMap<>();
    private ArrayList<String> keysCalculadora;
    //private Queue<TreeMap<String, Double>> listRegistosCalculadoraFifo;

    private StringBuilder resultCalculadora;
    private boolean firstDone;

    protected PreProc(Ficheiro ficheiro) {
        valoresRegistoCalculadora = new TreeMap<>();
        listRegistosCalculadora = new TreeMap<>();
        listRegistosCalculadoraFifo = new TreeMap<>();
        resultCalculadora = new StringBuilder();
        this.ficheiro=ficheiro;
        try {
            wekaArff = WekaArff.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        keysCalculadora = new ArrayList<>();
        extras = new ArrayList<>();
        extras.add("mean");
        extras.add("median");
        extras.add("sd");
        extras.add("fft");
        extras.add("min");
        extras.add("max");

        firstDone = false;
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
    public void setActivity(MainActivity activity){
        this.activity=activity;
    }
    public void setKeys(List<String> keys){
        this.keys=keys;
    }

    public void addCalculadora_stack() {
        if (Config.STACK){
            for (String key: keys) {
                double value = valoresRegisto.get(key);
                valoresRegistoCalculadora.put(key, value);
                // Log.d("valoresRegistoCalculadora", "key:" + key + " put: " + Double.toString(value));
            }

            TreeMap<String, Double> valorNovo = new TreeMap<>();

            for (Map.Entry<String,Double> valor : valoresRegistoCalculadora.entrySet()) {
                valorNovo.put(valor.getKey(),valor.getValue());
            }
            TreeMap<String, Double> temp;
            for (int i = listRegistosCalculadora.size()-1; i>0 ; i--){

                temp = listRegistosCalculadora.get(i-1);
                //  Log.d("listRegistosCalculadora","i=" + Integer.toString(i)+" tempo=" + temp.toString());
                listRegistosCalculadora.put(i,temp);
            }
            listRegistosCalculadora.put(0,valorNovo);
            preProcCalculate();

        }else {
            firstDone=false;
        }

    }
    public void addCalculadora() {
        if (!firstDone){
            for (String key: keys) {
                double value = valoresRegisto.get(key);
                valoresRegistoCalculadora.put(key,value);
                // Log.d("valoresRegistoCalculadora", "key:" + key + " put: " + Double.toString(value));
            }
            int index = Config.PREPROC_COUNTER-preProcCounter;

            TreeMap<String, Double> valorNovo = new TreeMap<>();

            for (Map.Entry<String,Double> valor : valoresRegistoCalculadora.entrySet()) {
                valorNovo.put(valor.getKey(),valor.getValue());
            }
            listRegistosCalculadora.put(index,valorNovo);

            preProcCounter--;
            if (preProcCounter ==0){
                if (Config.STACK){
                    firstDone =true;
                    preProcCalculate();
                    addCalculadora_stack();// <-- envia para stack aqui
                }
                else{
                    preProcCalculate();
                    init();
                }

            }
        }else{
            addCalculadora_stack();
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
        //activity.beep();
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
        result.append(Config.CLASS_LABEL);
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
        init();
    }

    public void setMode(String mode) {
        this.mode = mode;
        wekaArff.setMode(mode);
    }
}
