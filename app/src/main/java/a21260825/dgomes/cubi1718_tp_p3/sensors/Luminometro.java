package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import java.util.HashMap;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;

/**
 * Created by diogo on 14-04-2018.
 */

public class Luminometro extends CubiSensor {
    private static Luminometro instance;
    private float luminosidade;

    protected Luminometro(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        registo = Registo.getInstance();
        valores = new HashMap<String,String>();
        valores.put(Config.Lum,"");
        cubiSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                        luminosidade = event.values[0];
                        tv.setText("Luminosidade: " + luminosidade);
                        valores.put(Config.Lum, Float.toString(luminosidade));
                        registo.addValores(valores);
                }
            }
        };
    }

    public static Luminometro getInstance(SensorManager mSensorManager) {
        if(instance == null) {
            instance = new Luminometro(mSensorManager);
        }
        return instance;
    }
}
