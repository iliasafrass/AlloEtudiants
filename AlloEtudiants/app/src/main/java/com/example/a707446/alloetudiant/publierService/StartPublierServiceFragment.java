package com.example.a707446.alloetudiant.publierService;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.rakshakhegde.stepperindicator.StepperIndicator;


public class StartPublierServiceFragment extends Fragment {

    StepperIndicator indicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_start_publier, container /*, false*/);

        final ViewPager pager = result.findViewById(R.id.pager);
        assert pager != null;
        pager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager()));

        indicator = result.findViewById(R.id.stepper_indicator);
        // We keep last page for a "finishing" page
        indicator.setViewPager(pager, true);

        indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                pager.setCurrentItem(step, true);
            }
        });

        return result;
    }
}
