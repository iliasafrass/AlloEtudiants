package com.example.a707446.alloetudiant.general.services;

import com.example.a707446.alloetudiant.general.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Request;

import java.lang.ref.Reference;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlloEtudiantRestClient {

    @POST("/login")
    Call<Void> login(
            @Body LoginRequest loginRequest
    );

    @GET("/events")
    Call<List<Event>> getEvents();


    @GET("/offers")
    Call<List<Offer>> getOffers();


    @GET("/requests")
    Call<List<Request>> getRequests();
}
