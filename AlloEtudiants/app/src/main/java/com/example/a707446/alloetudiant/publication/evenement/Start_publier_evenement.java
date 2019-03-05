package com.example.a707446.alloetudiant.publication.evenement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a707446.alloetudiant.R;
import com.stepstone.stepper.StepperLayout;

public class Start_publier_evenement extends AppCompatActivity implements DataManager {
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private static final String TITRE = "titre";
    private static final String ADRESSE = "adresse";
    private static final String DESCRIPTION = "description";
    private static final String DATES = "dates";

    private StepperLayout mStepperLayout;
    private StepperAdapter mStepperAdapter;

    private String mTitle,mDescription,mAdresse,mDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_publier_evenement);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout_evenement);
        mStepperAdapter = new StepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);

        int startingStepEvent = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        mAdresse = savedInstanceState != null ? savedInstanceState.getString(ADRESSE) : null;
        mDates = savedInstanceState != null ? savedInstanceState.getString(DATES) : null;
        mTitle = savedInstanceState != null ? savedInstanceState.getString(TITRE) : null;
        mDescription = savedInstanceState != null ? savedInstanceState.getString(DESCRIPTION) : null;

        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this), startingStepEvent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP_POSITION_KEY, mStepperLayout.getCurrentStepPosition());
        outState.putString(ADRESSE, mAdresse);
        outState.putString(DATES, mDates);
        outState.putString(TITRE, mTitle);
        outState.putString(DESCRIPTION, mDescription);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        final int currentStepPosition = mStepperLayout.getCurrentStepPosition();
        if (currentStepPosition > 0) {
            mStepperLayout.onBackClicked();
        } else {
            finish();
        }
    }

    @Override
    public void saveAddress(String a) {
        mAdresse = a;
    }

    @Override
    public void saveDates(String d) {
        mDates = d;
    }



    @Override
    public void saveTitle(String t) {
        mTitle = t;
    }

    @Override
    public void saveDescription(String d) {
        mDescription = d;
    }

    @Override
    public String getAddress() {
        return mAdresse;
    }

    @Override
    public String getDates() {
        return mDates;
    }

    @Override
    public String getTitre() {
        return mTitle;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }
}
