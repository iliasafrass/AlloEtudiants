package com.example.a707446.alloetudiant.recherche.tabFragments.demande.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RequestRepoImpl implements RequestRepo {

    //instance de retrofit builder
    Retrofit retrofit;
    //android google simple
    public RequestRepoImpl() {
        this.retrofit = RetrofitClientInstance.getRetrofitInstance();
    }

    @Override
    public Call<List<Request>> getRequests() {
        return retrofit.create(AlloEtudiantRestClient.class).getRequests();
    }
}
