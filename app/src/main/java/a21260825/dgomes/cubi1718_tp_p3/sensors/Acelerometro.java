package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;

/**
 * Created by diogo on 14-04-2018.
 */

public class Acelerometro extends CubiSensor {

    private static Acelerometro instance;
    private float[] gravity;
    private float xAcc, yAcc, zAcc;

    protected Acelerometro(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        registo = Registo.getInstance();
        valores = new HashMap<String,String>();
        valores.put("xAcc","");
        valores.put("yAcc","");
        valores.put("zAcc","");
        cubiSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravity = new float[]{0, 0, 0};
        sensorEventListener = new SensorEventListener() {
            private String result;
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSensorChanged(SensorEvent event) {
                final float alpha = 0.8f;
                long curTime = System.currentTimeMillis();
                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
                //if ((curTime - lastUpdate) > 1000) {
                if (timestamp != 0) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;
                    xAcc = event.values[0] - gravity[0];
                    yAcc = event.values[1] - gravity[1];
                    zAcc = event.values[2] - gravity[2];
                    result = "Acc X: " + round(xAcc) + "\t\t\tY: " + round(yAcc) + "\t\t\tZ: " + round(zAcc);
                    tv.setText(result);
                    valores.put("xAcc",Float.toString(xAcc));
                    valores.put("yAcc",Float.toString(yAcc));
                    valores.put("zAcc",Float.toString(zAcc));
                    /*
                    Iterator it = valores.entrySet().iterator();
                    Log.d("Acelerometro","valores");
                    while (it.hasNext()) {
                        Map.Entry valor = (Map.Entry)it.next();
                        String key = (String)valor.getKey();
                        String value = (String)valor.getValue();
                        Log.d(key,value);
                        it.remove();
                    }*/
                    registo.addValores(valores);
                    //valores.clear();
                    //Log.d("Acel",result);
                }
                timestamp = event.timestamp;
            }

            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }
        };
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static float round(float d) {
        int decimalPlace = 5;
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static Acelerometro getInstance(SensorManager mSensorManager){
        if(instance == null) {
            instance = new Acelerometro(mSensorManager);
        }
        return instance;
    }

}
