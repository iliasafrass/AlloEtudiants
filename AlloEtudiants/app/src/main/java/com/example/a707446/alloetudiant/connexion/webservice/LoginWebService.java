package com.example.a707446.alloetudiant.connexion.webservice;

import com.example.a707446.alloetudiant.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.model.pojo.Event;
import com.example.a707446.alloetudiant.model.pojo.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
