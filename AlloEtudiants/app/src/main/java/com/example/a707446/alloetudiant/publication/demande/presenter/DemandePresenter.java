package com.example.a707446.alloetudiant.publication.demande.presenter;

import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

public class DemandePresenter implements DemandeContract {
    // Constants
    private static final String TAG = DemandePresenter.class.getSimpleName();

    // Globals
    private DemandeContract.View mView;
    private Repo mRepo;

    public DemandePresenter(DemandeContract.View view) {
        mView = view;
        mRepo = new RepoImpl();
    }

    

}
