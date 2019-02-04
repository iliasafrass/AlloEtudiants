package com.example.a707446.alloetudiant.inscription.repository;

import com.example.a707446.alloetudiant.model.dto.ProfileDto;
import com.example.a707446.alloetudiant.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.model.payload.RegisterMessage;

import retrofit2.Call;

public interface InscriptionRepository {

    Call<RegisterMessage> inscription(RegisterProfileDto profileDto);
}
