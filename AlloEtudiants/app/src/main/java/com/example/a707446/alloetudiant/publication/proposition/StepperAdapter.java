package com.example.a707446.alloetudiant.publication.proposition;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.a707446.alloetudiant.publication.proposition.pageFragments.PropositionStep0;
import com.example.a707446.alloetudiant.publication.proposition.pageFragments.PropositionStep1;
import com.example.a707446.alloetudiant.publication.proposition.pageFragments.RecapOfferFragment;
import com.google.gson.Gson;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepperAdapter extends AbstractFragmentStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";
    private static final String TITRE = "titre";
    private static final String DESCRIPTION = "description";
    private static final String MATIERE = "matiere";


    public StepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }
    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                final PropositionStep0 step0 = new PropositionStep0();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                step0.setArguments(b1);

                Log.d("step", " 0");
                return step0;

            case 1:
                final PropositionStep1 step1 = new PropositionStep1();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                step1.setArguments(b2);
                Log.d("step", " 1");
                return step1;
            case 2:
                final RecapOfferFragment step3 = new RecapOfferFragment();
                Bundle b3 = new Bundle();

                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                b3.putString(TITRE,PropositionStep0.titreInput);
                Log.d("titreInput", " titre is "+PropositionStep0.titreInput);
                b3.putString(DESCRIPTION,PropositionStep0.descriptionInput);
                b3.putString(MATIERE,PropositionStep0.selectedSpinner);

                Log.d("step", " 2");
                step3.setArguments(b3);
                return step3;

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
        switch (position){
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("0") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("1") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("2") //can be a CharSequence instead
                        .create();
        }
        return null;
    }
}