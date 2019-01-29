package com.example.a707446.alloetudiant.recherche.tabFragments.demande.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter.RequestContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRepoImpl implements RequestRepo {

    private RequestContract.Presenter mPresenter;

    public RequestRepoImpl(RequestContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getRequests() {
        RetrofitClientInstance
                .getRetrofitInstance().create(AlloEtudiantRestClient.class)
                .getRequests()
                .enqueue(new Callback<List<Request>>() {
                    @Override
                    public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                        System.out.println(response.code());
                        List<Request> requestList = response.body();
                        if (requestList != null && !requestList.isEmpty()) {
                            mPresenter.receiveRequestsFromRepo(requestList);
                        } else {
                            System.out.println("#########");
                            System.out.println("Requests is Empty!");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Request>> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong!");
                    }
                });
    }
}
