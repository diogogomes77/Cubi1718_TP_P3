package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.HashMap;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;

/**
 * Created by diogo on 17-04-2018.
 */

public class Bateria extends CubiSensor {
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static Bateria instance;
    private String locationProvider;
    private Intent batteryStatus;

    private BroadcastReceiver mBatInfoReceiver;

    private Activity activity;

    public static Bateria getInstance(Activity activity) {
        if(instance == null) {
            instance = new Bateria(activity);
        }
        return instance;
    }

    protected Bateria(Activity activity) {
        this.activity = activity;
        registo = Registo.getInstance();

        valores = new HashMap<String, String>();



        valores.put(Config.BATERIA,"");
        /*
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = activity.registerReceiver(null, ifilter);
        */

        mBatInfoReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent intent) {
                int level = intent.getIntExtra("level", 0);
                valores.put(Config.BATERIA,String.valueOf(level));
                tv.setText("Batery: " + String.valueOf(level) + "%");
                registo.addValores(valores);
            }
        };
    }


    @Override
    public void iniciar() {

        activity.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

/*
        int level = batteryStatus.getIntExtra(BatteryManaer.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale;

*/
    }

    @Override
    public void terminar() {
        activity.unregisterReceiver(this.mBatInfoReceiver);
    }


}
