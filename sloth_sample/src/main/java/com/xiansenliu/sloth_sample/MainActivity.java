package com.xiansenliu.sloth_sample;

import android.Manifest;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xiansenliu.sloth.Sloth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v ->
                Sloth
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
                        .requestCode(2333)
                        .onRational((permissions, requestAction) -> {
                            Toast.makeText(this, "show rationale", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("The reason for permissions")
                                    .setMessage("need some permissions to ensure that the app will run properly")
                                    .setPositiveButton("OK", (dialogInterface, i) -> requestAction.invoke())
                                    .show();
                        })
                        .afterGranted((requestCode, permissions) -> Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show())
                        .afterDenied(((requestCode, permissions, goSettingAction) -> {
                            Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Request was denied")
                                    .setMessage("go to the app detail page and grant the permissions manually")
                                    .setPositiveButton("Go", (dialogInterface, i) -> goSettingAction.invoke())
                                    .show();
                        }))
                        .commit(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
