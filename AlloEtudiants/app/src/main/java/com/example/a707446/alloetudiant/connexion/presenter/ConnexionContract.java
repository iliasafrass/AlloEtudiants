package com.example.a707446.alloetudiant.connexion.presenter;

public interface ConnexionContract {
    interface View {
        void login();
        //void login(String message);
        void signup();
        void forgetPassword();
    }

    interface Presenter  {
        void startLogin();
        //void startLogin(String email,String password);
        void startSignup();
        void startForgetPassword();
    }
}
