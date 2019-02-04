package com.example.a707446.alloetudiant.general.repository.connexion;

import com.example.a707446.alloetudiant.general.services.connexion.LoginWebService;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;
import com.example.a707446.alloetudiant.general.model.payload.LoginRequest;

import retrofit2.Call;
import retrofit2.Retrofit;

public class LoginRepositoryImpl implements LoginRepository {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<Void> login(LoginRequest loginRequest) {
        return retrofit.create(LoginWebService.class)
                .login(loginRequest);
    }

    @Override
    public Call<String> getProfileIdByEmail(String email) {
        return retrofit.create(LoginWebService.class)
                .getProfileIdByEmail(email);
    }


}
