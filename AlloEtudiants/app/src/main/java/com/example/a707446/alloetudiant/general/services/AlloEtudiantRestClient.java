package com.example.a707446.alloetudiant.general.services;

import com.example.a707446.alloetudiant.general.enumeration.Subject;
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
import retrofit2.http.Path;

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

    @GET("/events/{id}")
    Call<Event> getEventById(@Path("id")String id);

    @GET("/requests/{id}")
    Call<Request> getRequestById(@Path("id")String id);

    @GET("/offers/{id}")
    Call<Offer> getOfferById(@Path("id")String id);

    @GET("/requests/subjects/{subject}")
    Call<List<Request>> getRequestsBySubject(@Path("subject") Subject subject);

    @GET("/offers/subjects/{subject}")
    Call<List<Offer>> getOffersBySubject(@Path("subject")Subject subject);

    @GET("/events/title/{title}")
    Call<List<Event>> getEventsByTitle(@Path("title")String title);
}
