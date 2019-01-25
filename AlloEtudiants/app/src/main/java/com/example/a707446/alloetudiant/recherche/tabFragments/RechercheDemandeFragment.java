package com.example.a707446.alloetudiant.recherche.tabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;

import butterknife.ButterKnife;

public class RechercheDemandeFragment extends AbstractFragment {


    public RechercheDemandeFragment() {
        // Required empty public constructor
    }




    public static RechercheDemandeFragment newInstance() {
        return new RechercheDemandeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recherche_demande_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);



        return view;
    }

}
