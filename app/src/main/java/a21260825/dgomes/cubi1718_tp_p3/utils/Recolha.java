package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Acelerometro;
import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Gyroscopio;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Localizacao;
import a21260825.dgomes.cubi1718_tp_p3.sensors.LocalizacaoFused;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Luminometro;

/**
 * Created by diogo on 14-04-2018.
 */

public class Recolha {

    private static Recolha instance;
    private MainActivity activity;
    private Ficheiro ficheiro;


    // Acquire a reference to the system Location Manager
    private LocationManager locationManager;
    // Define a listener that responds to location updates
    private LocationListener locationListener;

    private SensorManager mSensorManager;
    private List<CubiSensor> cubiSensores;

    private TextView tvXyz;

    protected Recolha(MainActivity activity, Ficheiro ficheiro) {
        this.activity = activity;
        this.tvXyz = activity.getTvXyz();
        cubiSensores = new ArrayList<>();
    }

    public static Recolha getInstance(MainActivity activity, Ficheiro ficheiro) {
        if(instance == null) {
            instance = new Recolha(activity,ficheiro);
        }
        return instance;
    }

    public void preparar(){
        inicializarSensores();
        //startLocationListener();
    }

    private void inicializarSensores() {
       /* lat = new double[5];
        lon = new double[5];
        alt = new double[5];*/

        mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        addSensor(Gyroscopio.getInstance(mSensorManager));
        addSensor(Acelerometro.getInstance(mSensorManager));
        addSensor(Luminometro.getInstance(mSensorManager));
        addSensor(LocalizacaoFused.getInstance(this.activity));
        addSensor(Localizacao.getInstance(this.activity));


        //locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        //lumi = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    private void addSensor(CubiSensor sensor){
        TextView tv = new TextView(activity);
        activity.getSensorTvs().addView(tv);
        sensor.setTv(tv);
        cubiSensores.add(sensor);
        activity.addLog(sensor.getClass().getSimpleName() + " adicionado\n");
    }



    private void startLocationListener() {






/*
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                tvlat.setText("Latitude: " + lat[0]);
                tvlon.setText("Longitude: " + lon[0]);
                tvlat.setText("Altitude: " + alt[0]);

                //makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
*/

    }

    public void iniciar() {
        for (CubiSensor sensor :cubiSensores) {
            sensor.iniciar();
            activity.addLog(sensor.getClass().getSimpleName() + " iniciado\n");
        }
    }

    public void terminar() {
        for (CubiSensor sensor :cubiSensores) {
            sensor.terminar();
            activity.addLog(sensor.getClass().getSimpleName() + " terminado\n");
        }
    }
}
