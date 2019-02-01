package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;

import java.util.List;

public interface PropositionContract {
    interface View {
        void receiveOffersFromPresenter(List<Offer> offers);
    }

    interface Presenter  {
        void sendOffersToView();
    }
}
