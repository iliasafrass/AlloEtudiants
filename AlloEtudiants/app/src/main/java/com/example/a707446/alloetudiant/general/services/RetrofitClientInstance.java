package com.example.a707446.alloetudiant.general.services;

import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static final String BASE_URL = "http://alloetudiantapi.herokuapp.com";
    static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + SharedPreferencesSingleton.getToken(BaseApplication.getAppContext()));
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).build();
    static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm")
            .setLenient()
            .create();
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
