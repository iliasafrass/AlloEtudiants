package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOfferPresenter implements DetailOfferContract.Presenter {
    // Constants
    private static final String TAG = DetailOfferContract.class.getSimpleName();

    // Globals
    private DetailOfferContract.View mView;
    private Repo mRepo;

    public DetailOfferPresenter(DetailOfferContract.View view) {
        mView = view;
        mRepo = new RepoImpl();

    }
    @Override
    public void startgetOfferById(String id) {
        mRepo.getOfferById(id).enqueue(
                new Callback<Offer>() {
                    @Override
                    public void onResponse(Call<Offer> call, Response<Offer> response) {
                        if(response.body() != null)
                            mView.getOfferById(response.body());
                        else
                            Log.d("response", " is null");
                    }

                    @Override
                    public void onFailure(Call<Offer> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong in detail Offer presenter ! :(");
                    }
                });
    }
}
