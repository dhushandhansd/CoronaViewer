package com.sd.coronaviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.scwang.wave.MultiWaveHeader;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MultiWaveHeader header1,footer1;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header1 = findViewById(R.id.headerwave);
        footer1 = findViewById(R.id.footerwave);

        header1.setVelocity(1);
        header1.setProgress(1);
        header1.isRunning();
        header1.setGradientAngle(45);
        header1.setWaveHeight(40);
        header1.setStartColor(Color.RED);
        header1.setCloseColor(Color.BLACK);


        footer1.setVelocity(1);
        footer1.setProgress(1);
        footer1.isRunning();
        footer1.setGradientAngle(45);
        footer1.setWaveHeight(40);
        footer1.setStartColor(Color.BLACK);
        footer1.setCloseColor(Color.RED);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),HomePage.class));
                finish();
            }
        },3000);
    }
}
