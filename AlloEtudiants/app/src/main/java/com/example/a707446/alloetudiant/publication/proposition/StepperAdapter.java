package com.example.a707446.alloetudiant.publication.proposition;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.example.a707446.alloetudiant.publication.proposition.fragments.PropositionStep0;
import com.example.a707446.alloetudiant.publication.proposition.fragments.PropositionStep1;
import com.example.a707446.alloetudiant.publication.proposition.fragments.RecapOfferFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepperAdapter extends AbstractFragmentStepAdapter {


    public StepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                final PropositionStep0 step0 = new PropositionStep0();
                return step0;

            case 1:
                final PropositionStep1 step1 = new PropositionStep1();
                return step1;
            case 2:
                final RecapOfferFragment step2 = new RecapOfferFragment();
                return step2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position) {
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("") //can be a CharSequence instead
                        .create();
        }
        return null;
    }
}