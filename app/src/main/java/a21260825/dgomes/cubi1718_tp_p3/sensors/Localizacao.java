package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by diogo on 14-04-2018.
 */

public class Localizacao extends CubiSensor{
    private FusedLocationProviderClient mFusedLocationClient;
    private static Localizacao instance;
    private Activity activity;


    protected Localizacao(Activity activity, final TextView tv) {
        this.activity=activity;
        if (ActivityCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.activity);
    }

    public static Localizacao getInstance(Activity activity, TextView tv) {
        if(instance == null) {
            instance = new Localizacao(activity,tv);
        }
        return instance;
    }

    @Override
    public void iniciar() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this.activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            tv.setText("Latitude: " + location.getLatitude());
                            tv.append("\nLongitude: " + location.getLongitude());
                            tv.append("\nAltitude: " + location.getAltitude());
                        }
                    }
                });
    }

    @Override
    public void terminar() {
        //mSensorManager.unregisterListener(sensorEventListener);
    }
}
