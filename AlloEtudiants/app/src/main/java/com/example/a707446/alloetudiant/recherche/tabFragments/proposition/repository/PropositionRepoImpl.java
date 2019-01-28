package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter.PropositionContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropositionRepoImpl implements PropositionRepo {
    private PropositionContract.Presenter mPresenter;

    public PropositionRepoImpl(PropositionContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
    @Override
    public void getOffers() {
        RetrofitClientInstance
                .getRetrofitInstance().create(AlloEtudiantRestClient.class)
                .getOffers()
                .enqueue(new Callback<List<Offer>>() {
                    @Override
                    public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                        System.out.println(response.code());
                        List<Offer> offerList = response.body();
                        if(offerList != null && !offerList.isEmpty()){
                            mPresenter.receiveOffersFromRepo(offerList);
                        } else {
                            System.out.println("#########");
                            System.out.println("Offers is Empty!");
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Offer>> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong!");
                    }
                });
    }
}
