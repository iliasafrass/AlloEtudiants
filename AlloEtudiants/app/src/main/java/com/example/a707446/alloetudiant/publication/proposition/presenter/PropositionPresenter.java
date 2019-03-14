package com.example.a707446.alloetudiant.publication.proposition.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.dto.OfferDto;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropositionPresenter implements PropositionContract.Presenter {
    // Constants
    private static final String TAG = PropositionPresenter.class.getSimpleName();

    // Globals
    private PropositionContract.View mView;
    private Repo mRepo;

    public PropositionPresenter(PropositionContract.View view) {
        mView = view;
        mRepo = new RepoImpl();
    }


    @Override
    public void startCreateOffer(OfferDto offerDto) {
        mRepo.createOffer(offerDto).enqueue(
                new Callback<Offer>() {
                    @Override
                    public void onResponse(Call<Offer> call, Response<Offer> response) {
                        if (response.body() != null)
                            mView.showSuccessMsg();
                        else
                            mView.showFailedMsg();
                        Log.d("POST_REQUEST", " response = " + response.body());
                    }

                    @Override
                    public void onFailure(Call<Offer> call, Throwable t) {

                        Log.d("POST_REQUEST", " Error = " + t.toString());
                        mView.showFailedMsg();
                    }
                }
        );
    }
}
