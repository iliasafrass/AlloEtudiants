package com.example.a707446.alloetudiant.recherche.tabFragments.evenement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailFragment extends AbstractFragment {

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

    public EventDetailFragment() {
        // Requires empty public constructor
    }

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Get back arguments
            if (getArguments() != null) {
                this.idEvent =  getArguments().getString("id");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event_detail_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        getActivity().setTitle(R.string.detail);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText(event.getTitle());
        icon.setImageResource(R.drawable.ic_events);
        date.setText(event.getDates().toString());
        description.setText(event.getDescription());
        address.setText(event.getAddress());
    }
}
