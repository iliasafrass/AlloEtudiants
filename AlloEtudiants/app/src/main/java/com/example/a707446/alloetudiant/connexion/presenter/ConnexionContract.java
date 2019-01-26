package com.example.a707446.alloetudiant.connexion.presenter;

import android.arch.lifecycle.LiveData;

import com.example.a707446.alloetudiant.model.pojo.Event;
import com.example.a707446.alloetudiant.model.pojo.Profile;

import java.util.List;

public interface ConnexionContract {
    interface View {
        void login(String message);
        void signup();
        void forgetPassword();
    }

    interface Presenter  {

        void startLogin(String email, String password);
        void startSignup();
        void startForgetPassword();
    }
}
