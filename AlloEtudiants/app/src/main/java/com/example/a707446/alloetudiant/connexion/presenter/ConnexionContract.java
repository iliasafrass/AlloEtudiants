package com.example.a707446.alloetudiant.connexion.presenter;

public interface ConnexionContract {
    interface View {
        void login();
        void signup();
        void forgetPassword();
    }

    interface Presenter  {
        void startLogin();
        void startSignup();
        void startForgetPassword();
    }
}
