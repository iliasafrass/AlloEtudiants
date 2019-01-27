package com.example.a707446.alloetudiant.connexion.presenter;

public class ConnexionPresenter implements ConnexionContract.Presenter {
    // Constants
    private static final String TAG = ConnexionPresenter.class.getSimpleName();

    // Globals
    private ConnexionContract.View mView;

    public ConnexionPresenter(ConnexionContract.View view) {
        mView = view;
    }

    @Override
    public void startLogin() {
        mView.login();
    }

    @Override
    public void startSignup() {
        mView.signup();
    }

    @Override
    public void startForgetPassword(){mView.forgetPassword();}

}
