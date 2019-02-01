package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class EvenementRepoImpl implements EvenementRepo {


    Retrofit retrofit;
    public EvenementRepoImpl() {
        this.retrofit = RetrofitClientInstance.getRetrofitInstance();
    }

    @Override
    public Call<List<Event>> getEvents() {
        return retrofit.create(AlloEtudiantRestClient.class).getEvents();
    }
}
