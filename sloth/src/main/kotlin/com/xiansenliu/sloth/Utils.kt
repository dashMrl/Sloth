package com.xiansenliu.sloth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.ActivityCompat

/**
 * Author       xinliu
 * Date         5/7/17
 * Time         10:13 PM
 */


private val isBelowMarshmallow: Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.M


internal fun createSettingIntent(context: Context): Intent {
    val intent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS")
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    return intent
}


fun arePermissionsGranted(context: Context, permissions: List<String>): Boolean {
    if (isBelowMarshmallow) return true
    permissions.forEach {
        val granted = isPermissionGranted(context, it)
        if (!granted) {
            return false
        }
    }
    return true
}

private fun isPermissionGranted(context: Context, permission: String): Boolean {
    if (isBelowMarshmallow) {
        return true
    }
    val result = ActivityCompat.checkSelfPermission(context, permission)
    return result == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("NewApi")
internal fun shouldShowRationale(fragment: android.app.Fragment, permissions: List<String>): List<String> {
    val unGrantedPermissions = ArrayList<String>()
    for (permission in permissions) {
        val should = fragment.shouldShowRequestPermissionRationale(permission)
        if (should) {
            unGrantedPermissions.add(permission)
        }
    }
    return unGrantedPermissions
}

