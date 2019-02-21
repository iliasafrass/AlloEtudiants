package com.example.a707446.alloetudiant.recherche.tabFragments.evenement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter.EvenementContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.presenter.EvenementPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class RechercheEvenementFragment extends AbstractFragment implements EvenementContract.View {

    //Views
    @BindView(R.id.recycler_view_evenement)
    public RecyclerView recyclerView;
    @BindView(R.id.textInputTitre)
    public TextInputLayout textInputTitle;
    ArrayList<String> items = new ArrayList<>();
    SpinnerDialog spinnerDialog;
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

        View view = inflater.inflate(R.layout.recherche_evenement_fragment, null);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = new EvenementPresenter(this);

        //creation de l'adapter on lui passant la liste des evenemnts.
        mAdapter = new EventsAdapter(getActivity().getBaseContext(), eventList);

        //Définir le layoutManager pour positionner les éléments
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //ajouter une ligne qui sépare deux item
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // Attachez l'adaptateur au recyclerview .
        recyclerView.setAdapter(mAdapter);

        NavigationActivity.inDetail = false;
        mPresenter.sendEventsToView();


        return view;
    }

    private void initItems() {
        items.add("Tous");
        for (Event event : eventList) {
            if (!items.contains(event.getTitle()))
                items.add(event.getTitle());
        }
    }

    @Override
    public void receiveEventsFromPresenter(List<Event> events) {
        eventList = events;
        mAdapter.setEventsList(events);
        /*
         *search by title
         */
        initItems();
        spinnerDialog = new SpinnerDialog(getActivity(), items, "chercher par le titre");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                if (!item.equals("Tous"))
                    mPresenter.startgetEventsByTitle(item);
                else
                    mPresenter.sendEventsToView();
            }
        });

        textInputTitle.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        /*************************************************************************/

    }

    @Override
    public void getEventsByTitle(List<Event> events) {
        mAdapter.setEventsList(events);
    }

    @Override
    public void onPause() {
        super.onPause();
        NavigationActivity.inDetail = false;
    }
}
