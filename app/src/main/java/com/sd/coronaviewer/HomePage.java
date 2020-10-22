package com.sd.coronaviewer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class HomePage extends AppCompatActivity{

    InterstitialAd mInterstitialAd;
    public ProgressDialog loadingbar;
    public ImageView home,maps,todo;
    public TextView total,deaths,recovered,ncondition,ccondtion,recov,dea,update;
    public DatabaseReference counts;
    public int CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        loadingbar = new ProgressDialog(HomePage.this);
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.setMessage("Loading...");
        loadingbar.show();

        mInterstitialAd = new InterstitialAd(this);
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173");//Test
        mInterstitialAd.setAdUnitId("ca-app-pub-8277001045167500/7040242148");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                showInterstitial();
            }
        },35000);

        final String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};


        home = findViewById(R.id.liveicon);
        maps = findViewById(R.id.mapicon);
        todo = findViewById(R.id.todoicon);
        total = findViewById(R.id.corondisp1);
        deaths = findViewById(R.id.corona_deaths1);
        recovered = findViewById(R.id.corona_recov1);
        ncondition = findViewById(R.id.corona_ncond1);
        ccondtion = findViewById(R.id.corona_ccond1);
        recov = findViewById(R.id.coronarecovered);
        dea = findViewById(R.id.coronadeaths2);
        update = findViewById(R.id.update);

        counts = FirebaseDatabase.getInstance().getReference().child("Counts");

        counts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loadingbar.dismiss();
                total.setText(dataSnapshot.child("total").getValue().toString().trim());
                deaths.setText(dataSnapshot.child("deaths").getValue().toString().trim());
                recovered.setText(dataSnapshot.child("recov").getValue().toString().trim());
                ncondition.setText(dataSnapshot.child("ncond").getValue().toString().trim());
                ccondtion.setText(dataSnapshot.child("ccond").getValue().toString().trim());
                recov.setText(dataSnapshot.child("recov").getValue().toString().trim());
                dea.setText(dataSnapshot.child("deaths").getValue().toString().trim());
                update.setText(dataSnapshot.child("updated").getValue().toString().trim());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomePage.this,"Presently in Page",Toast.LENGTH_SHORT).show();
            }
        });


        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(HomePage.this,"Permissions Granted",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(HomePage.this, "Permissions Denied", Toast.LENGTH_SHORT).show();

                    }
                };
                TedPermission.with(HomePage.this).setPermissionListener(permissionListener).setPermissions(permission).check();
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                startActivity(new Intent(getApplicationContext(),todolist.class));
            }
        });

    }

    public void showInterstitial(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.i("TAG","Ad did not Load Properly...");
        }
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    public void indialist(View view) {
        startActivity(new Intent(getApplicationContext(),IndiaList.class));

    }
}
