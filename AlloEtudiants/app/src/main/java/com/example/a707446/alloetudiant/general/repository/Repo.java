package com.example.a707446.alloetudiant.general.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Request;

import java.util.List;
import retrofit2.Call;

public interface Repo {
    Call<List<Request>> getRequests();
    Call<List<Offer>> getOffers();
    Call<List<Event>> getEvents();

    Call<Event> getEventById(String id);
    Call<Request> getRequestById(String id);
    Call<Offer> getOfferById(String id);

    Call<List<Request>> getRequestsBySubject(String subject);
    Call<List<Offer>> getOffersBySubject(String subject);

    Call<List<Event>> getEventsByTitle(String title);

}
