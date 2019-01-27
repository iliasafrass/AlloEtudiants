package com.example.a707446.alloetudiant.recherche.tabFragments.evenement;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.recherche.presenter.RechercheContract;
import com.example.a707446.alloetudiant.recherche.presenter.RecherchePresenter;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter.EvenementContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter.EvenementPresenter;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RechercheEvenementFragment extends AbstractFragment implements EvenementContract.View{

    //Views
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

/*    @BindView(R.id.textView_evenement)
    public TextView textView;*/


    //variable

    private EvenementContract.Presenter mPresenter;
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




        //creation de l'adapter on lui passant la liste des evenemnts.
        mAdapter = new EventsAdapter(getActivity().getBaseContext(), eventList);

        //Définir le layoutManager pour positionner les éléments
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //ajouter une ligne qui sépare deux item
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // Attachez l'adaptateur au recyclerview .
        recyclerView.setAdapter(mAdapter);


        mPresenter = new EvenementPresenter(this);

        mPresenter.sendEventsToView();
        //prepareEventData();
        return view;
    }

    private void prepareEventData() {

        Date createdDate = new Date(2019,01,12);
        Date lastModifiedDate= new Date(2019,01,24);

        List<Date> dates= new ArrayList<Date>();
        dates.add(createdDate);
        Event event = new Event("1",createdDate,lastModifiedDate,"profile1",AnnounceType.EVENT,"Soirée d'integration","univ Lille","soiré pour les nouveaux etudiants","img",dates);
        Event event2 = new Event("2",createdDate,lastModifiedDate,"profile1",AnnounceType.EVENT,"Soirée d'integration","univ Lille","soiré pour les nouveaux etudiants","img",dates);

        eventList.add(event);
        eventList.add(event2);


        //notifier que l es donnée sont change pour fair un refresh des donnne
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void receiveEventsFromPresenter(List<Event> events) {
        for(Event e : events){
            eventList.add(e);
            mAdapter.notifyDataSetChanged();
            //textView.setText("");
        }
        Toast.makeText(getActivity(), "Size : "+eventList.size(), Toast.LENGTH_SHORT).show();

    }
}
