package com.example.a707446.alloetudiant.general.services.connexion;

import com.example.a707446.alloetudiant.general.model.payload.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginWebService {

    @POST("/login")
    Call<Void> login(
            @Body LoginRequest loginRequest
    );

    @GET("/profiles/id/{email}")
    Call<String> getProfileIdByEmail(@Path("email") String email);
}
