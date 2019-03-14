package com.example.a707446.alloetudiant.general.repository.connexion;

import com.example.a707446.alloetudiant.general.model.payload.LoginRequest;

import retrofit2.Call;

public interface LoginRepository {

    Call<Void> login(LoginRequest loginRequest);

    Call<String> getProfileIdByEmail(String email);
}
