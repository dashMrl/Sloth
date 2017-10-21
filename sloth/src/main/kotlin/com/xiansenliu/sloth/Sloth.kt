package com.xiansenliu.sloth

import android.app.Activity
import android.app.Fragment
import android.support.annotation.MainThread

/**
 * Author       xinliu
 * Date         4/29/17
 * Time         6:01 PM
 */

class Sloth {
    companion object {
        @MainThread
        fun with(frag: Fragment): RequestBuilder = with(frag.activity)

        @MainThread
        fun with(frag: android.support.v4.app.Fragment): RequestBuilder = with(frag.activity)

        @MainThread
        fun with(act: Activity): RequestBuilder = RequestBuilder(act)
    }


}
