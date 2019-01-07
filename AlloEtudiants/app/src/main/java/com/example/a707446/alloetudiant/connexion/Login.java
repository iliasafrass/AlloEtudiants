package com.example.a707446.alloetudiant.connexion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    /*@BindView(R.id.login)
    public Button btnLogin;
    @BindView(R.id.signup)
    public Button btnSignup;
*/

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
        Toast.makeText(getApplication(), "Login", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
        startActivity(i);
    }

}
