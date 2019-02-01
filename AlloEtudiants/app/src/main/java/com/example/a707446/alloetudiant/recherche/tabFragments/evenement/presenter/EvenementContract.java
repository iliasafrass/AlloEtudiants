package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;

import java.util.List;

public interface EvenementContract {

    interface View {
        void receiveEventsFromPresenter(List<Event> events);

    }

    interface Presenter {
        void sendEventsToView();
    }

}
