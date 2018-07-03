package a21260825.dgomes.cubi1718_tp_p3.analise;

import android.util.Log;

import a21260825.dgomes.cubi1718_tp_p3.utils.Config;
import arsystem.ARSubSystemBuilder;
import arsystem.ARSystem;
import arsystem.ARSystemBuilder;
import arsystem.classification.WekaClassifier;
import arsystem.featureextraction.featureextractors.DifPosMaxMin;
import arsystem.featureextraction.featureextractors.Entropy;
import arsystem.featureextraction.featureextractors.FFTMag;
import arsystem.featureextraction.featureextractors.Max;
import arsystem.featureextraction.featureextractors.Min;
import arsystem.featureextraction.featureextractors.QuarterMeans;
import arsystem.featureextraction.featureextractors.RawNumericData;
import arsystem.featureselection.WekaFeatureSelector;
import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

/**
 * Created by diogo on 09-06-2018.
 */

public class Analyser {
    //final int windowSize = 64;
    //final int overlapSize = 32;
    final int windowSize = 32;
    final int overlapSize = 16;

    private ARSubSystemBuilder subSystemBuilder1,subSystemBuilder2;
    private ARSystemBuilder systemBuilder;
    private ARSystem ars;
    private static Analyser instance;
    public static Analyser getInstance(){
        if (instance==null)
            instance = new Analyser();
        return instance;
    }
    protected Analyser() {
        //SUBSYSTEM
        subSystemBuilder1 = new ARSubSystemBuilder("LshakeH");
        subSystemBuilder1
                .featureExtractor("acc_x", RawNumericData.class).passDataDimension("acc", "x").done()
                .featureExtractor("difPosMaxMin_acc_x", DifPosMaxMin.class).passDataDimension("acc", "x").done()
                .featureExtractor("acc_x_quarterMean", QuarterMeans.class).passDataDimension("acc", "x").done()
                .featureExtractor("max_acc_x", Max.class).passDataDimension("acc", "x").done()
                .featureExtractor("min_acc_x", Min.class).passDataDimension("acc", "x").done()
                .featureSelector( new WekaFeatureSelector( new CfsSubsetEval() ) )
                .classificationLabels("LEFT", "RIGHT")
                .classifier( new WekaClassifier( new RandomForest() ) );
        subSystemBuilder2 = new ARSubSystemBuilder("LshakeV");
        subSystemBuilder2
                .featureExtractor("acc_y", RawNumericData.class).passDataDimension("acc", "y").done()
                .featureExtractor("difPosMaxMin_acc_y", DifPosMaxMin.class).passDataDimension("acc", "y").done()
                .featureExtractor("acc_y_quarterMean", QuarterMeans.class).passDataDimension("acc", "y").done()
                .featureExtractor("max_acc_y", Max.class).passDataDimension("acc", "y").done()
                .featureExtractor("min_acc_y", Min.class).passDataDimension("acc", "y").done()
                .classificationLabels("UP", "DOWN")
                .classifier( new WekaClassifier( new RandomForest() ) );

        /*
                .featureExtractor("acc_z", RawNumericData.class).passDataDimension("acc", "z").done()
                .featureExtractor("difPosMaxMin_acc_z", DifPosMaxMin.class).passDataDimension("acc", "z").done()
                .featureExtractor("acc_z_quarterMean", QuarterMeans.class).passDataDimension("acc", "z").done()
                .featureExtractor("max_acc_z", Max.class).passDataDimension("acc", "z").done()
                .featureExtractor("min_acc_z", Min.class).passDataDimension("acc", "z").done()

                .featureExtractor("mag_mag", RawNumericData.class).passDataDimension("mag", "mag").done()
                .featureExtractor("difPosMaxMin_mag_mag", DifPosMaxMin.class).passDataDimension("mag", "mag").done()
                .featureExtractor("mag_mag_quarterMean", QuarterMeans.class).passDataDimension("mag", "mag").done()
                .featureExtractor("max_mag_mag", Max.class).passDataDimension("mag", "mag").done()
                .featureExtractor("min_mag_mag", Min.class).passDataDimension("mag", "mag").done()

                .featureExtractor("mag_x", RawNumericData.class).passDataDimension("mag", "x").done()
                .featureExtractor("difPosMaxMin_mag_x", DifPosMaxMin.class).passDataDimension("mag", "x").done()
                .featureExtractor("mag_x_quarterMean", QuarterMeans.class).passDataDimension("mag", "x").done()
                .featureExtractor("max_mag_x", Max.class).passDataDimension("mag", "x").done()
                .featureExtractor("min_mag_x", Min.class).passDataDimension("mag", "x").done()

                .featureExtractor("mag_y", RawNumericData.class).passDataDimension("mag", "y").done()
                .featureExtractor("difPosMaxMin_mag_y", DifPosMaxMin.class).passDataDimension("mag", "y").done()
                .featureExtractor("mag_y_quarterMean", QuarterMeans.class).passDataDimension("mag", "y").done()
                .featureExtractor("max_mag_y", Max.class).passDataDimension("mag", "y").done()
                .featureExtractor("min_mag_y", Min.class).passDataDimension("mag", "y").done()

                .featureExtractor("mag_z", RawNumericData.class).passDataDimension("mag", "z").done()
                .featureExtractor("difPosMaxMin_mag_z", DifPosMaxMin.class).passDataDimension("mag", "z").done()
                .featureExtractor("mag_z_quarterMean", QuarterMeans.class).passDataDimension("mag", "z").done()
                .featureExtractor("max_mag_z", Max.class).passDataDimension("mag", "z").done()
                .featureExtractor("min_mag_z", Min.class).passDataDimension("mag", "z").done()

                .featureSelector(new WekaFeatureSelector(new CfsSubsetEval()))
               // .classificationLabels("LshakeH", "LshakeV", "TwistH", "TwistV", "SshakeH", "SshakeV")
                .classifier(new WekaClassifier(new RandomForest()));
                */
                //.classifier(new WekaClassifier(new J48()));
        //MAIN SYSTEM

        systemBuilder = new ARSystemBuilder("ARSystem");
        ars = systemBuilder
                .numericDataInputStream("acc", windowSize, overlapSize, "x", "y", "z")
                .numericDataInputStream("mag", windowSize, overlapSize, "mag", "x", "y", "z")
                //feature extractors
                /*
                .featureExtractor("acc_x_fft", FFTMag.class).passDataDimension("acc", "x").done()
                .featureExtractor("entropy_acc_x", Entropy.class).
                        passPreviousExtractedFeature("acc" + "_x_fft").done()
                .featureExtractor("acc_y_fft", FFTMag.class).passDataDimension("acc", "y").done()
                .featureExtractor("entropy_acc_y", Entropy.class).
                        passPreviousExtractedFeature("acc" + "_y_fft").done()
                .featureExtractor("acc_z_fft", FFTMag.class).passDataDimension("acc", "z").done()
                .featureExtractor("entropy_acc_z", Entropy.class).
                        passPreviousExtractedFeature("acc" + "_z_fft").done()
                */
                .featureExtractor("mag_mag_fft", FFTMag.class).passDataDimension("mag", "mag").done()
                .featureExtractor("entropy_mag_mag", Entropy.class).
                        passPreviousExtractedFeature("mag" + "_mag_fft").done()
                /*
                .featureExtractor("mag_x_fft", FFTMag.class).passDataDimension("mag", "x").done()
                .featureExtractor("entropy_mag_x", Entropy.class).
                        passPreviousExtractedFeature("mag" + "_x_fft").done()
                .featureExtractor("mag_y_fft", FFTMag.class).passDataDimension("mag", "y").done()
                .featureExtractor("entropy_mag_y", Entropy.class).
                        passPreviousExtractedFeature("mag" + "_y_fft").done()
                .featureExtractor("mag_z_fft", FFTMag.class).passDataDimension("mag", "z").done()
                .featureExtractor("entropy_mag_z", Entropy.class).
                        passPreviousExtractedFeature("mag" + "_z_fft").done()
                  */
                 // etc ...
                .classifier(new WekaClassifier(new RandomForest()))
                //.classifier(new WekaClassifier(new J48()))
                .classificationLabels(Config.ACT1, Config.ACT2, Config.ACT3, Config.ACT4, Config.ACT5,
                        Config.ACT6,Config.ACT7,Config.ACT8,Config.ACT9,Config.ACT10)
                //.subSystem(subSystemBuilder1)
                //.subSystem(subSystemBuilder2)
                //.subLabelDelimiter("_")
                .reuseSameInstance(false)
                .createSystem();
       System.out.println(ars.toString());
       //Log.d("init",ars.toString());

        // ---------------------
        // train system
        // ---------------------
        /*
        ars.setMode(ARSystem.MODE_TRAINING);

        while (true) { //add your own logic instead
            while (!ars.isFull()) {
                ars.addData(0, 1.0, 0.8, 0.1, 0.2); //add data from acc
                ars.addData(1, 1.0, 0.8, 0.1, 0.2); //add data from gyro
            }
            ars.extractFeatures("standing");
        }
        ars.trainClassifier();
        */
        // ---------------------
        // test system
        // ---------------------
        /*ars.setMode(ARSystem.MODE_TESTING);
        while (true) { //add your own logic instead
            while (!ars.isFull()) {
                ars.addData(0, 1.0, 0.8, 0.1, 0.2); //add data from acc
                ars.addData(1, 1.0, 0.8, 0.1, 0.2); //add data from gyro
            }
            String label = ars.classify(); //
            System.out.println(" ---> Classification: " + label);
        }*/

    }
    public ARSystem getArs(){
        return ars;
    }
    //public void addData(String activity, int index, Float x,Float y,Float z){

    //}


}
