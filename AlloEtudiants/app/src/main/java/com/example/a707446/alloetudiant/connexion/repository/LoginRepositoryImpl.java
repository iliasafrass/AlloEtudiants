package com.example.a707446.alloetudiant.connexion.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.a707446.alloetudiant.connexion.presenter.ConnexionContract;
import com.example.a707446.alloetudiant.connexion.webservice.LoginWebService;
import com.example.a707446.alloetudiant.general.webservice.RetrofitClientInstance;
import com.example.a707446.alloetudiant.general.webservice.ServiceGenerator;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionPresenter;
import com.example.a707446.alloetudiant.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.model.pojo.Event;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepositoryImpl implements LoginRepository{

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<Void> login(LoginRequest loginRequest) {
        return retrofit.create(LoginWebService.class)
                .login(loginRequest);
    }

    @Override
    public Call<String> getProfileIdByEmail(String email) {
        return retrofit.create(LoginWebService.class)
                .getProfileIdByEmail(email);
    }


}
