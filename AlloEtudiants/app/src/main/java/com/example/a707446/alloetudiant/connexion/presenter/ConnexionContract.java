package com.example.a707446.alloetudiant.connexion.presenter;

public interface ConnexionContract {
    interface View {
        void login(String token, String profileId);
        void signup();
        void forgetPassword();
        void toast(String message);
        void showError(String error);
    }

    interface Presenter  {

        void startLogin(String email, String password);
        void startSignup();
        void startForgetPassword();
    }
}
