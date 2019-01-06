package com.example.a707446.alloetudiant.splash;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.Login;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Thread chrono = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                }
            }

        };
        chrono.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
