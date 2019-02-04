package com.example.a707446.alloetudiant.start;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.Login;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.splash.Splash;

import java.util.Date;

public class StartActivity extends Activity {

    SharedPreferences preferences;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        preferences = getSharedPreferences("token",MODE_PRIVATE);
        token = preferences.getString("token","");


        // Need to check if token is valid or not
        if(!token.isEmpty()){
            JWT jwt = new JWT(token);
            Date expiresAt = jwt.getExpiresAt();
            if(expiresAt.after(new Date())){
                Toast.makeText(getApplicationContext(),expiresAt.toString(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(),"token n'est plus valable",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),Splash.class);
                startActivity(i);
            }
        } else {
            Toast.makeText(getApplicationContext(),"token n'existe pas",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),Splash.class);
            startActivity(i);
        }

        finish();

    }
}
