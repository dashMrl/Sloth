package com.xiansenliu.sloth;

import java.util.List;

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         3:17 PM
 */

public interface OnGrantedCallback {

    void onGranted(int requestCode, List<String> grantedPermissions);


}