package com.xiansenliu.sloth_sample

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.xiansenliu.sloth.Action
import com.xiansenliu.sloth.OnDeniedCallback
import com.xiansenliu.sloth.OnGrantedCallback

import com.xiansenliu.sloth.PuppetFragment
import com.xiansenliu.sloth.RationaleCallback
import com.xiansenliu.sloth.Sloth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { v ->
            Sloth.with(this)
                    .code(2333)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
                    .callback(
                            RationaleCallback { permissions, requestAction ->
                                Toast.makeText(this, "show rationale", Toast.LENGTH_SHORT).show()
                                AlertDialog.Builder(this@MainActivity)
                                        .setTitle("The reason for permissions")
                                        .setMessage("need some permissions to ensure that the app will run properly")
                                        .setPositiveButton("OK") { dialogInterface, i -> requestAction.invoke() }
                                        .show()
                            },
                            OnGrantedCallback { requestCode, grantedPermissions ->
                                Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show()
                            },
                            OnDeniedCallback { requestCode, deniedPermissions, goSettingAction ->
                                Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()
                                AlertDialog.Builder(this@MainActivity)
                                        .setTitle("Request was denied")
                                        .setMessage("go to the app detail page and grant the permissions manually")
                                        .setPositiveButton("Go") { dialogInterface, i -> goSettingAction.invoke() }
                                        .show()
                            })
                    .commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2333) {
            Log.d(TAG, "onActivityResult: back from settings，Request Code = " + requestCode)
        }
    }


    companion object {

        private val TAG = "MainActivity"
    }

}
