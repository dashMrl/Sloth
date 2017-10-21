package com.xiansenliu.sloth

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         3:53 PM
 */

interface RationaleCallback {
    fun showRationale(permissions: List<String>, requestAction: Action)
}

internal val DEFAULT_ON_RATIONALE = object : RationaleCallback {
    override fun showRationale(permissions: List<String>, requestAction: Action) {
        requestAction.invoke()
    }

}
