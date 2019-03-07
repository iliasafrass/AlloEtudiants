package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.RechercheFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.detail.presenter.DetailEvenementContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.detail.presenter.DetailEvenementpresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailEventFragment extends AbstractFragment implements DetailEvenementContract.View {

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

    private DetailEvenementContract.Presenter mPresenter;
    private Event mEvent;
    private String idEvent;

    /*private void annuler() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navigationActivity_fragmentContainer, RechercheFragment.newInstance());
        transaction.commit();
    }*/

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
        mPresenter = new DetailEvenementpresenter(this);
        NavigationActivity.inDetail = true;
        mPresenter.startgetEventById(idEvent);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationActivity.firstChildFragment = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        NavigationActivity.inDetail = false;
    }

    @Override
    public void getEventById(Event event) {
        Log.d("event", event.getId());
        this.mEvent = event;

        if(mEvent != null) {
//            Toast.makeText(this.getView().getContext(), mEvent.getId(), Toast.LENGTH_LONG).show();
            Log.d("mEvent", mEvent.toString());
            title.setText(mEvent.getTitle());
            icon.setImageResource(R.drawable.ic_events);
            date.setText(mEvent.getDates());
            description.setText(mEvent.getDescription());
            address.setText(mEvent.getAddress());
            getActivity().setTitle(R.string.toolbar_details);
        }
        else{
//            Toast.makeText(this.getView().getContext(), "null", Toast.LENGTH_LONG).show();
            Log.d("mEvent", "null");
        }
    }


}
