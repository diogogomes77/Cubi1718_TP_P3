package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.models.Registo;


/**
 * Created by diogo on 14-04-2018.
 */

public abstract class CubiSensor {
    protected Sensor cubiSensor;
    protected float timestamp;
    protected long lastUpdate = 0;
    protected TextView tv;
    protected Registo registo;

    protected TreeMap<String,Float> valores = new TreeMap<>();

    protected SensorManager mSensorManager;
    protected SensorEventListener sensorEventListener;

    public void iniciar() {
        mSensorManager.registerListener(
                sensorEventListener,
                cubiSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void terminar() {
        mSensorManager.unregisterListener(sensorEventListener);
    }

    public TreeMap<String, Float> getValores() {
        return valores;
    }

    @Override
    public String toString() {
        if (cubiSensor==null)
            return this.getClass().getSimpleName() + " (null)";
        return cubiSensor.getName() + " (" + cubiSensor.getType() + ")";
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }
}
