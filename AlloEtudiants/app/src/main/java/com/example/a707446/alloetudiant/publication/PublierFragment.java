package com.example.a707446.alloetudiant.publication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.publication.proposition.StartPublierServiceFragment;

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

    @BindView(R.id.publier_service)
    public Button btn_service;

    @OnClick(R.id.publier_service)
    void goToServiceActivity(){
//        Intent i = new Intent(getActivity().getApplicationContext(), PublierService.class);
//        startActivity(i);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.navigationActivity_fragmentContainer,new StartPublierServiceFragment())
                .addToBackStack(null)
                .commit();
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
