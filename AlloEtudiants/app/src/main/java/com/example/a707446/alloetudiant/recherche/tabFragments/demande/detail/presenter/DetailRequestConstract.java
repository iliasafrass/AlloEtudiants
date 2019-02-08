package com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Request;

public interface DetailRequestConstract {
    interface View {
        void getRequestById(Request request);
    }

    interface Presenter {
        void startgetRequestById(String id);
    }
}
