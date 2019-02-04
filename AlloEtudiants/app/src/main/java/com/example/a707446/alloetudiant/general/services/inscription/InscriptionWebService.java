package com.example.a707446.alloetudiant.general.services.inscription;

import com.example.a707446.alloetudiant.general.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.general.model.payload.RegisterMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InscriptionWebService {

    @POST("/register")
    Call<RegisterMessage> inscription(
            @Body RegisterProfileDto profileDto
    );

}
