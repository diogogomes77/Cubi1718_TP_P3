package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.HashMap;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;

/**
 * Created by diogo on 14-04-2018.
 */

public class Orientador extends CubiSensor {

    private static Orientador instance;

    protected Orientador(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        valores = new HashMap<String,String>();
        valores.put(Config.MAG_X,"");
        valores.put(Config.MAG_Y,"");
        valores.put(Config.MAG_Z,"");
        valores.put(Config.MAG,"");

        registo = Registo.getInstance();
        cubiSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorEventListener = new SensorEventListener() {
            private String result;
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    // get values for each axes X,Y,Z
                    float magX = event.values[0];
                    float magY = event.values[1];
                    float magZ = event.values[2];
                    double magnitude = Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));
                    valores.put(Config.MAG_X,Float.toString(magX));
                    valores.put(Config.MAG_Y,Float.toString(magY));
                    valores.put(Config.MAG_Z,Float.toString(magZ));
                    valores.put(Config.MAG,Double.toString(magnitude));
                    result = "Magnetic X: " + Float.toString(magX) + " Y: " + Float.toString(magY) + " Z: " + Float.toString(magZ);
                    tv.setText(result);
                    registo.addValores(valores);
                }
            }
        };
    }

    public static Orientador getInstance(SensorManager mSensorManager){
        if(instance == null) {
            instance = new Orientador(mSensorManager);
        }
        return instance;
    }
}
