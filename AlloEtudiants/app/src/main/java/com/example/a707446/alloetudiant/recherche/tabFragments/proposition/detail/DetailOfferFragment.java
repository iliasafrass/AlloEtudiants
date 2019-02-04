package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail;


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
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.BottomBar;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailOfferFragment extends AbstractFragment {
    //Views
    @BindView(R.id.title_offer_detail)
    public TextView title;

    @BindView(R.id.offer_image_detail)
    public ImageView icon;

    @BindView(R.id.date_offer_detail)
    public TextView date;

    @BindView(R.id.description_offer_detail)
    public TextView description;

    @BindView(R.id.address_offer_detail)
    public TextView address;

    private Offer offer;
    private String idOffer;

    public BottomBar.EnableBottomBar enableBottomBar;

    public DetailOfferFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        if (getArguments() != null) {
            idOffer = getArguments().getString("id", "-1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_offer,null);
        mUnbinder = ButterKnife.bind(this,view);

        getActivity().setTitle(R.string.toolbar_details);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationActivity.firstChildFragment = false;
        Toast.makeText(view.getContext(), idOffer, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onPause() {
        super.onPause();
        enableBottomBar = (BottomBar.EnableBottomBar)getContext();
        enableBottomBar.enableBottomBar();
    }
}
