package com.dashmrl.sloth

/**
 * Author       xinliu
 * Date         7/27/18
 * Time         18:55 PM
 */
typealias Action = () -> Unit

typealias OnDeniedCallback = (requestCode: Int, deniedPermissions: List<String>, goSettingAction: Action) -> Unit

typealias OnGrantedCallback = (requestCode: Int, grantedPermissions: List<String>) -> Unit

typealias RationaleCallback = (permissions: List<String>, requestAction: Action) -> Unit

