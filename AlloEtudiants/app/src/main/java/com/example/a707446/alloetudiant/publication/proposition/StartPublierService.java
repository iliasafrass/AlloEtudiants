package com.example.a707446.alloetudiant.publication.proposition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.publication.proposition.pageFragments.DataManager;
import com.stepstone.stepper.StepperLayout;


public class StartPublierService extends AppCompatActivity implements DataManager {

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private static final String ADRESSE = "adresse";
    private static final String PRIX = "prix";
    private static final String DISPO = "dispo";
    private static final String TITRE = "titre";
    private static final String DESCRIPTION = "description";
    private static final String MATIERE = "matiere";

    private StepperLayout mStepperLayout;
    private StepperAdapter mStepperAdapter;
    private String mAdresse, mPrix, mDispo, mTitle, mDescription, mMatiere;


    public StartPublierService() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_proposition);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout_proposition);
        mStepperAdapter = new StepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);

        int startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        mAdresse = savedInstanceState != null ? savedInstanceState.getString(ADRESSE) : null;
        mPrix = savedInstanceState != null ? savedInstanceState.getString(PRIX) : null;
        mDispo = savedInstanceState != null ? savedInstanceState.getString(DISPO) : null;


        mTitle = savedInstanceState != null ? savedInstanceState.getString(TITRE) : null;
        mDescription = savedInstanceState != null ? savedInstanceState.getString(DESCRIPTION) : null;
        mMatiere = savedInstanceState != null ? savedInstanceState.getString(MATIERE) : null;
        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this), startingStepPosition);


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP_POSITION_KEY, mStepperLayout.getCurrentStepPosition());
        outState.putString(ADRESSE, mAdresse);
        outState.putString(PRIX, mPrix);
        outState.putString(DISPO, mDispo);
        outState.putString(TITRE, mTitle);
        outState.putString(DESCRIPTION, mDescription);
        outState.putString(MATIERE, mMatiere);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void saveAddress(String a) {
        mAdresse = a;
    }

    @Override
    public void savePrix(String p) {
        mPrix = p;
    }

    @Override
    public void saveDispo(String d) {
        mDispo = d;
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
    public void saveMatiere(String m) {
        mMatiere = m;
    }

    @Override
    public String getAddress() {
        return mAdresse;
    }

    @Override
    public String getPrix() {
        return mPrix;
    }

    @Override
    public String getDispo() {
        return mDispo;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public String getMatiere() {
        return mMatiere;
    }

    @Override
    public String getTitre() {
        return mTitle;
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
}
