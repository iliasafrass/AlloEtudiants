package com.example.a707446.alloetudiant.connexion.presenter;

import android.content.Context;

public interface ConnexionContract {
    interface View {
        void login(String token, String profileId);

        void signup();

        void forgetPassword();

        void toast(String message);

        void showError(String error);

        Context applicationContext();

        void endLogin();
    }

    interface Presenter {
        void startLogin(String email, String password);

        void startSignup();

        void startForgetPassword();
    }
}
