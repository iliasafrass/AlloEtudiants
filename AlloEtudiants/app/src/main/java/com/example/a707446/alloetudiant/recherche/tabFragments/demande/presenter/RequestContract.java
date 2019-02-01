package com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Request;

import java.util.List;

public interface RequestContract {
    interface View {
        void receiveRequestsFromPresenter(List<Request> requests);
    }

    interface Presenter {
        void sendRequestsToView();
    }
}
