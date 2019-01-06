package com.example.a707446.alloetudiant.publication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;

import butterknife.ButterKnife;

public class PublierFragment extends AbstractFragment {

    public PublierFragment() {
        // Requires empty public constructor
    }

    public static PublierFragment newInstance() {
        return new PublierFragment();
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
