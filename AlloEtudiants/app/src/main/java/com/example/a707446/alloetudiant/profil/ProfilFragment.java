package com.example.a707446.alloetudiant.profil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.annonces.AnnonceFragment;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;

import butterknife.ButterKnife;

public class ProfilFragment extends AbstractFragment {


    public ProfilFragment() {
        // Requires empty public constructor
    }

    public static  ProfilFragment newInstance() {return  new ProfilFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profil_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        getActivity().setTitle(R.string.toolbar_profil);

        return view;
    }
}
