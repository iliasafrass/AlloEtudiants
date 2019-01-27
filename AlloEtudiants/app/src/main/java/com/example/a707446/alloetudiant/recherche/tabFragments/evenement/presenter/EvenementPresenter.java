package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository.EvenementRepImpl;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.repository.EvenementRepo;

import java.util.List;

public class EvenementPresenter implements EvenementContract.Presenter{

    // Constants
    private static final String TAG = EvenementPresenter.class.getSimpleName();

    // Globals
    private EvenementContract.View mView;
    private EvenementRepo mRepo;



    public EvenementPresenter(EvenementContract.View view) {
        mView = view;
        mRepo = new EvenementRepImpl(this);

    }




    @Override
    public void receiveEventsFromRepo(List<Event> eventList) {
        if(eventList != null && !eventList.isEmpty()) {
            mView.receiveEventsFromPresenter(eventList);
        }
        else{
        }
    }

    @Override
    public void sendEventsToView() {
        mRepo.getEvents();
    }


}
