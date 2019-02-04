package com.example.a707446.alloetudiant.splash;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.Login;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        final Intent i = new Intent(getApplicationContext(),Login.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 1000);
    }

}
