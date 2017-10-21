package com.xiansenliu.sloth

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.content.pm.PackageManager
import java.util.*

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         3:08 PM
 * Used to request permissions and get the results
 */

class PuppetFragment : Fragment() {

    private lateinit var mRequest: Request

    @SuppressLint("NewApi")
    internal fun request(request: Request) {
        setRequest(request)
        val permissions = mRequest.permissions
        val requestCode = mRequest.requestCode

        val granted = arePermissionsGranted(activity, permissions)
        if (granted) {
            onAllPermissionsGranted(requestCode, permissions)
            return
        }

        val unGrantedPermissions = shouldShowRationale(this, permissions)
        if (unGrantedPermissions.isNotEmpty()) {
            mRequest.onRational.showRationale(unGrantedPermissions) { requestPermissions(permissions.toTypedArray(), requestCode) }
        } else {
            requestPermissions(permissions.toTypedArray(), requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != mRequest.requestCode) return
        val granted = ArrayList<String>()
        val denied = ArrayList<String>()
        for (i in permissions.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(permissions[i])
            } else {
                denied.add(permissions[i])
            }
        }
        if (denied.size > 0) {
            onAnyPermissionDenied(requestCode, denied)
        } else {
            onAllPermissionsGranted(requestCode, granted)
        }
    }

    private fun onAllPermissionsGranted(requestCode: Int, permissions: List<String>) {
        mRequest.onGranted.onGranted(requestCode, permissions)
    }

    private fun onAnyPermissionDenied(requestCode: Int, permissions: List<String>) {
        mRequest.onDenied.onDenied(requestCode, permissions) { activity.startActivityForResult(createSettingIntent(activity), requestCode) }
    }

    private fun setRequest(request: Request) {
        this.mRequest = request
    }

    companion object {
        private val TAG = PuppetFragment::class.java.canonicalName
        fun create(act: Activity): PuppetFragment {
            var puppetFragment: PuppetFragment? = act.fragmentManager.findFragmentByTag(TAG) as PuppetFragment?
            if (puppetFragment == null) {
                puppetFragment = PuppetFragment()
                act.fragmentManager.beginTransaction()
                        .add(puppetFragment, TAG)
                        .commitAllowingStateLoss()
                act.fragmentManager.executePendingTransactions()
            }
            return puppetFragment
        }

    }
}
