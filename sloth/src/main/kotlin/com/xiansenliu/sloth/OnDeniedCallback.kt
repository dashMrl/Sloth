package com.xiansenliu.sloth

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         3:17 PM
 */

interface OnDeniedCallback {

    fun onDenied(requestCode: Int, deniedPermissions: List<String>, goSettingAction: Action)

}

internal val DEFAULT_ON_DENIED = object : OnDeniedCallback {
    override fun onDenied(requestCode: Int, deniedPermissions: List<String>, goSettingAction: Action) {
        goSettingAction.invoke()
    }
}

