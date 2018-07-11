package a21260825.dgomes.cubi1718_tp_p3.weka;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

/**
 * Created by dgomes on 06-07-2018.
 */

public class WekaArff {
    private final ArrayList<Attribute> attributes;
    private final WekaTest wekaTest;
    private String atividade;
    private Instances mDataset;
    private Attribute attributeAtividade;
    private static WekaArff instance;
    private List<String> atividades;
    private int mFeatLen;
    private boolean done;
    private Ficheiro ficheiro;
    private OnNovosCalculadosTask calculadosTask;
    protected String modo;
    private MainActivity activity;

    protected WekaArff() throws Exception {
        atividades = new ArrayList<>();
        setAtividades();
        attributes = new ArrayList<Attribute>();
        done = false;
        ficheiro = Ficheiro.getInstance();
        wekaTest = WekaTest.getInstance();
    }

    public static WekaArff getInstance() throws Exception {
        if(instance==null){
            instance=new WekaArff();
        }
        return instance;
    }

    public void setAtividade(String at){
        this.atividade=at;
    }

    public void setFeatures(List<String> keysCalculadora){
        Log.d("keysCalculadora-2",Integer.toString(keysCalculadora.size()));
        if (!done){

            for (String key : keysCalculadora) {
                Attribute a = new Attribute(key);
                attributes.add(a);
                Log.d("setFeatures","extra: " + key + " = " + a.toString());
               // Log.d("setFeatures","Attribute: " + a.name());
            }
            attributeAtividade = new Attribute(Config.CLASS_LABEL, atividades);
            attributes.add(attributeAtividade);
            mDataset = new Instances("extras", attributes,Config.PREPROC_COUNTER);
            done=true;
        }else{
            Log.d("setFeatures","pass");
        }
        // Adding the max feature
        //attributes.add(new Attribute(Globals.FEAT_MAX_LABEL));
        // Set the last column/attributeAtividade (standing/walking/running) as the class
        // index for classification
        // mDataset.setClassIndex(mDataset.numAttributes() - 1);
    }

    public void addInstance(TreeMap<String, Double> calculados){
        calculadosTask = new OnNovosCalculadosTask(calculados);
        calculadosTask.execute();
    }

    public void stop(){
        ArffSaver saver = new ArffSaver();
        // Set the data source of the file content
        saver.setInstances(mDataset);
        Log.e("WekaArff stop", mDataset.size()+"");
        try {
            File mFeatureFile;
            if (modo==null){
                Log.d("WekaArff stop", "modo null");
                mFeatureFile = ficheiro.getWekaArff();
            }else{
                Log.d("WekaArff stop", "modo: " + modo);
                if (modo.contentEquals(Config.MODE_SAVE) || modo.contentEquals(Config.MODE_SAVE))
                    mFeatureFile = ficheiro.getWekaArff();
                else //if (modo.contentEquals(Config.MODE_TRAIN))
                    mFeatureFile = ficheiro.getWekaArffTrain();
               // else if (modo.contentEquals(Config.MODE_AUTO))

            }

            saver.setFile(mFeatureFile);
            // Write into the file
            saver.writeBatch();
            Log.d("ArffSaver",modo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setAtividades(){
        atividades.add(Config.ACT1);
        atividades.add(Config.ACT2);
        atividades.add(Config.ACT3);
        atividades.add(Config.ACT4);
        atividades.add(Config.ACT5);
        atividades.add(Config.ACT6);
        atividades.add(Config.ACT7);
        atividades.add(Config.ACT8);
        atividades.add(Config.ACT9);
        atividades.add(Config.ACT10);
    }

    public void setMode(String mode) {
        this.modo = mode;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    private class OnNovosCalculadosTask extends AsyncTask<Void, Void, String> {

        private final TreeMap<String, Double> calculados;
        private String prediction="";

        public OnNovosCalculadosTask(TreeMap<String, Double> calculados) {
            this.calculados=calculados;

        }

        @Override
        protected String doInBackground(Void... arg0) {

            Log.d("addInstance", calculados.toString());
            Instance inst = new DenseInstance(attributes.size());
            inst.setDataset(mDataset);
            Double value = null;
            for( Attribute a : attributes){
                String extra = a.name();
                //Log.i("Attribute", a.name() + ": " + a.toString());
                value = calculados.get(extra);
                if (value!=null){
                   // Log.i("createInstances", a.name() + " = " + Double.toString(value));
                    inst.setValue(a,value);
                }
            }
           // Log.i("Attribute", attributeAtividade.name() + ": " + attributeAtividade.toString());
          //  Log.i("atividade", atividade);
            if(modo!=null) {
                Log.d("WekaArff", "modo:" + modo + " atividade:" + atividade);
                if (modo.contentEquals(Config.MODE_TRAIN) || modo.contentEquals(Config.MODE_SAVE))
                    inst.setValue(attributeAtividade, atividade);
                else {
                    //inst.setValue(attributeAtividade, "?");
                    prediction = wekaTest.test(inst);
                }
            }
            else
                inst.setValue(attributeAtividade, atividade);
            mDataset.add(inst);
            Log.i("new instance", mDataset.size() + "");
            return prediction;
        }
        @Override
        protected void onPostExecute(String result) {


            if (result!=null) {
                activity.addLog("Prediction = " + result + "\n");
            }
        }

    }
}

