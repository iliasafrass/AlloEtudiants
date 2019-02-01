package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository.EvenementRepo;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository.EvenementRepoImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvenementPresenter implements EvenementContract.Presenter {

    // Constants
    private static final String TAG = EvenementPresenter.class.getSimpleName();

    // Globals
    private EvenementContract.View mView;
    private EvenementRepo mRepo;

    public EvenementPresenter(EvenementContract.View view) {
        mView = view;
        mRepo = new EvenementRepoImpl();

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
