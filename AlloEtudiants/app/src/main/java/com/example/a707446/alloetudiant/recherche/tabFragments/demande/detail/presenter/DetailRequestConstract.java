package com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter;

import android.content.Context;

import com.example.a707446.alloetudiant.general.model.pojo.Request;

public interface DetailRequestConstract {
    interface View {
        void getRequestById(Request request);
        void showError(String error);
        void showToast(String message);
        Context applicationContext();
    }

    interface Presenter {
        void startgetRequestById(String id);
        void startAskingRequest(Request request);
    }
}
