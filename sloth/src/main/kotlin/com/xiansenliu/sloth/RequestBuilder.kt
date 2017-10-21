package com.xiansenliu.sloth

import android.app.Activity

/**
 * Author       xinliu
 * Date         10/21/17
 * Time         9:07 PM
 */
class RequestBuilder(internal val act: Activity) {
    private var callbackSet = false
    internal val permissions = mutableListOf<String>()
    internal lateinit var onGranted: OnGrantedCallback
    internal lateinit var onDenied: OnDeniedCallback
    internal lateinit var onRationale: RationaleCallback
    internal var requestCode: Int = 0

    fun code(requestCode: Int): RequestBuilder {
        this.requestCode = requestCode
        return this
    }

    fun callback(onRationale: RationaleCallback = DEFAULT_ON_RATIONALE,
                 onGranted: OnGrantedCallback = DEFAULT_ON_GRANTED,
                 onDenied: OnDeniedCallback = DEFAULT_ON_DENIED): RequestBuilder {
        this.onRationale = onRationale
        this.onGranted = onGranted
        this.onDenied = onDenied
        callbackSet = true
        return this
    }

    fun commit() {
        if (callbackSet) {
            Request(this)
        } else {
            throw IllegalStateException("callback() has never been call,please call it at least once")
        }
    }

    fun request(vararg permissions: String): RequestBuilder = request(listOf(*permissions))

    fun request(permissions: List<String>): RequestBuilder {
        this.permissions.clear()
        this.permissions.addAll(permissions)
        return this
    }

    companion object {
        val DEFAULT_ON_GRANTED: OnGrantedCallback = OnGrantedCallback { _, _ -> }
        val DEFAULT_ON_RATIONALE: RationaleCallback = RationaleCallback { _, requestAction -> requestAction.invoke() }
        val DEFAULT_ON_DENIED: OnDeniedCallback = OnDeniedCallback { _, _, _ -> }
    }
}