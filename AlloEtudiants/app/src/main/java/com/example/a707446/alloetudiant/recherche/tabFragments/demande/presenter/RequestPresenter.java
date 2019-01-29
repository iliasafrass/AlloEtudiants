package com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter;

import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.repository.RequestRepo;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.repository.RequestRepoImpl;

import java.util.List;

public class RequestPresenter implements RequestContract.Presenter {
    // Constants
    private static final String TAG = RequestPresenter.class.getSimpleName();

    // Globals
    private RequestContract.View mView;
    private RequestRepo mRepo;

    public RequestPresenter(RequestContract.View view) {
        mView = view;
        mRepo = new RequestRepoImpl(this);

    }

    @Override
    public void receiveRequestsFromRepo(List<Request> requestList) {
        if (requestList != null && !requestList.isEmpty()) {
            mView.receiveRequestsFromPresenter(requestList);
        } else {
            System.out.println("requestList receive from repo is NULL !!");
        }
    }

    @Override
    public void sendRequestsToView() {
        mRepo.getRequests();
    }
}
