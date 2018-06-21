package a21260825.dgomes.cubi1718_tp_p3.analise;

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
import weka.classifiers.trees.RandomForest;

/**
 * Created by diogo on 09-06-2018.
 */

public class Analyser {
    final int windowSize = 64;
    final int overlapSize = 32;

    public Analyser() {
        //SUBSYSTEM
        ARSubSystemBuilder subSystemBuilder = new ARSubSystemBuilder("SIDE");
        subSystemBuilder
                .featureExtractor("acc_x", RawNumericData.class).passDataDimension("acc", "x").done()
                .featureExtractor("difPosMaxMin_acc_x", DifPosMaxMin.class).passDataDimension("acc", "x").done()
                .featureExtractor("acc_x_quarterMean", QuarterMeans.class).passDataDimension("acc", "x").done()
                .featureExtractor("max_acc_x", Max.class).passDataDimension("acc", "x").done()
                .featureExtractor("min_acc_x", Min.class).passDataDimension("acc", "x").done()
                .featureSelector(new WekaFeatureSelector(new CfsSubsetEval()))
                .classificationLabels("LEFT", "RIGHT")
                .classifier(new WekaClassifier(new RandomForest()));
        //MAIN SYSTEM
        ARSystemBuilder systemBuilder = new ARSystemBuilder("ARSystem");
        ARSystem ars = systemBuilder
                .numericDataInputStream("acc", windowSize, overlapSize, "mag", "x", "y", "z")
                .numericDataInputStream("gyro", windowSize, overlapSize, "mag", "x", "y", "z")
                //feature extractors
                .featureExtractor("acc_x_fft", FFTMag.class).passDataDimension("acc", "x").done()
                .featureExtractor("entropy_acc_x", Entropy.class).
                        passPreviousExtractedFeature("acc" + "_x_fft").done()
                 // etc ...
                .classifier(new WekaClassifier(new RandomForest()))
                .classificationLabels("STANDING", "SIDE", "SQUAT", "JUMP")
                .subSystem(subSystemBuilder)
                .subLabelDelimiter("_")
                .reuseSameInstance(true)
                .createSystem();
        System.out.println(ars.toString());
        // ---------------------
        // train system
        // ---------------------
        ars.setMode(ARSystem.MODE_TRAINING);
        /*
        while (true) { //add your own logic instead
            while (!ars.isFull()) {
                ars.addData(0, 1.0, 0.8, 0.1, 0.2); //add data from acc
                ars.addData(1, 1.0, 0.8, 0.1, 0.2); //add data from gyro
            }
            ars.extractFeatures("standing");
        }
        ars.trainClassifier();
        // ---------------------
        // test system
        // ---------------------
        ars.setMode(ARSystem.MODE_TESTING);
        while (true) { //add your own logic instead
            while (!ars.isFull()) {
                ars.addData(0, 1.0, 0.8, 0.1, 0.2); //add data from acc
                ars.addData(1, 1.0, 0.8, 0.1, 0.2); //add data from gyro
            }
            String label = ars.classify(); //
            System.out.println(" ---> Classification: " + label);
        }
        */
    }
}
