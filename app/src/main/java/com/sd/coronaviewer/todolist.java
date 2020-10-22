package com.sd.coronaviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class todolist extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    public ProgressDialog loadingbar;

    public ImageView live,maps,todo;
    public YouTubePlayerView news1,news2,news3,news4,news5,news6,news7,news8;
    public String url1,url2,url3,url4,url5,url6,url7,url8;
    public DatabaseReference newsrefer;
    public DatabaseReference worldcounts;
    public TextView country1,country2,country3,country4,country5,country6,country7,country8,country9,country10;
    public TextView aff1,aff2,aff3,aff4,aff5,aff6,aff7,aff8,aff9,aff10;
    public TextView death1,death2,death3,death4,death5,death6,death7,death8,death9,death10;
    public TextView update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        loadingbar = new ProgressDialog(todolist.this);
        //working of loading bar
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.setMessage("Loading...");
        loadingbar.show();

        mInterstitialAd = new InterstitialAd(this);
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173");//Test
        mInterstitialAd.setAdUnitId("ca-app-pub-8277001045167500/8117627899");
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


        //update Text//

        update = findViewById(R.id.updatedcountries);

        //ICONS//
        live = findViewById(R.id.livesicon);
        maps = findViewById(R.id.mapsicon);
        todo = findViewById(R.id.todosicon);

        //YOUTUBE//
        news1 = findViewById(R.id.youtubevideo1);
        getLifecycle().addObserver(news1);
        news2 = findViewById(R.id. youtubevideo2);
        getLifecycle().addObserver(news2);
        news3 = findViewById(R.id.youtubevideo3);
        getLifecycle().addObserver(news3);
        news4 = findViewById(R.id.youtubevideo4);
        getLifecycle().addObserver(news4);
        news5 = findViewById(R.id. youtubevideo5);
        getLifecycle().addObserver(news5);
        news6 = findViewById(R.id.youtubevideo6);
        getLifecycle().addObserver(news6);
        news7 = findViewById(R.id. youtubevideo7);
        getLifecycle().addObserver(news7);
        news8 = findViewById(R.id.youtubevideo8);
        getLifecycle().addObserver(news8);

        //WORLD COUNT//

        aff1 = findViewById(R.id.County1A);
        aff2 = findViewById(R.id.County2A);
        aff3 = findViewById(R.id.County3A);
        aff4 = findViewById(R.id.County4A);
        aff5 = findViewById(R.id.County5A);
        aff6 = findViewById(R.id.County6A);
        aff7 = findViewById(R.id.County7A);
        aff8 = findViewById(R.id.County8A);
        aff9 = findViewById(R.id.County9A);
        aff10 = findViewById(R.id.County10A);

        country1 = findViewById(R.id.County1N);
        country2 = findViewById(R.id.County2N);
        country3 = findViewById(R.id.County3N);
        country4 = findViewById(R.id.County4N);
        country5 = findViewById(R.id.County5N);
        country6 = findViewById(R.id.County6N);
        country7 = findViewById(R.id.County7N);
        country8 = findViewById(R.id.County8N);
        country9 = findViewById(R.id.County9N);
        country10 = findViewById(R.id.County10N);

        death1 = findViewById(R.id.County1D);
        death2 = findViewById(R.id.County2D);
        death3 = findViewById(R.id.County3D);
        death4 = findViewById(R.id.County4D);
        death5 = findViewById(R.id.County5D);
        death6 = findViewById(R.id.County6D);
        death7 = findViewById(R.id.County7D);
        death8 = findViewById(R.id.County8D);
        death9 = findViewById(R.id.County9D);
        death10 = findViewById(R.id.County10D);


        //NEWS UPDATED TIME ZONE//


        //WORLD COUNT DATA//
        worldcounts = FirebaseDatabase.getInstance().getReference().child("Counts");
        worldcounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loadingbar.dismiss();
                aff1.setText(dataSnapshot.child("total1").getValue().toString().trim());
                country1.setText(dataSnapshot.child("country1").getValue().toString().trim());
                death1.setText(dataSnapshot.child("deaths1").getValue().toString().trim());

                aff2.setText(dataSnapshot.child("total2").getValue().toString().trim());
                country2.setText(dataSnapshot.child("country2").getValue().toString().trim());
                death2.setText(dataSnapshot.child("deaths2").getValue().toString().trim());

                aff3.setText(dataSnapshot.child("total3").getValue().toString().trim());
                country3.setText(dataSnapshot.child("country3").getValue().toString().trim());
                death3.setText(dataSnapshot.child("deaths3").getValue().toString().trim());

                aff4.setText(dataSnapshot.child("total4").getValue().toString().trim());
                country4.setText(dataSnapshot.child("country4").getValue().toString().trim());
                death4.setText(dataSnapshot.child("deaths4").getValue().toString().trim());

                aff5.setText(dataSnapshot.child("total5").getValue().toString().trim());
                country5.setText(dataSnapshot.child("country5").getValue().toString().trim());
                death5.setText(dataSnapshot.child("deaths5").getValue().toString().trim());

                aff6.setText(dataSnapshot.child("total6").getValue().toString().trim());
                country6.setText(dataSnapshot.child("country6").getValue().toString().trim());
                death6.setText(dataSnapshot.child("deaths6").getValue().toString().trim());

                aff7.setText(dataSnapshot.child("total7").getValue().toString().trim());
                country7.setText(dataSnapshot.child("country7").getValue().toString().trim());
                death7.setText(dataSnapshot.child("deaths7").getValue().toString().trim());

                aff8.setText(dataSnapshot.child("total8").getValue().toString().trim());
                country8.setText(dataSnapshot.child("country8").getValue().toString().trim());
                death8.setText(dataSnapshot.child("deaths8").getValue().toString().trim());

                aff9.setText(dataSnapshot.child("total9").getValue().toString().trim());
                country9.setText(dataSnapshot.child("country9").getValue().toString().trim());
                death9.setText(dataSnapshot.child("deaths9").getValue().toString().trim());

                aff10.setText(dataSnapshot.child("total10").getValue().toString().trim());
                country10.setText(dataSnapshot.child("country10").getValue().toString().trim());
                death10.setText(dataSnapshot.child("deaths10").getValue().toString().trim());

                update.setText(dataSnapshot.child("updated").getValue().toString().trim());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //NEWS DATA RECEIVE//
        newsrefer = FirebaseDatabase.getInstance().getReference().child("News");
        newsrefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                url1 = dataSnapshot.child("news1").getValue().toString().trim();
                url2 = dataSnapshot.child("news2").getValue().toString().trim();
                url3 = dataSnapshot.child("news3").getValue().toString().trim();
                url4 = dataSnapshot.child("news4").getValue().toString().trim();
                url5 = dataSnapshot.child("news5").getValue().toString().trim();
                url6 = dataSnapshot.child("news6").getValue().toString().trim();
                url7 = dataSnapshot.child("news7").getValue().toString().trim();
                url8 = dataSnapshot.child("news8").getValue().toString().trim();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        news1.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url1,0);
            }
        });

        news2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url2,0);
                youTubePlayer.pause();
            }
        });

        news3.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url3,0);
                youTubePlayer.pause();
            }
        });
        news4.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url4,0);
                youTubePlayer.pause();
            }
        });

        news5.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url5,0);
                youTubePlayer.pause();
            }
        });

        news6.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url6,0);
                youTubePlayer.pause();
            }
        });
        news7.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url7,0);
                youTubePlayer.pause();
            }
        });

        news8.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(url8,0);
                youTubePlayer.pause();
            }
        });


        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomePage.class));
                finish();

            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(todolist.this,"Presently in Page",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void news1getter(View view) {

        Intent new1 = new Intent(todolist.this,WebView.class);
        new1.putExtra("news1setter",url1);

    }

    public void showInterstitial(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG","Ad did not Load!!");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),HomePage.class));
    }

    public void indianlist(View view) {
        startActivity(new Intent(getApplicationContext(),IndiaList.class));
    }

    public void tnlist(View view) {
        startActivity(new Intent(getApplicationContext(),tamilnadu.class));
    }
}
