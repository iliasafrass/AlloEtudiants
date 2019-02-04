package com.example.a707446.alloetudiant.publication.proposition;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.rakshakhegde.stepperindicator.StepperIndicator;

import butterknife.ButterKnife;


public class StartPublierServiceFragment extends AbstractFragment {

    StepperIndicator indicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_start_publier, null /*, false*/);

        mUnbinder = ButterKnife.bind(this,result);

        final ViewPager pager = result.findViewById(R.id.pager2);
        assert pager != null;
        pager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager()));

        indicator = result.findViewById(R.id.stepper_indicator2);
        // We keep last page for a "finishing" page
        indicator.setViewPager(pager, true);

        indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                pager.setCurrentItem(step, true);
            }
        });

        getActivity().setTitle(R.string.toolbar_publier);

        return result;

    }


    public StartPublierServiceFragment() {
        // Required empty public constructor
    }
    public static StartPublierServiceFragment newInstance() {
        return new StartPublierServiceFragment();
    }

}
