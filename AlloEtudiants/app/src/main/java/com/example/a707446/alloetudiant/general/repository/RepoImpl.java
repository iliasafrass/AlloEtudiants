package com.example.a707446.alloetudiant.general.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RepoImpl implements Repo {

    //instance de retrofit builder
    Retrofit retrofit;
    //android google simple
    public RepoImpl() {
        this.retrofit = RetrofitClientInstance.getRetrofitInstance();
    }

    @Override
    public Call<List<Request>> getRequests() {
        return retrofit.create(AlloEtudiantRestClient.class).getRequests();
    }
    @Override
    public Call<List<Offer>> getOffers() {
        return retrofit.create(AlloEtudiantRestClient.class).getOffers();

    }

    @Override
    public Call<List<Event>> getEvents() {
        return retrofit.create(AlloEtudiantRestClient.class).getEvents();
    }
}