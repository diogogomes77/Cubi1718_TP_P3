package a21260825.dgomes.cubi1718_tp_p3.analise;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

/**
 * Created by diogo on 30-06-2018.
 */

public class WekaAnalyser {
    private static final String WEKA_TEST = "WekaTest";
    private final MainActivity activity;
    private Random mRandom = new Random();
    private Sample[] mSamples = new Sample[]{
            new Sample(1, 0, new double[]{5, 3.5, 2, 0.4}), // should be in the setosa domain
            new Sample(2, 1, new double[]{5.6, 3, 3.5, 1.2}), // should be in the versicolor domain
            new Sample(3, 2, new double[]{7, 3, 6.8, 2.1}) // should be in the virginica domain
    };
    private Classifier mClassifier = null;


    public WekaAnalyser(MainActivity activity) {
        this.activity = activity;
    }

    private void loadModel(){
        Log.d(WEKA_TEST, "loadModel()");

        AssetManager assetManager =  activity.getBaseContext().getAssets();;
        try {
            mClassifier = (Classifier) weka.core.SerializationHelper.read(assetManager.open("iris_model_logistic_allfeatures.model"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Weka "catch'em all!"
            e.printStackTrace();
        }
        //Toast.makeText(this, "Model loaded.", Toast.LENGTH_SHORT).show();
    }

    private void predict(){
        Log.d(WEKA_TEST, "predict()");

        if(mClassifier==null){
            //Toast.makeText(this, "Model not loaded!", Toast.LENGTH_SHORT).show();
            Log.d(WEKA_TEST, "Model not loaded!");
            return;
        }

        // we need those for creating new instances later
        // order of attributes/classes needs to be exactly equal to those used for training
        final Attribute attributeSepalLength = new Attribute("sepallength");
        final Attribute attributeSepalWidth = new Attribute("sepalwidth");
        final Attribute attributePetalLength = new Attribute("petallength");
        final Attribute attributePetalWidth = new Attribute("petalwidth");
        final List<String> classes = new ArrayList<String>() {
            {
                add("Iris-setosa"); // cls nr 1
                add("Iris-versicolor"); // cls nr 2
                add("Iris-virginica"); // cls nr 3
            }
        };

        // Instances(...) requires ArrayList<> instead of List<>...
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>(2) {
            {
                add(attributeSepalLength);
                add(attributeSepalWidth);
                add(attributePetalLength);
                add(attributePetalWidth);
                Attribute attributeClass = new Attribute("@@class@@", classes);
                add(attributeClass);
            }
        };
        // unpredicted data sets (reference to sample structure for new instances)
        Instances dataUnpredicted = new Instances("TestInstances",
                attributeList, 1);
        // last feature is target variable
        dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes() - 1);

        // create new instance: this one should fall into the setosa domain
        final Sample s = mSamples[mRandom.nextInt(mSamples.length)];
        DenseInstance newInstance = new DenseInstance(dataUnpredicted.numAttributes()) {
            {
                setValue(attributeSepalLength, s.features[0]);
                setValue(attributeSepalWidth, s.features[1]);
                setValue(attributePetalLength, s.features[2]);
                setValue(attributePetalWidth, s.features[3]);
            }
        };
        // reference to dataset
        newInstance.setDataset(dataUnpredicted);

        // predict new sample
        try {
            double result = mClassifier.classifyInstance(newInstance);
            String className = classes.get(new Double(result).intValue());
            String msg = "Nr: " + s.nr + ", predicted: " + className + ", actual: " + classes.get(s.label);
            Log.d(WEKA_TEST, msg);
            //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Sample {
        public int nr;
        public int label;
        public double [] features;

        public Sample(int _nr, int _label, double[] _features) {
            this.nr = _nr;
            this.label = _label;
            this.features = _features;
        }

        @Override
        public String toString() {
            return "Nr " +
                    nr +
                    ", cls " + label +
                    ", feat: " + Arrays.toString(features);
        }
    }
}
