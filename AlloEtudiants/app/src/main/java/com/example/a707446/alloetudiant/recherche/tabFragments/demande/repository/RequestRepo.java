package com.example.a707446.alloetudiant.recherche.tabFragments.demande.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Request;

import java.util.List;

import retrofit2.Call;

public interface RequestRepo {
    Call<List<Request>> getRequests();
}
