package a21260825.dgomes.cubi1718_tp_p3.weka;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.*;

/**
 * Created by diogo on 07-07-2018.
 */

public class WekaTest {
    private static WekaTest instance;
    private static String labelFeature = Config.CLASS_LABEL;
    private final File ficheiroTrain;
    private String path;
    private String arff_train;
    private String arff_test;
    private Instances trainData;
    private Classifier newTree;
    private boolean trainDone;
    private Instances unlabeled;
    private MainActivity activity;
    private Train train;
    private boolean trainning;

    protected WekaTest()  {
       // path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + Config.PASTA_FICHEIRO_LOCAL;
        path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + Config.PASTA_FICHEIRO_LOCAL;
        arff_train = Config.FICHEIRO + "_" + Config.FICHEIRO_TRAIN + "_" + Config.EXTENCAO_ARFF;
        arff_test =path + Config.FICHEIRO + "_" + Config.FICHEIRO_TEST + "_" + Config.EXTENCAO_ARFF;
        File pasta = new File(path);
        ficheiroTrain = new File(pasta, arff_train);
        train = new Train();
        trainDone = false;
        trainning=false;

    }

    public static WekaTest getInstance()  {
        if(instance==null){
            instance = new WekaTest();
        }
        return instance;
    }
    public void setActivity(MainActivity activity){
        this.activity=activity;
    }
    public void reset(){
        instance = null;
        instance = new WekaTest();
    }
    private Instances readTrain() throws Exception {
        Log.d("WekaTest","readTrain: " + ficheiroTrain.getAbsolutePath());
       // DataSource source = new DataSource(arff_train);
        DataSource source = new DataSource(ficheiroTrain.getAbsolutePath());
        Instances data = source.getDataSet();
        return data;
    }

    private Instances readTest() throws Exception {
        DataSource source = new DataSource(arff_test);
        Instances data = source.getDataSet();
        return data;
    }

    private double accuracy(Classifier myClass, Instances testData) {
        float correctPreds = 0;
        for (int i = 0; i < testData.numInstances(); i++){
            String actual = testData.instance(i).stringValue(testData.classAttribute());
            String label = null;
            try {
                double pred = myClass.classifyInstance(testData.instance(i));
                label = testData.classAttribute().value((int) pred);
            } catch (Exception ex) {
                Log.d("WekaTest",ex.toString());
            }
            if (label.equals(actual)) {
                correctPreds++;
            }
        }
        return correctPreds / testData.numInstances();
    }
    

    public String test(Instance inst) {

        if (trainDone) {
            double newPred = -1.0;
            Log.d("WekaTest", "inst: " + inst.toString());
            try {
                if (unlabeled==null){
                    unlabeled = new Instances(inst.dataset());
                    Log.d("WekaTest", "unlabeled: " + unlabeled.toString());
                }
                /*
                newPred = newTree.classifyInstance(inst);
                String label = trainData.classAttribute().value((int) newPred);
                Log.d("WekaTest", "Index: " + newPred + " Prediction = " + label);
                newPred = -1.0;
                */

                double clsLabel = -1.0;
                if(unlabeled.size()==0)
                    unlabeled.add(0,inst);
                else
                    unlabeled.set(0,inst);

                unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
              //  Log.d("WekaTest", "unlabeled: " + unlabeled.toString());
                Instance i = unlabeled.instance(0);

                clsLabel = newTree.classifyInstance(i);
                i.setClassValue(clsLabel);
                String label = unlabeled.classAttribute().value((int) clsLabel);
                Double ac = accuracy(newTree,unlabeled);
                Log.d("WekaTest", "Index: " + clsLabel + " Prediction = " + label+" ac:"+Double.toString(ac));
                clsLabel = -1.0;
                return label;
                /*
                newPred = newTree.classifyInstance(inst);
                String label = trainData.classAttribute().value((int) newPred);
                Log.d("WekaTest", "Index: " + newPred + " Prediction = " + label);
                newPred = -1.0;
                */
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            train();
        }
        return "Training...";
    }
    private void train(){
        if (!trainning) {
            try {
                trainData = null;
                trainData = readTrain();
                if (trainData == null) {
                    Log.d("WekaTest", "data null!");
                    trainDone = false;

                } else {

                    trainData.setClassIndex(trainData.numAttributes() - 1);
                    Log.d("WekaTest","Enter thread");
                    //train_.run();
                    train.execute();
                    Log.d("WekaTest","Continue after thread");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
    
    private class Train extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {
            try {
                trainning=true;
                Log.d("WekaTest","Start trainning");
                    /*
                    String[] options = new String[1];
                    options[0] = "-U";            // unpruned tree
                    J48 tree = new J48();         // new instance of tree
                    tree.setOptions(options);     // set the options
                    */
                //RandomTree tree = new RandomTree();
                RandomForest tree = new RandomForest();
                tree.buildClassifier(trainData);   // build classifier
                newTree = tree;
                //Log.d("WekaTest","train newtree: " + newTree.toString());
                    /*
                     Classifier cls = new J48();
                    cls.buildClassifier(trainData);
                    // evaluate classifier and print some statistics
                    Evaluation eval = new Evaluation(trainData);
                    eval.evaluateModel(cls, test);
                    System.out.println(eval.toSummaryString("\nResults\n======\n", false));
                     */
                trainDone = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {


            if (result!=null) {
                activity.addLog("Prediction = " + result + "\n");
            }
        }

    }
}
