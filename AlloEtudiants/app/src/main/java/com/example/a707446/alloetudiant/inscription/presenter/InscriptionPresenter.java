package com.example.a707446.alloetudiant.inscription.presenter;

public class InscriptionPresenter implements InscriptionContract.Presenter {
    // Constants
    private static final String TAG = InscriptionPresenter.class.getSimpleName();

    // Globals
    private InscriptionContract.View mView;

    public InscriptionPresenter(InscriptionContract.View view) {
        mView = view;
    }

    @Override
    public void startInscrire() {
        mView.inscrire();
    }

    @Override
    public void startAnnuler() {mView.annuler();}


}
