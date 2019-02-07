package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;

public interface DetailOfferContract {
    interface View {
        void getOfferById(Offer offer);
    }

    interface Presenter {

        void startgetOfferById(String id);
    }
}
