package a21260825.dgomes.cubi1718_tp_p3.sensors;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;

/**
 * Created by diogo on 14-04-2018.
 */

public class Localizacao extends CubiSensor {
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static Localizacao instance;

    private Activity activity;


    protected Localizacao(Activity activity) {
        this.activity = activity;
        valores = new HashMap<String,String>();
        valores.put("Latitude","");
        valores.put("Longitude","");
        valores.put("Altitude","");
        registo = Registo.getInstance();
        locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                tv.setText(location.getLatitude()+","+location.getLongitude()
                        +","+location.getAltitude());
                valores.put("Latitude",Double.toString(location.getLatitude()));
                valores.put("Longitude",Double.toString(location.getLongitude()));
                valores.put("Altitude",Double.toString(location.getAltitude()));
                registo.addValores(valores);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

    }

    public static Localizacao getInstance(Activity activity) {
        if(instance == null) {
            instance = new Localizacao(activity);
        }
        return instance;
    }

    @Override
    public void iniciar() {
        //decidir qual a precisão necessária
        // Register the listener with the Location Manager to receive location updates
        //      String locationProvider = LocationManager.NETWORK_PROVIDER;
        // Or, use GPS location data:
        String locationProvider = LocationManager.GPS_PROVIDER;
        //Quando efectivamente começar a detectar
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

    }

    @Override
    public void terminar() {
        locationManager.removeUpdates(locationListener);
    }
}
