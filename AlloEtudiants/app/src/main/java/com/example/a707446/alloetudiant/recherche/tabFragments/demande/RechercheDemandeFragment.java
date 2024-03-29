package com.example.a707446.alloetudiant.recherche.tabFragments.demande;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter.RequestContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter.RequestPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;

public class RechercheDemandeFragment extends AbstractFragment implements RequestContract.View {

    //Views
    @BindView(R.id.recycler_view_demande)
    public RecyclerView recyclerView;

    @BindView(R.id.spinner_request)
    public MaterialSpinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    List<String> listItems = new ArrayList<>();
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
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recherche_demande_fragment, null);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = new RequestPresenter(this);
        //creation de l'adapter on lui passant la liste des demandes.
        mAdapter = new RequestsAdapter(getActivity().getBaseContext(), requestList);
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
                    mPresenter.startgetRequestsBySubject(selected);
                } else {
                    mPresenter.sendRequestsToView();
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

        mPresenter.sendRequestsToView();
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
    public void receiveRequestsFromPresenter(List<Request> requests) {
        mAdapter.setRequestsList(requests);
        ;
    }

    @Override
    public void getRequestsBySubject(List<Request> requests) {
        mAdapter.setRequestsList(requests);
    }


}
