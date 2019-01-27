package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;
import com.example.a707446.alloetudiant.recherche.presenter.RechercheContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechercheRepImpl implements RechercheRepo {

    private RechercheContract.Presenter mPresenter;

    public RechercheRepImpl(RechercheContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
    @Override
        public void getEvents() {
            RetrofitClientInstance
                    .getRetrofitInstance().create(AlloEtudiantRestClient.class)
                    .getEvents()
                    .enqueue(new Callback<List<Event>>() {
                        @Override
                        public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                            System.out.println(response.code());
                            List<Event> eventList = response.body();
                            if(eventList != null && !eventList.isEmpty()){
                                mPresenter.receiveEventsFromRepo(eventList);
                            } else {
                                System.out.println("#########");
                                System.out.println("Events is Empty!");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<Event>> call, Throwable t) {
                            System.out.println("#########");
                            System.out.println("Something went wrong!");
                        }
                    });
        }
}
