package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvenementPresenter implements EvenementContract.Presenter {

    // Constants
    private static final String TAG = EvenementPresenter.class.getSimpleName();

    // Globals
    private EvenementContract.View mView;
    private Repo mRepo;

    public EvenementPresenter(EvenementContract.View view) {
        mView = view;
        mRepo = new RepoImpl();

    }

    @Override
    public void sendEventsToView() {

        mRepo.getEvents().enqueue(
                new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        //view.methode
                        mView.receiveEventsFromPresenter(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong!");
                    }
                }
        );
    }

}
