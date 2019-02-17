package com.example.a707446.alloetudiant.publication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.publication.demande.StartDemande;
import com.example.a707446.alloetudiant.publication.proposition.StartPublierService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublierFragment extends AbstractFragment {

    public PublierFragment() {
        // Requires empty public constructor
    }

    public static PublierFragment newInstance() {
        return new PublierFragment();
    }

    @BindView(R.id.button_proposition)
    public ImageButton btn_proposition;

    @OnClick(R.id.button_proposition)
    void goToServiceActivity(){
        Intent i = new Intent(getContext(),StartPublierService.class);
        startActivity(i);
    }

    @BindView(R.id.button_demande)
    public ImageButton btn_demande;

    @OnClick(R.id.button_demande)
    void goToDemande(){
        Intent i = new Intent(getContext(),StartDemande.class);
        startActivity(i);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.publier_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        getActivity().setTitle(R.string.toolbar_publier);

        return view;
    }

}
