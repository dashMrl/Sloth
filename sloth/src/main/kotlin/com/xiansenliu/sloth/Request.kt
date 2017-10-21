package com.xiansenliu.sloth

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         7:44 PM
 */

internal class Request constructor(builder: RequestBuilder) {
    internal val act = builder.act
    internal val permissions = builder.permissions
    internal val onGranted = builder.onGranted
    internal val onDenied = builder.onDenied
    internal val onRational = builder.onRationale
    internal val requestCode = builder.requestCode

    init {
        launch(this)
    }

    companion object {
        private fun launch(request: Request) {
            val fragment = PuppetFragment.create(request.act)
            fragment.request(request)
        }
    }
}
