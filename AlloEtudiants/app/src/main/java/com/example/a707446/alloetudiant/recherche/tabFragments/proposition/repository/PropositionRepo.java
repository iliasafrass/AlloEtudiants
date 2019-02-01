package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;

import java.util.List;

import retrofit2.Call;

public interface PropositionRepo {
    Call<List<Offer>> getOffers();
}
