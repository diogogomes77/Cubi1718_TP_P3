package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.HashMap;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;

/**
 * Created by diogo on 15-04-2018.
 */

public class Magnetometro extends CubiSensor {

    private static Magnetometro instance;

    protected Magnetometro(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        valores = new TreeMap<String,String>();
        valores.put(Config.MAG_X,"");
        valores.put(Config.MAG_Y,"");
        valores.put(Config.MAG_Z,"");
       // valores.put(Config.MAG,"");

        registo = Registo.getInstance();
        cubiSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorEventListener = new SensorEventListener() {
            private String result;
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

                if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    String result="";
                    switch (accuracy) {
                        case 0:
                            result=("Unreliable");

                            break;
                        case 1:
                            result=("Low Accuracy");

                            break;
                        case 2:
                            result=("Medium Accuracy");


                            break;
                        case 3:
                            result=("High Accuracy");

                            break;
                    }
                    Log.d("Magnetometro",result);
                }
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    // get values for each axes X,Y,Z
                    float magX = event.values[0];
                    float magY = event.values[1];
                    float magZ = event.values[2];
                    float magnitude = (float) Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));
                    valores.put(Config.MAG_X,Float.toString(magX));
                    valores.put(Config.MAG_Y,Float.toString(magY));
                    valores.put(Config.MAG_Z,Float.toString(magZ));
                    valores.put(Config.MAG,Float.toString(magnitude));
                    result = "Magnetic mag:" + Float.toString(magnitude) + " X: " + Float.toString(magX) + " Y: " + Float.toString(magY) + " Z: " + Float.toString(magZ);
                    tv.setText(result);
                    registo.addValores(valores);
                }
            }
        };
    }

    public static Magnetometro getInstance(SensorManager mSensorManager){
        if(instance == null) {
            instance = new Magnetometro(mSensorManager);
        }
        return instance;
    }
}
