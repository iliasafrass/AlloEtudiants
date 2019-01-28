package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.repository.PropositionRepo;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.repository.PropositionRepoImpl;

import java.util.List;




public class PropositionPresenter implements  PropositionContract.Presenter {


    // Constants
    private static final String TAG = PropositionPresenter.class.getSimpleName();

    // Globals
    private PropositionContract.View mView;
    private PropositionRepo mRepo;

    public PropositionPresenter(PropositionContract.View view) {
        mView = view;
        mRepo = new PropositionRepoImpl(this);

    }

    @Override
    public void receiveOffersFromRepo(List<Offer> offerList) {
        if(offerList != null && !offerList.isEmpty()) {
            mView.receiveOffersFromPresenter(offerList);
        }
        else{
            System.out.println("eventList receive from repo is NULL !!");
        }
    }

    @Override
    public void sendOffersToView() {
        mRepo.getOffers();
    }
}
