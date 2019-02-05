package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.detail;

import com.example.a707446.alloetudiant.general.model.pojo.Event;

public interface DetailEvenementContract {
    interface View {
         void getEventById(Event event);
    }

    interface Presenter {

        void StartgetEventById(String id);
    }

}
