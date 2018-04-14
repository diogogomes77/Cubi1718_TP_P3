package a21260825.dgomes.cubi1718_tp_p3.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by diogo on 14-04-2018.
 */

public class Permissoes {

    private static Permissoes instance = null;

    private Activity activity;
    private TextView tvLog;

    private boolean permStorage, permCoarseLocation, permFineLocation;

    protected Permissoes(Activity activity, TextView tvLog) {
        this.activity = activity;
        this.tvLog = tvLog;
    }

    public static Permissoes getInstance(Activity activity, TextView tvLog) {
        if(instance == null) {
            instance = new Permissoes(activity, tvLog);
        }
        return instance;
    }


    public boolean temPermissoes() {
        String[] permissions =
                {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
        if (hasPermissions(activity, permissions)) {
            //Toast.makeText(activity, "Nice! Permissões completas", Toast.LENGTH_LONG).show();
            //prepararRecolha();
            return true;
        } else {
            ActivityCompat.requestPermissions(activity, permissions, Config.MY_PERMMISSIONS_ALL);
            //btRecolha.setEnabled(false);
            return false;

        }
    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    applyVarPerm(permission, false);
                    return false;
                }
                applyVarPerm(permission, true);
            }
        }
        return true;
    }

    private void applyVarPerm(String permission, boolean b) {
        if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permStorage = b;
            tvLog.append("permStorage=" + b + "\n");
        }
        if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permCoarseLocation = b;
            tvLog.append("permCoarseLocation=" + b + "\n");
        }
        if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permFineLocation = b;
            tvLog.append("permFineLocation=" + b + "\n");
        }
    }


}
