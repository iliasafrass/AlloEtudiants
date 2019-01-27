package com.example.a707446.alloetudiant.recherche.tabFragments.evenement;


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
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RechercheEvenementFragment extends AbstractFragment{

    //Views
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    //variable
    private List<Event> eventList = new ArrayList<>();
    private EventsAdapter mAdapter;



    public RechercheEvenementFragment() {
        // Required empty public constructor

    }

    public static RechercheEvenementFragment newInstance() {
        return new RechercheEvenementFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recherche_evenement_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        //creation de l'adaptter on lui passant la lite des film.
        mAdapter = new EventsAdapter(getActivity().getBaseContext(), eventList);

        //Définir le layoutManager pour positionner les éléments
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //ajouter une ligne qui sépare deux item
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // Attachez l'adaptateur au recyclerview .
        recyclerView.setAdapter(mAdapter);

        //methode qui permet d'initier la liste des film
        prepareEventData();


        return view;
    }

    private void prepareEventData() {

        Date createdDate = new Date(2019,01,12);
        Date lastModifiedDate= new Date(2019,01,24);

        List<Date> dates= new ArrayList<Date>();
        dates.add(createdDate);
        Event event = new Event("1",createdDate,lastModifiedDate,"profile1",AnnounceType.EVENT,"Soirée d'integration","univ Lille","soiré pour les nouveaux etudiants","img",dates);

       eventList.add(event);
        //notifier que l es donnée sont change pour fair un refresh des donnne
        mAdapter.notifyDataSetChanged();

    }

}
