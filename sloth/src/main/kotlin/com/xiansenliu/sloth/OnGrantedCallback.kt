package com.xiansenliu.sloth

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         3:17 PM
 */

interface OnGrantedCallback {

    fun onGranted(requestCode: Int, grantedPermissions: List<String>)
}

internal val DEFAULT_ON_GRANTED = object : OnGrantedCallback {
    override fun onGranted(requestCode: Int, grantedPermissions: List<String>) {

    }
}