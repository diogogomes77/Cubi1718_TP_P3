package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.HashMap;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;

/**
 * Created by diogo on 14-04-2018.
 */

public class Gyroscopio extends CubiSensor {

    private static Gyroscopio instance;

    private float xGyro, yGyro, zGyro;

    protected Gyroscopio(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        valores = new HashMap<String,String>();
        valores.put("xGyro","");
        valores.put("yGyro","");
        valores.put("zGyro","");
        registo = Registo.getInstance();
        cubiSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorEventListener = new SensorEventListener() {

            private String result;
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    xGyro = event.values[0];
                    yGyro = event.values[1];
                    zGyro = event.values[2];
                    result = "Giroscopio X: " + Float.toString(xGyro) + " Y: " + Float.toString(yGyro) + " Z: " + Float.toString(zGyro);
                    tv.setText(result);
                    valores.put("xGyro",Float.toString(xGyro));
                    valores.put("yGyro",Float.toString(yGyro));
                    valores.put("zGyro",Float.toString(zGyro));
                    registo.addValores(valores);
                }
            }
        };
    }

    public static Gyroscopio getInstance(SensorManager mSensorManager){
        if(instance == null) {
            instance = new Gyroscopio(mSensorManager);
        }
        return instance;
    }
}
