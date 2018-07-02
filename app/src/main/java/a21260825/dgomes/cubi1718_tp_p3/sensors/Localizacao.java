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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.TreeMap;

import a21260825.dgomes.cubi1718_tp_p3.models.Registo;
import a21260825.dgomes.cubi1718_tp_p3.utils.Config;

/**
 * Created by diogo on 14-04-2018.
 */

public class Localizacao extends CubiSensor {
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static Localizacao instance;
    private String locationProvider;

    private Activity activity;

    public static Localizacao getInstance(Activity activity) {
        if(instance == null) {
            instance = new Localizacao(activity);
        }
        return instance;
    }

    protected Localizacao(Activity activity) {
        this.activity = activity;
        registo = Registo.getInstance();

       // valores = new TreeMap<String, String>();

        locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);

        Location location = escolheLocationProvider();
        preparaLocalizacao();
/*
        valores.put(Config.LATITUDE,"");
        valores.put(Config.LONGITUDE,"");
        valores.put(Config.ALTITUDE,"");
*/


        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                atualizaLocalizacao(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
    }

    private Location escolheLocationProvider() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "Sem permissoes para Location!",Toast.LENGTH_LONG).show();
        }else{
            String locationGps = LocationManager.GPS_PROVIDER;
            String locationNet = LocationManager.NETWORK_PROVIDER;
            Location lastKnownLocationGps = locationManager.getLastKnownLocation(locationGps);
            Location lastKnownLocationNet = locationManager.getLastKnownLocation(locationNet);
            if (lastKnownLocationGps==null && lastKnownLocationNet==null){
                this.locationProvider = locationNet;
                return null;
            }
            if (lastKnownLocationGps==null){
                this.locationProvider = locationNet;
                return lastKnownLocationNet;
            }
            if (lastKnownLocationNet==null){
                this.locationProvider = locationGps;
                return lastKnownLocationGps;
            }
            if (isBetterLocation(lastKnownLocationGps, lastKnownLocationNet)){
                this.locationProvider = locationGps;
                return lastKnownLocationGps;
            }else{
                this.locationProvider = locationNet;
                return lastKnownLocationNet;
            }
        }
        return null;

    }
    private void preparaLocalizacao(){

        valores.put(Config.LATITUDE,0.0f);
        valores.put(Config.LONGITUDE,0.0f);
        valores.put(Config.ALTITUDE,0.0f);

    }
    private void atualizaLocalizacao(Location location){
        escolheLocationProvider();
        tv.setText("Local ("+ this.locationProvider +"): " + location.getLatitude()+","+location.getLongitude()
                +","+location.getAltitude());
        valores.put(Config.LATITUDE,(float) location.getLatitude());
        valores.put(Config.LONGITUDE,(float) location.getLongitude());
        valores.put(Config.ALTITUDE,(float) location.getAltitude());
        registo.addValores(valores);
    }

    @Override
    public void iniciar() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "Sem permissoes para Location!",Toast.LENGTH_LONG).show();
        }else{
            locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        }

    }

    @Override
    public void terminar() {
        locationManager.removeUpdates(locationListener);
    }

    /** Determines whether one Location reading is better than the current Location fix
     * @param location  The new Location that you want to evaluate
     * @param currentBestLocation  The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }


}
