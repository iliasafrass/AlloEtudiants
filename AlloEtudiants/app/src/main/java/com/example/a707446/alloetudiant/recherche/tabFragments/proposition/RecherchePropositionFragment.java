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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.enumeration.Subject;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter.PropositionContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.presenter.PropositionPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecherchePropositionFragment extends AbstractFragment implements PropositionContract.View {

    //Views
    @BindView(R.id.recycler_view_proposition)
    public RecyclerView recyclerView;
    @BindView(R.id.spinner_offer)
    public MaterialSpinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    List<String> listItems = new ArrayList<>();
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

        View view = inflater.inflate(R.layout.recherche_proposition_fragment, null);
        mUnbinder = ButterKnife.bind(this, view);

        NavigationActivity.inDetail = false;
        mPresenter = new PropositionPresenter(this);

        //creation de l'adapter on lui passant la liste des propositions.
        mAdapter = new OffersAdapter(getActivity().getBaseContext(), offerList);

        /*
         *filter with spinner
         */
        initSpinnerItems();
        spinnerAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, listItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1)// -1 hint choose..
                {
                    String selected = spinner.getItemAtPosition(position).toString();
                    mPresenter.startgetOffersBySubject(selected);
                } else {
                    mPresenter.sendOffersToView();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*************************************************************/


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

    private void initSpinnerItems() {
        listItems.add(Subject.MATHEMATIQUES.toString());
        listItems.add(Subject.PHYSIQUE.toString());
        listItems.add(Subject.CHIMIE.toString());
        listItems.add(Subject.MECANIQUE.toString());
        listItems.add(Subject.INFORMATIQUE.toString());
        listItems.add(Subject.ELECTRONIQUE.toString());
    }

    @Override
    public void receiveOffersFromPresenter(List<Offer> offers) {
        mAdapter.setOffersList(offers);
    }

    @Override
    public void getOffersBySubject(List<Offer> offers) {
        mAdapter.setOffersList(offers);
    }

    @Override
    public void onPause() {
        super.onPause();
        NavigationActivity.inDetail = false;
    }
}
