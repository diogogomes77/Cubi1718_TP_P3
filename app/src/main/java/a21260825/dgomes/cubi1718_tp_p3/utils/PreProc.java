package a21260825.dgomes.cubi1718_tp_p3.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogo on 01-07-2018.
 */

public class PreProc {
    private List<Double> values;

    public PreProc() {
        values= new ArrayList<>();
    }

    public void addValue(double value){
        values.add(value);
    }

    public void resetValues(){
        values.clear();
    }

    private double sum (){
        if (values.size() > 0) {
            double sum = 0.0;

            for (double i : values) {
                sum += i;
            }
            return sum;
        }
        return 0.0;
    }

    public double mean (){
        double sum = sum();
        double mean = 0.0f;
        mean = sum / (values.size() * 1.0);
        return mean;
    }

    public double median (){
        int middle = values.size()/2;

        if (values.size() % 2 == 1) {
            return values.get(middle);
        } else {
            return (values.get(middle-1) + values.get(middle)) / 2.0;
        }
    }

    public double sd (){
        int sum = 0;
        double mean = mean();

        for (double i : values)
            sum += Math.pow((i - mean), 2);
        return Math.sqrt( sum / ( values.size() - 1 ) );
    }

    public List<Double> getValues() {
        return values;
    }
}
