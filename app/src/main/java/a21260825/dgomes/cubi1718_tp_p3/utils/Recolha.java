package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a21260825.dgomes.cubi1718_tp_p3.activities.MainActivity;
import a21260825.dgomes.cubi1718_tp_p3.arsystem.Analyser;
import a21260825.dgomes.cubi1718_tp_p3.models.Registo;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Acelerometro;
import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.sensors.Magnetometro;
import arsystem.ARSystem;

/**
 * Created by diogo on 14-04-2018.
 */

public class Recolha {

    private static Recolha instance;
    private MainActivity activity;
    private Ficheiro ficheiro;
    private Registo registo;
    private  ARSystem ars;

    // Acquire a reference to the system Location Manager
    private LocationManager locationManager;
    // Define a listener that responds to location updates
    private LocationListener locationListener;

    private SensorManager mSensorManager;
    private List<CubiSensor> cubiSensores;
    private String mode;

    protected Recolha(MainActivity activity, Ficheiro ficheiro) {
        this.activity = activity;
        this.ficheiro = ficheiro;
        cubiSensores = new ArrayList<>();
        registo = Registo.getInstance(cubiSensores,ficheiro);
        registo.setActivity(activity);

        ars= Analyser.getInstance().getArs();
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

        //addSensor(LocalizacaoFused.getInstance(this.activity));
        //addSensor(Gyroscopio.getInstance(mSensorManager));
        addSensor(Acelerometro.getInstance(mSensorManager));
        //addSensor(Luminometro.getInstance(mSensorManager));
       // addSensor(Localizacao.getInstance(this.activity));
        addSensor(Magnetometro.getInstance(mSensorManager));
      //  addSensor(Bateria.getInstance(this.activity));

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

    public void iniciar(String mode) {
        this.mode=mode;
        this.registo.setMode(mode);
        switch (mode){
            case Config.MODE_TRAIN:
                modeTrain();
                break;
            case Config.MODE_SAVE:
                modeSave(Config.MODE_SAVE);
                break;
            case Config.MODE_AUTO:
                modeAuto();
                break;
        }
    }

    private void modeTrain(){
        if (Config.ARSLIB)
            ars.setMode(ARSystem.MODE_TRAINING);
        modeSave(Config.MODE_TRAIN);
    }

    private void modeSave(String mode){
        if(ficheiro.startSave(mode)){
            for (CubiSensor sensor :cubiSensores) {
                sensor.iniciar();
                if (mode.contentEquals(Config.MODE_TRAIN)){
                    activity.addLog(sensor.toString() + " treino iniciado\n");
                }else{
                    activity.addLog(sensor.toString() + " iniciado\n");
                }

            }
        }
    }

    private void modeAuto(){
        ars.setMode(ARSystem.MODE_TESTING);
        for (CubiSensor sensor :cubiSensores) {
            sensor.iniciar();
            activity.addLog(sensor.toString() + " reconhecimentos iniciado\n");
        }
    }

    public void terminar() {

        this.mode=mode;
        switch (mode){
            case Config.MODE_TRAIN:
                for (CubiSensor sensor :cubiSensores) {
                    sensor.terminar();
                    activity.addLog(sensor.toString() + " treino terminado\n");
                }
                ficheiro.stopSaving();
                if (Config.ARSLIB){
                    ars.trainClassifier();
                    Log.d("terminar","ars.toString()");
                    System.out.println(ars.toString());
                }

                break;
            case Config.MODE_SAVE:
                for (CubiSensor sensor :cubiSensores) {
                    sensor.terminar();
                    activity.addLog(sensor.toString() + " terminado\n");
                }
                ficheiro.stopSaving();
                break;
            case Config.MODE_AUTO:
                for (CubiSensor sensor :cubiSensores) {
                    sensor.terminar();
                    activity.addLog(sensor.toString() + " reconhecimento terminado\n");
                }
                if (Config.ARSLIB){
                    if (ars.isFull()){
                        String label = ars.classify(); //
                        activity.addLog(" ---> Classification: " + label + "\n");
                        //String last_label = ars.getLastClassifiedLabel();
                        //Log.d("last_label",last_label);
                    }else{
                        activity.addLog(" ---> Classification: " + "not full yet...\n");

                    }
                    Log.d("terminar","ars.toString()");
                    System.out.println(ars.toString());
                }

                break;
        }

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
