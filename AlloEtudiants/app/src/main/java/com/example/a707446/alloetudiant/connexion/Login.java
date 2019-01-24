package com.example.a707446.alloetudiant.connexion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.presenter.ConnexionContract;
import com.example.a707446.alloetudiant.connexion.presenter.ConnexionPresenter;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.inscription.Inscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Login extends AppCompatActivity implements ConnexionContract.View {


    //Views
    @BindView(R.id.Email)
    public EditText emailEditText;
    @BindView(R.id.password)
    public EditText passwordEditText;

    // Globals
    private ConnexionContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUnbinder = ButterKnife.bind(this);
        mPresenter = new ConnexionPresenter(this);
    }

    @OnClick(R.id.signup)
    public void onSignupClick(){mPresenter.startSignup();}

    @OnClick(R.id.login)
    public void onLoginClick(){mPresenter.startLogin();}

    @OnClick(R.id.forgetPassword)
    public void  onForgetPasswordClick(){mPresenter.startForgetPassword();}

    @Override
    public void signup(){
        Toast.makeText(getApplication(), "Inscription", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),Inscription.class);
        startActivity(i);
    }

    @Override
    public void forgetPassword() {
        Toast.makeText(getApplication(), "mot de passe oublié !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void login(){
        Toast.makeText(getApplication(), "Bienvenu :)", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
        startActivity(i);
    }

    private void mydialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Attention!")
                //set message
                .setMessage("Etes-vous sûr de vouloir quitter l'application ?")
                //set positive button
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        Toast.makeText(getApplicationContext(),"Au revoir!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                //set negative button
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext()," :) ",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :
                mydialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
