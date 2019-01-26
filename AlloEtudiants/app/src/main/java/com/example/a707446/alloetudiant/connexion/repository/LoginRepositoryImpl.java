package com.example.a707446.alloetudiant.connexion.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.a707446.alloetudiant.connexion.webservice.LoginWebService;
import com.example.a707446.alloetudiant.general.webservice.RetrofitClientInstance;
import com.example.a707446.alloetudiant.general.webservice.ServiceGenerator;
import com.example.a707446.alloetudiant.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.model.pojo.Event;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepositoryImpl implements LoginRepository{

    private LoginWebService loginWS = RetrofitClientInstance.getRetrofitInstance().create(LoginWebService.class);


    @Override
    public void login(LoginRequest loginRequest) {

    }

    @Override
    public Call<List<Event>> getEvents() {
        return null;
    }
}
