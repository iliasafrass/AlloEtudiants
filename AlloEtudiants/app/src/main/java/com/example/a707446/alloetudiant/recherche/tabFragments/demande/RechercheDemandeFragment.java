package com.example.a707446.alloetudiant.recherche.tabFragments.demande;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter.RequestContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter.RequestPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechercheDemandeFragment extends AbstractFragment implements RequestContract.View {

    //Views
    @BindView(R.id.recycler_view_demande)
    public RecyclerView recyclerView;

    //variable
    private RequestContract.Presenter mPresenter;
    private List<Request> requestList = new ArrayList<>();
    private RequestsAdapter mAdapter;

    public RechercheDemandeFragment() {
        // Required empty public constructor
    }


    public static RechercheDemandeFragment newInstance() {
        return new RechercheDemandeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recherche_demande_fragment, null);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = new RequestPresenter(this);

        //creation de l'adapter on lui passant la liste des evenemnts.
        mAdapter = new RequestsAdapter(getActivity().getBaseContext(), requestList);

        //Définir le layoutManager pour positionner les éléments
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //ajouter une ligne qui sépare deux item
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // Attachez l'adaptateur au recyclerview .
        recyclerView.setAdapter(mAdapter);

        mPresenter.sendRequestsToView();


        return view;
    }

    @Override
    public void receiveRequestsFromPresenter(List<Request> requests) {
        mAdapter.setRequestsList(requests);
    }


}
