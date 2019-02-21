package com.example.a707446.alloetudiant.annonces;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;

import butterknife.ButterKnife;

public class AnnonceFragment extends AbstractFragment {

    public AnnonceFragment() {
        // Requires empty public constructor
    }

    public static AnnonceFragment newInstance() {
        return new AnnonceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.annonce_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        getActivity().setTitle(R.string.toolbar_annonce);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
            NavigationActivity.mBottomNavigationView.getMenu().getItem(2).setChecked(true);

    }
}
