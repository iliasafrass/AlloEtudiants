package com.example.a707446.alloetudiant.publication.proposition.pageFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.BottomBar;

import butterknife.BindView;


public class PropositionStep2 extends Fragment {

    TextView stepNumber;

    public static PropositionStep2 newInstance(int page, boolean isLast) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        if (isLast)
            args.putBoolean("isLast", true);
        final PropositionStep2 fragment = new PropositionStep2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_proposition_step2, container, false);
        stepNumber = (TextView) view.findViewById(R.id.textView3);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null){
            final int page = getArguments().getInt("page", 0);
                stepNumber.setText(Integer.toString(page));
        }
    }

}
