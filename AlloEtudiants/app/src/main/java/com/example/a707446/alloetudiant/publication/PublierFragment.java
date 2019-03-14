package com.example.a707446.alloetudiant.publication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.publication.demande.StartPublierDemande;
import com.example.a707446.alloetudiant.publication.evenement.Start_publier_evenement;
import com.example.a707446.alloetudiant.publication.proposition.StartPublierService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublierFragment extends AbstractFragment {

    public PublierFragment() {
        // Requires empty public constructor
    }

    public static PublierFragment newInstance() {
        return new PublierFragment();
    }

    @OnClick(R.id.cardOffer)
    void goToServiceActivity() {
        Intent i = new Intent(getContext(), StartPublierService.class);
        startActivity(i);
    }

    @OnClick(R.id.cardRequest)
    void goToDemande() {
        Intent i = new Intent(getContext(), StartPublierDemande.class);
        startActivity(i);
    }

    @OnClick(R.id.cardEvent)
    void goToEvenement() {
        Intent i = new Intent(getContext(), Start_publier_evenement.class);
        startActivity(i);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.publier_fragment, null);
        mUnbinder = ButterKnife.bind(this, view);

        getActivity().setTitle(R.string.toolbar_publier);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationActivity.mBottomNavigationView.getMenu().getItem(3).setChecked(true);

    }
}
