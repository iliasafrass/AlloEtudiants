package com.example.a707446.alloetudiant.publication.evenement.presenter;

import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

public class EvenementPresenter implements EvenementContract {
    // Constants
    private static final String TAG = EvenementPresenter.class.getSimpleName();

    // Globals
    private EvenementContract.View mView;
    private Repo mRepo;

    public EvenementPresenter(EvenementContract.View view) {
        mView = view;
        mRepo = new RepoImpl();
    }


}
