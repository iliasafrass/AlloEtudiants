package com.example.a707446.alloetudiant.recherche.tabFragments.proposition;


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
public class RecherchePropositionFragment extends AbstractFragment{


    public RecherchePropositionFragment() {
        // Required empty public constructor
    }



    public static RecherchePropositionFragment newInstance() {
        return new RecherchePropositionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recherche_proposition_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);



        return view;
    }

}
