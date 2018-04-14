package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by diogo on 14-04-2018.
 */

public class Gyroscopio extends CubiSensor {

    private static Gyroscopio instance;

    private float xGyro, yGyro, zGyro;

    protected Gyroscopio(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;

        cubiSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorEventListener = new SensorEventListener() {
            private String result;
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                long curTime = System.currentTimeMillis();
                //if ((curTime - lastUpdate) > 1000) {
                if (timestamp != 0) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    xGyro = event.values[0];
                    yGyro = event.values[1];
                    zGyro = event.values[2];
                    result = "Giroscopio X: " + xGyro + " Y: " + yGyro + " Z: " + zGyro;
                    tv.setText(result);
                    //Log.d("Gyro",result);
                }
                timestamp = event.timestamp;
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
