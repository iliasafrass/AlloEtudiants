package com.example.a707446.alloetudiant.inscription.presenter;

import com.example.a707446.alloetudiant.general.model.dto.RegisterProfileDto;

public interface InscriptionContract {
    interface View {
        void annuler();

        void endInscrire(String message, int code);

        void toast(String message);
    }

    interface Presenter {
        // To be used by the view
        void startInscrire(RegisterProfileDto profileDto);

        void startAnnuler();

        // To be used by The repositoyr
        void startToast(String message);

        void endInscrire(String message, int code);

    }
}
