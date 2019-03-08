package com.example.a707446.alloetudiant.connexion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.presenter.ConnexionContract;
import com.example.a707446.alloetudiant.connexion.presenter.ConnexionPresenter;
import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.inscription.Inscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Login extends AppCompatActivity implements ConnexionContract.View {

    // Globals
    private ConnexionContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUnbinder = ButterKnife.bind(this);
        mPresenter = new ConnexionPresenter(this);

        SharedPreferencesSingleton.setToken(BaseApplication.getAppContext(),"");

    }

    @BindView(R.id.Email)
    EditText edtEmail;

    @BindView(R.id.password)
    EditText edtPassword;

    @OnClick(R.id.signup)
    public void onSignupClick(){mPresenter.startSignup();}

    @OnClick(R.id.login)
    public void onLoginClick(){
//        Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
//        startActivity(i);
        mPresenter.startLogin(edtEmail.getText().toString(),edtPassword.getText().toString());
    }

    @OnClick(R.id.forgetPassword)
    public void  onForgetPasswordClick(){mPresenter.startForgetPassword();}

    @Override
    public void signup(){
//        Toast.makeText(getApplication(), "Inscription", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),Inscription.class);
        startActivity(i);
    }

    @Override
    public void forgetPassword() {
//        Toast.makeText(getApplication(), "mot de passe oublié !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        errorSnackbar(error);
    }

    @Override
    public Context applicationContext() {
        return getApplicationContext();
    }

    @Override
    public void login(String token, String profileId){
        Toast.makeText(getApplication(), "Bienvenu "+profileId, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
        startActivity(i);
    }

    private void mydialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle(R.string.warning)
                //set message
                .setMessage(R.string.quitApplication)
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

    private void errorSnackbar(String error){
        TSnackbar errorSnackBar = TSnackbar.make(findViewById(android.R.id.content),error,TSnackbar.LENGTH_LONG);
        errorSnackBar.setActionTextColor(ContextCompat.getColor(getApplicationContext(),R.color.errorSnackBarText));
        View snackBarView = errorSnackBar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackBarView.getLayoutParams();
        params.height = 80;
        snackBarView.setPadding(0,-20,0,-15);
        snackBarView.setLayoutParams(params);
        snackBarView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.errorSnackBarBackground));
        TextView textView = (TextView) snackBarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.errorSnackBarText));
        textView.setTextSize(12);
        textView.setGravity(Gravity.CENTER);
        errorSnackBar.show();
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
