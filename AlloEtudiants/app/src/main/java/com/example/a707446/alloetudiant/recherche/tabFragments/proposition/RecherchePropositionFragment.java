package com.example.a707446.alloetudiant.recherche.tabFragments.proposition;


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
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter.PropositionContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter.PropositionPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecherchePropositionFragment extends AbstractFragment implements PropositionContract.View {

    //Views
    @BindView(R.id.recycler_view_proposition)
    public RecyclerView recyclerView;

    //variable
    private PropositionContract.Presenter mPresenter;
    private List<Offer> offerList = new ArrayList<>();
    private OffersAdapter mAdapter;

    public RecherchePropositionFragment() {
        // Required empty public constructor
    }



    public static RecherchePropositionFragment newInstance() {
        return new RecherchePropositionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recherche_proposition_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        mPresenter = new PropositionPresenter(this);

        //creation de l'adapter on lui passant la liste des evenemnts.
        mAdapter = new OffersAdapter(getActivity().getBaseContext(), offerList);

        //Définir le layoutManager pour positionner les éléments
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //ajouter une ligne qui sépare deux item
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // Attachez l'adaptateur au recyclerview .
        recyclerView.setAdapter(mAdapter);

        mPresenter.sendOffersToView();


        return view;
    }

    @Override
    public void receiveOffersFromPresenter(List<Offer> offers) {
        for(Offer o : offers){
            offerList.add(o);
            mAdapter.notifyDataSetChanged();
        }
    }
}
