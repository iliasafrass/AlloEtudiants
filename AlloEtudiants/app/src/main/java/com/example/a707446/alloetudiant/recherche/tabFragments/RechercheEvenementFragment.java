package com.example.a707446.alloetudiant.recherche.tabFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RechercheEvenementFragment extends AbstractFragment{


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



        return view;
    }
}
