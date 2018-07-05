package a21260825.dgomes.cubi1718_tp_p3.preprocessamento;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import a21260825.dgomes.cubi1718_tp_p3.utils.FFT;

/**
 * Created by diogo on 01-07-2018.
 */

public class Calculadora {
    private List<Double> values;

    public Calculadora() {
        values= new ArrayList<>();
    }

    public void addValue(double value){
        values.add(value);
    }

    public void resetValues(){
        values.clear();
    }

    private double sum(){
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
        double mean = 0.0;
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
        double sum = 0.0;
        double mean = mean();
        double result;

        for (double i : values)
            sum += Math.pow((i - mean), 2);
        result = Math.sqrt( sum / ( values.size() - 1 ) );
        //Log.d("sd","values.size="+Integer.toString(values.size()) + " result="+Double.toString(result));
        String res = "";
        for(Double i : values){
            res += Double.toString(i)+", ";
        }
       // Log.d("sd","values= "+res);
        return result;
    }

    public List<Double> getValues() {
        return values;
    }

    public double fft() {
        //int N = Config.PREPROC_COUNTER;
        int N = values.size();
        FFT fft = new FFT(N);
        double[] window = fft.getWindow();
        double[] re = new double[N];
        double[] im = new double[N];

        for(int i=0; i<N; i++) {
            re[i] = values.get(i);
            im[i] = 0;
        }
        fft.fft(re, im);

        double sum = 0.0;

        for(int i=0; i<re.length; i++)
            sum+= Math.pow(re[i],2);

        return Math.sqrt( sum / ( values.size() - 1 ) );
            //System.out.print(((int)(re[i]*1000)/1000.0) + " ");

        //System.out.print("]\nIm: [");
        //for(int i=0; i<im.length; i++)
        //    System.out.print(((int)(im[i]*1000)/1000.0) + " ");


    }
}
