package com.example.a707446.alloetudiant.inscription.presenter;

public interface InscriptionContract {
    interface View {
        void annuler();
        void inscrire();
    }

    interface Presenter  {
        void startInscrire();
        void startAnnuler();
    }
}
