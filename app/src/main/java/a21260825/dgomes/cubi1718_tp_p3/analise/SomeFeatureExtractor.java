package a21260825.dgomes.cubi1718_tp_p3.analise;

import arsystem.data.ImmutableNumericData;
import arsystem.featureextraction.AbstractNumericFeatureExtractor;
import arsystem.featureextraction.extractedfeature.ExtractedFeature;

/**
 * Created by diogo on 09-06-2018.
 */

public class SomeFeatureExtractor extends AbstractNumericFeatureExtractor {
    ImmutableNumericData data;
    public SomeFeatureExtractor(ImmutableNumericData data) {
        super(data);
// store data input values
        this.data = data;
    }
    @Override
    public void extractFeatures(ExtractedFeature featureResult) {
// do something with the data...
        double [] values = data.getValues();
// add result to a feature result object
        featureResult.addValues(values);
    }
}