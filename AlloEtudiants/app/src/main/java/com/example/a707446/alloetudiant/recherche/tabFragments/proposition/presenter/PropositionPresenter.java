package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import java.util.List;

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
    public void sendOffersToView() {
        mRepo.getOffers()
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        mView.receiveOffersFromPresenter(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong!");
                    }
                });
    }

    @Override
    public void startgetOffersBySubject(String subject) {
        mRepo.getOffersBySubject(subject).enqueue(
                new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        if(response.body() != null)
                            mView.getOffersBySubject(response.body());
                        else
                            Log.d("response", " is null");
                    }

                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong in  Offer presenter ! :(");
                    }
                });
    }
}
