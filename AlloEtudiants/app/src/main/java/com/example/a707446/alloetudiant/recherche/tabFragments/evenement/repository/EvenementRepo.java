package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Event;

import java.util.List;

import retrofit2.Call;

public interface EvenementRepo {
    Call<List<Event>> getEvents();
}
