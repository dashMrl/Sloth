package com.xiansenliu.sloth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       xinliu
 * Date         5/7/17
 * Time         10:13 PM
 */

public final class Utils {
    private Utils() {
    }

    public static Intent createSettingIntent(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        Uri uri = Uri.fromParts("package", context.getPackageName(),  null);
        intent.setData(uri);
        return intent;
    }


    public static boolean arePermissionsGranted(Context context, String... permissions) {
        if (isBelowMarshmallow()) {
            return true;
        }
        for (String permission : permissions) {
            boolean granted = isPermissionGranted(context, permission);
            if (!granted) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        if (isBelowMarshmallow()) {
            return true;
        }
        int result = ActivityCompat.checkSelfPermission(context, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @SuppressLint("NewApi")
    public static List<String> shouldShowRationale(android.app.Fragment fragment, String... permissions) {
        List<String> unGrantedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            boolean should = fragment.shouldShowRequestPermissionRationale(permission);
            if (should) {
                unGrantedPermissions.add(permission);
            }
        }
        return unGrantedPermissions;
    }


    static boolean isBelowMarshmallow() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

    static <T> T checkNUll(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    private static final String TAG = "Utils";

    public static PuppetFragment getPuppetFragment(Activity activity) {
        PuppetFragment puppetFragment = findRxPermissionsFragment(activity);
        boolean isNewInstance = puppetFragment == null;
        if (isNewInstance) {
            puppetFragment = new PuppetFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(puppetFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return puppetFragment;
    }

    private static PuppetFragment findRxPermissionsFragment(Activity activity) {
        return (PuppetFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

}
