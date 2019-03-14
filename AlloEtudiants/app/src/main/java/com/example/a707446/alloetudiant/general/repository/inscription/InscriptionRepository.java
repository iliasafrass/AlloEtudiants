package com.example.a707446.alloetudiant.general.repository.inscription;

import com.example.a707446.alloetudiant.general.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.general.model.payload.RegisterMessage;

import retrofit2.Call;

public interface InscriptionRepository {

    Call<RegisterMessage> inscription(RegisterProfileDto profileDto);
}
