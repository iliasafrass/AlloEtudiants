package com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestPresenter implements RequestContract.Presenter {
    // Constants
    private static final String TAG = RequestPresenter.class.getSimpleName();

    // Globals
    private RequestContract.View mView;
    private Repo mRepo;

    public RequestPresenter(RequestContract.View view) {
        mView = view;
        mRepo = new RepoImpl();

    }

    @Override
    public void sendRequestsToView() {
        mRepo.getRequests().enqueue(
                new Callback<List<Request>>() {
                    @Override
                    public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                        //view.methode
                        mView.receiveRequestsFromPresenter(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Request>> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong!");
                    }
                }
        );
    }
}
