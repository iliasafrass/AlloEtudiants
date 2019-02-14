package com.example.a707446.alloetudiant.publication.proposition.pageFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.BottomBar;


public class PropositionStep1 extends Fragment {

    private TextView lblPage;

    public static PropositionStep1 newInstance(int page, boolean isLast) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        if (isLast)
            args.putBoolean("isLast", true);
        final PropositionStep1 fragment = new PropositionStep1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_proposition_step1, container, false);
        lblPage = (TextView) view.findViewById(R.id.lbl_page);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null){
            final int page = getArguments().getInt("page", 0);
            if (getArguments().containsKey("isLast"))
                lblPage.setText("You're done!");
            else
                lblPage.setText(Integer.toString(page));
        }
    }

}
