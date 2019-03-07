package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Request;

public interface DetailOfferContract {
    interface View {
        void getOfferById(Offer offer);
        void showMessage(String message);
    }

    interface Presenter {
        void startgetOfferById(String id);
        void startAskingOffer(Offer offer);
    }
}
