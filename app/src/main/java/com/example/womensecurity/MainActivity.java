package com.example.womensecurity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.womensecurity.mDataBase.DBAdapter;
import com.example.womensecurity.mDataObject.Spacecraft;
import com.example.womensecurity.mService.GpsTracker;
import com.google.android.material.navigation.NavigationView;
import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mNavDrawer;

    private ImageView btn_send_sms;

    final int SEND_SMS_PERMISSION_REQUESTCODE = 1;

    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();

    GpsTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavDrawer=findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,mNavDrawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        mNavDrawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        btn_send_sms=findViewById(R.id.btn_sos);

        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_permission(v);
            }
        });

    }



    private void check_permission(View v) {
        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            onSend(v);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUESTCODE);
        }
    }

    @Override
    public void onBackPressed() {

        if(mNavDrawer.isDrawerOpen(GravityCompat.START)){
            mNavDrawer.closeDrawer(GravityCompat.START);
        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.Exit));
            builder.setMessage(getString(R.string.exit_message));

            builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.show();

        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.tips1:
                Intent intent=new Intent(MainActivity.this, Tips1Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tips2:
                intent=new Intent(MainActivity.this, Tips2Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.laws:
                intent=new Intent(MainActivity.this, LawsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.videos:
                intent=new Intent(MainActivity.this, VideosActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=hi");
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,"Share via"));
                break;
            case R.id.about:
                showAboutDialog();
                break;
            case R.id.rate:
                Toast.makeText(this,"This is Rate Item",Toast.LENGTH_SHORT).show();
                break;
        }

        mNavDrawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id == R.id.om_about){
            showAboutDialog();
        }
        if(id == R.id.om_rate){

        }
        if(id == R.id.om_exit){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.Exit));
            builder.setMessage(getString(R.string.exit_message));

            builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.show();
        }

        return super.onOptionsItemSelected(item);

    }

    public void open_number_activity(View v)
    {

        Intent intent=new Intent(MainActivity.this, NumbersActivity.class);
        startActivity(intent);
        finish();

    }

    public void onSend(View v)
    {
        gpsTracker = new GpsTracker(MainActivity.this);

        String location = "";

        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            location = "Location: http://www.google.com/maps/place/" + (latitude) + "," + (longitude);
        } else {
            location = "Location not defined!";
            gpsTracker.showSettingsAlert();
        }

        String smsMessage = "I am in trouble! Help me.. " + location;
        spacecrafts.clear();
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        Cursor c=db.retrieve();

        while (c.moveToNext())
        {
            String number=c.getString(2);
            if(checkPermission(Manifest.permission.SEND_SMS)){
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number,null,smsMessage,null,null);
                Toast.makeText(this,"Message Sending on:"+number,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();
            }
        }

        db.closeDB();
    }

    public boolean checkPermission(String permission)
    {
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    // Alert Dialog with custom view
    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About the app");
        builder.setView(R.layout.about_dialog);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // dismiss dialog
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}