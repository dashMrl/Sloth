package com.xiansenliu.sloth;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         3:08 PM
 * Used to request permissions and get the results
 */

public final class PuppetFragment extends Fragment {
    public static final int GO_SETTING_CODE = 9999;
    private Request mRequest;

    @SuppressLint("NewApi")
    public void request(Request request) {
        setRequest(request);
        String[] permissions = mRequest.getPermissions();
        int requestCode = mRequest.getRequestCode();

        boolean granted = Utils.arePermissionsGranted(getActivity(), permissions);
        if (granted) {
            onAllPermissionsGranted(requestCode, Arrays.asList(permissions));
            return;
        }

        List<String> unGrantedPermissions = Utils.shouldShowRationale(this, permissions);
        if (unGrantedPermissions.size() > 0 && mRequest.getRationalCallback() != null) {
            mRequest.getRationalCallback().showRational(unGrantedPermissions, () -> requestPermissions(permissions, requestCode));
        } else {
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != mRequest.getRequestCode()) return;
        if (mRequest.getGrantedCallback() == null && mRequest.getDeniedCallback() == null) return;
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(permissions[i]);
            } else {
                denied.add(permissions[i]);
            }
        }
        if (denied.size() > 0) {
            onAnyPermissionDenied(requestCode, denied);
        } else {
            onAllPermissionsGranted(requestCode, granted);
        }
    }

    private void onAllPermissionsGranted(int requestCode, List<String> permissions) {
        if (mRequest.getGrantedCallback() == null) return;
        mRequest.getGrantedCallback().onGranted(requestCode, permissions);
    }

    private void onAnyPermissionDenied(int requestCode, List<String> permissions) {
        if (mRequest.getDeniedCallback() == null) return;
        mRequest.getDeniedCallback().onDenied(requestCode, permissions, () ->
                getActivity().startActivityForResult(Utils.createSettingIntent(getActivity()), GO_SETTING_CODE));
    }

    private void setRequest(Request request) {
        this.mRequest = request;

    }
}
