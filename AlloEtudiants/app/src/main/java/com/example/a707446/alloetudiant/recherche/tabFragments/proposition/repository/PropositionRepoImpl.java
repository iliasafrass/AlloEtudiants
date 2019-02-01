package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PropositionRepoImpl implements PropositionRepo {
    //instance de retrofit builder
    Retrofit retrofit;

    public PropositionRepoImpl() {
        this.retrofit = RetrofitClientInstance.getRetrofitInstance();
    }
    @Override
    public Call<List<Offer>> getOffers() {
        return retrofit.create(AlloEtudiantRestClient.class).getOffers();

    }
}
