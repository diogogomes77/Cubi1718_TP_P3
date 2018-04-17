package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.models.Registo;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Acelerometro;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Bateria;
import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Gyroscopio;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Localizacao;
import a21260825.dgomes.cubi1718_tp_p3.sensors.LocalizacaoFused;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Luminometro;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Magnetometro;

/**
 * Created by diogo on 14-04-2018.
 */

public class Recolha {

    private static Recolha instance;
    private MainActivity activity;
    private Ficheiro ficheiro;
    private Registo registo;

    // Acquire a reference to the system Location Manager
    private LocationManager locationManager;
    // Define a listener that responds to location updates
    private LocationListener locationListener;

    private SensorManager mSensorManager;
    private List<CubiSensor> cubiSensores;

    protected Recolha(MainActivity activity, Ficheiro ficheiro) {
        this.activity = activity;
        this.ficheiro = ficheiro;
        cubiSensores = new ArrayList<>();
        registo = Registo.getInstance(cubiSensores,ficheiro);
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

        mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);

        listSensors(mSensorManager);

        addSensor(Gyroscopio.getInstance(mSensorManager));
        addSensor(Acelerometro.getInstance(mSensorManager));
        addSensor(Luminometro.getInstance(mSensorManager));
        //addSensor(LocalizacaoFused.getInstance(this.activity));
        addSensor(Localizacao.getInstance(this.activity));
        addSensor(Magnetometro.getInstance(mSensorManager));
        addSensor(Bateria.getInstance(this.activity));

    }
    private void listSensors(SensorManager mSensorManager){
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        activity.addLog("Sensores disponiveis:\n");
        for(int i=0; i<deviceSensors.size();i++) {
            activity.addLog("\t"+ deviceSensors.get(i).getName() + "("
                    + deviceSensors.get(i).getType() + ")\n");
        }
    }

    public Registo getRegisto() {
        return registo;
    }

    private void addSensor(CubiSensor sensor){
        TextView tv = new TextView(activity);
        activity.getSensorTvs().addView(tv);
        sensor.setTv(tv);
        cubiSensores.add(sensor);
        activity.addLog(sensor.toString() + " adicionado\n");
    }

    public void iniciar() {
        if(ficheiro.startSave()){
            for (CubiSensor sensor :cubiSensores) {
                sensor.iniciar();
                activity.addLog(sensor.toString() + " iniciado\n");
            }
        }

    }

    public void terminar() {
        for (CubiSensor sensor :cubiSensores) {
            sensor.terminar();
            activity.addLog(sensor.toString() + " terminado\n");
        }
        ficheiro.stopSaving();
    }

    public void pausar() {
        for (CubiSensor sensor :cubiSensores) {
            sensor.terminar();
            activity.addLog(sensor.toString() + " pausado\n");
        }
    }

    public void continuar() {
        for (CubiSensor sensor :cubiSensores) {
            sensor.iniciar();
            activity.addLog(sensor.toString() + " continuado\n");
        }
    }
}
