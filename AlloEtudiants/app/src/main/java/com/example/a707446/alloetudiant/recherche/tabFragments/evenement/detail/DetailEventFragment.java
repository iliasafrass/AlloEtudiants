package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.BottomBar;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailEventFragment extends AbstractFragment {

    //Views
    @BindView(R.id.title_event_detail)
    public TextView title;

    @BindView(R.id.event_image_detail)
    public ImageView icon;

    @BindView(R.id.date_event_detail)
    public TextView date;

    @BindView(R.id.description_event_detail)
    public TextView description;

    @BindView(R.id.address_event_detail)
    public TextView address;

    private Event event;
    private String idEvent;

    public BottomBar.EnableBottomBar enableBottomBar;
    public DetailEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        if (getArguments() != null) {
            idEvent = getArguments().getString("id", "-1");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_event,null);
        mUnbinder = ButterKnife.bind(this,view);
/*
        title.setText(event.getTitle());
        icon.setImageResource(R.drawable.ic_events);
        date.setText(event.getDates().toString());
        description.setText(event.getDescription());
        address.setText(event.getAddress());*/

        getActivity().setTitle(R.string.toolbar_details);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationActivity.firstChildFragment = false;
        Toast.makeText(view.getContext(), idEvent, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        enableBottomBar = (BottomBar.EnableBottomBar)getContext();
        enableBottomBar.enableBottomBar();
    }
}
