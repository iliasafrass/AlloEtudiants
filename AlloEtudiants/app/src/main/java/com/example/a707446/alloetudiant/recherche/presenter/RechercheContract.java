package com.example.a707446.alloetudiant.recherche.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;

import java.util.List;

public interface RechercheContract {

    interface View {

        void test();

        void receiveEventsFromPresenter(List<Event> events);
    }

    interface Presenter  {

        void start();
        void receiveEventsFromRepo(List<Event> eventList);
        void sendEventsToView();
    }

}
