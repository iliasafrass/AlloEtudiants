package com.example.a707446.alloetudiant.profil.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Profile;

public interface ProfileContract {
    interface View {
        void getProfileById(Profile profile);
    }

    interface Presenter {
        void startgetProfileById(String id);
    }
}
