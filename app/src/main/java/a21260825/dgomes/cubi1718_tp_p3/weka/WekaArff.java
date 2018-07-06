package a21260825.dgomes.cubi1718_tp_p3.weka;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private String atividade;
    private Instances mDataset;
    private Attribute attributeAtividade;
    private static WekaArff instance;
    private List<String> atividades;
    private int mFeatLen;
    private boolean done;
    private Ficheiro ficheiro;
    private OnNovosCalculadosTask calculadosTask;

    protected WekaArff(){
        atividades = new ArrayList<>();
        setAtividades();
        attributes = new ArrayList<Attribute>();
        done = false;
        ficheiro = Ficheiro.getInstance();
    }

    public static WekaArff getInstance(){
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
            attributeAtividade = new Attribute("atividades", atividades);
            attributes.add(attributeAtividade);
            for (String key : keysCalculadora) {
                Attribute a = new Attribute(key);
                attributes.add(a);
                Log.d("setFeatures","extra: " + key + " = " + a.toString());
               // Log.d("setFeatures","Attribute: " + a.name());
            }
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
            // Set the destination of the file.
            File mFeatureFile = ficheiro.getWekaArff();
            saver.setFile(mFeatureFile);
            // Write into the file
            saver.writeBatch();
            Log.i("batch","write batch here");

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

    private class OnNovosCalculadosTask extends AsyncTask<Void, Void, Void> {

        private final TreeMap<String, Double> calculados;

        public OnNovosCalculadosTask(TreeMap<String, Double> calculados) {

            this.calculados=calculados;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.i("addInstance", calculados.toString());
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
            inst.setValue(attributeAtividade, atividade);
            mDataset.add(inst);
            Log.i("new instance", mDataset.size() + "");
            return null;
        }
    }
}

