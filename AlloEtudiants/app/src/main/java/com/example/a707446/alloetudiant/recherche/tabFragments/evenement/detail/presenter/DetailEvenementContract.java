package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.detail.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Event;

public interface DetailEvenementContract {
    interface View {
        void getEventById(Event event);
    }

    interface Presenter {

        void startgetEventById(String id);
    }

}
