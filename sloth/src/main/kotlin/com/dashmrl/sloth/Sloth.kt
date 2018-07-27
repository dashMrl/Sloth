package com.dashmrl.sloth

import android.support.annotation.MainThread
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Author       xinliu
 * Date         4/29/17
 * Time         6:01 PM
 */

class Sloth {
    companion object {
        @MainThread
        fun with(frag: Fragment): RequestBuilder = with(frag.requireActivity())

        @MainThread
        fun with(act: FragmentActivity): RequestBuilder = RequestBuilder(act)
    }


}
