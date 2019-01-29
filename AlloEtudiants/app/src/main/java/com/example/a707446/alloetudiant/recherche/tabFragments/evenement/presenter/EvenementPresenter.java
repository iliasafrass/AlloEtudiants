package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository.EvenementRepo;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository.EvenementRepoImpl;

import java.util.List;

public class EvenementPresenter implements EvenementContract.Presenter {

    // Constants
    private static final String TAG = EvenementPresenter.class.getSimpleName();

    // Globals
    private EvenementContract.View mView;
    private EvenementRepo mRepo;

    public EvenementPresenter(EvenementContract.View view) {
        mView = view;
        mRepo = new EvenementRepoImpl(this);

    }

    @Override
    public void receiveEventsFromRepo(List<Event> eventList) {
        if (eventList != null && !eventList.isEmpty()) {
            mView.receiveEventsFromPresenter(eventList);
        } else {
            System.out.println("eventList receive from repo is NULL !!");
        }
    }

    @Override
    public void sendEventsToView() {
        mRepo.getEvents();
    }

}
