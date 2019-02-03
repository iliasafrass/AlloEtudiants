package com.example.a707446.alloetudiant.connexion.repository;

import android.arch.lifecycle.LiveData;

import com.example.a707446.alloetudiant.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.model.pojo.Event;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;

public interface LoginRepository {

    Call<Void> login(LoginRequest loginRequest);

    Call<String> getProfileIdByEmail(String email);
}
