package com.example.a707446.alloetudiant.splash;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.Login;

public class Splash extends AppCompatActivity {

    TextView animatedTextView1;
    TextView animatedTextView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        animatedTextView1 = findViewById(R.id.text1);
        animatedTextView2 = findViewById(R.id.text2);

        startAnimation();

        Handler handler = new Handler();

        final Intent i = new Intent(getApplicationContext(),Login.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 4000);

    }
    private void startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim2);

        animatedTextView1.startAnimation(animation);
        animatedTextView2.startAnimation(animation2);
    }

}
