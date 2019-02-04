package com.example.a707446.alloetudiant.general.repository.inscription;

import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;
import com.example.a707446.alloetudiant.general.services.inscription.InscriptionWebService;
import com.example.a707446.alloetudiant.general.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.general.model.payload.RegisterMessage;

import retrofit2.Call;
import retrofit2.Retrofit;

public class InscriptionRepositoryImpl implements InscriptionRepository {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<RegisterMessage> inscription(RegisterProfileDto profileDto) {
        return retrofit.create(InscriptionWebService.class)
                .inscription(profileDto);
    }
}
