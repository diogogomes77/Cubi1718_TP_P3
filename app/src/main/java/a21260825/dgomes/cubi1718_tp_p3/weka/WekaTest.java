package a21260825.dgomes.cubi1718_tp_p3.weka;

import android.os.Environment;
import android.util.Log;

import java.io.File;

import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.*;

/**
 * Created by diogo on 07-07-2018.
 */

public class WekaTest {

    public static String labelFeature = "activity";

    public Instances readTrain() throws Exception {
        String path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + Config.PASTA_FICHEIRO_LOCAL;

        String arff =path + Config.FICHEIRO + "_" + Config.FICHEIRO_TRAIN + "_" + Config.EXTENCAO_ARFF;

        DataSource source = new DataSource(arff);
        Instances data = source.getDataSet();
        return data;
    }
    public Instances readTest() throws Exception {
        String path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + Config.PASTA_FICHEIRO_LOCAL;

        String arff =path + Config.FICHEIRO + "_" + Config.FICHEIRO_TEST + "_" + Config.EXTENCAO_ARFF;

        DataSource source = new DataSource(arff);
        Instances data = source.getDataSet();
        return data;
    }

    public double accuracy(Classifier myClass, Instances testData) {

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

    public void main(String[] args) throws Exception {

        Instances data = null;
        data = readTrain();

        if (data == null) {
            Log.d("WekaTest","data null!");
        }
        else {
            Log.d("WekaTest","data size: " + data.numAttributes() + "x" + data.numInstances());
        }

        // Set label
        data.setClass(data.attribute(labelFeature));

        System.out.println("label: " + data.classAttribute().name() + " --- index: " + data.classIndex());


        // Try SimpleCart decision tree/classifier
       // SimpleCart tree = new SimpleCart();
        Classifier tree = new RandomTree() ;
        tree.buildClassifier(data);

        double acc = accuracy(tree, data);
        Log.d("WekaTest","Accuracy = " + acc);
        Log.d("WekaTest","tree(SimpleCart): " + tree);

        weka.core.SerializationHelper.write("tree.model", tree);

        RandomTree newTree = null;
        newTree = (RandomTree) weka.core.SerializationHelper.read("tree.model");

        acc = accuracy(newTree, data);
        Log.d("WekaTest","Accuracy = " + acc);

        Instances test = null;

        test = readTest();
        test.setClass(test.attribute(labelFeature));
        Log.d("WekaTest",test.attribute(labelFeature).toString());

        double newPred = -1.0;
        for (int i=0; i<test.numInstances(); i++) {
            newPred = newTree.classifyInstance(test.instance(i));
            Log.d("WekaTest",test.instance(i).toString());
            String label = data.classAttribute().value((int) newPred);
            Log.d("WekaTest","Index: " + newPred + " Prediction = " + label);
            newPred = -1.0;
        }
        Log.d("WekaTest","-----------------------------");
        System.out.println("-----------------------------");
        for (int i=0; i<data.numInstances(); i++) {
            newPred = newTree.classifyInstance(data.instance(i));
            System.out.println(data.instance(i));
            String label = data.classAttribute().value((int) newPred);
            System.out.println("Index: " + newPred + " Prediction = " + label);
            newPred = -1.0;
        }
    }
}
