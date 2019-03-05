package com.example.a707446.alloetudiant.publication.evenement;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.example.a707446.alloetudiant.publication.evenement.fragments.EventStep0;
import com.example.a707446.alloetudiant.publication.evenement.fragments.EventStep1;
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
                final EventStep0 step0 = new EventStep0();
                return step0;

            case 1:
                final EventStep1 step1 = new EventStep1();
                return step1;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
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
        }
        return null;
    }
}
