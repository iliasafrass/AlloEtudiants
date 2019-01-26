package com.example.a707446.alloetudiant.general.webservice;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static final String BASE_URL ="http://192.168.43.219:8080";
    private static Retrofit retrofit = null;

    static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3YXJheS5hbWluZTBAZ21haWwuY29tIiwiZXhwIjoxNTQ5MzEzODQyLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XX0.zSw3-OgXGItkqwMiAgZWoCqw_C7f-62q2gooaOMNC80");
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
    }).build();

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
