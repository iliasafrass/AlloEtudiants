package com.example.a707446.alloetudiant.inscription.repository;

import com.example.a707446.alloetudiant.connexion.presenter.ConnexionContract;
import com.example.a707446.alloetudiant.general.webservice.RetrofitClientInstance;
import com.example.a707446.alloetudiant.inscription.Inscription;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionContract;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionPresenter;
import com.example.a707446.alloetudiant.inscription.webservice.InscriptionWebService;
import com.example.a707446.alloetudiant.model.dto.ProfileDto;
import com.example.a707446.alloetudiant.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.model.payload.RegisterMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InscriptionRepositoryImpl implements InscriptionRepository {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<RegisterMessage> inscription(RegisterProfileDto profileDto) {
        return retrofit.create(InscriptionWebService.class)
                .inscription(profileDto);
    }
}
