package com.example.a707446.alloetudiant.recherche.presenter;

public class RecherchePresenter implements RechercheContract.Presenter {

    // Constants
    private static final String TAG = RecherchePresenter.class.getSimpleName();

    // Globals
    private RechercheContract.View mView;

    public RecherchePresenter(RechercheContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.test();
    }
}
