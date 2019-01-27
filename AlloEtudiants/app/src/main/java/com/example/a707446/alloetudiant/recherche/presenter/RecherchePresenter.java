package com.example.a707446.alloetudiant.recherche.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.recherche.repository.RechercheRepImpl;
import com.example.a707446.alloetudiant.recherche.repository.RechercheRepo;

import java.util.List;

public class RecherchePresenter implements RechercheContract.Presenter{

    // Constants
    private static final String TAG = RecherchePresenter.class.getSimpleName();

    // Globals
    private RechercheContract.View mView;
    private RechercheRepo mRepo;



    public RecherchePresenter(RechercheContract.View view) {
        mView = view;
        mRepo = new RechercheRepImpl(this);

    }


    @Override
    public void start() {
        mView.test();
    }


    @Override
    public void receiveEventsFromRepo(List<Event> eventList) {
        if(eventList != null && !eventList.isEmpty()) {
            mView.receiveEventsFromPresenter(eventList);
        }
        else{
            mView.test();
        }
    }

    @Override
    public void sendEventsToView() {
        mRepo.getEvents();
    }


}
