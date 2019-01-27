package com.example.a707446.alloetudiant.general.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    //the localhost for emulator is 10.0.2.2
    private static final String BASE_URL ="https://alloetudiantapi.herokuapp.com";
    private static Retrofit retrofit = null;

    // create an instance of gson to be used when building our service
    static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm")
            .create();

    public static Retrofit getRetrofitInstance() {

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
