package com.example.a707446.alloetudiant.publication.demande;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.a707446.alloetudiant.R;
import com.stepstone.stepper.StepperLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPublierDemande extends AppCompatActivity implements DataManager {

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private static final String TITRE = "titre";
    private static final String ADRESSE = "adresse";
    private static final String DESCRIPTION = "description";
    private static final String MATIERE = "matiere";
    private static final String GRADE = "grade";
    private static final String HOURS = "hours";
    private static final String PRIX = "prix";
    private static final String DISPO = "dispo";

    private StepperLayout mStepperLayout;
    private StepperAdapter mStepperAdapter;
    private String mAdresse;
    private Float mPrix;
    private String mDispo;
    private String mTitle;
    private String mDescription;
    private String mMatiere;
    private String mGrade;
    private int mHours;

    public StartPublierDemande() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_demande);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout_demande);
        mStepperAdapter = new StepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);


        int startingStepDemande = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        mAdresse = savedInstanceState != null ? savedInstanceState.getString(ADRESSE) : null;
        mPrix = savedInstanceState != null ? savedInstanceState.getFloat(PRIX) : 0f;
        mDispo = savedInstanceState != null ? savedInstanceState.getString(DISPO) : null;
        mTitle = savedInstanceState != null ? savedInstanceState.getString(TITRE) : null;
        mDescription = savedInstanceState != null ? savedInstanceState.getString(DESCRIPTION) : null;
        mMatiere = savedInstanceState != null ? savedInstanceState.getString(MATIERE) : null;
        mGrade = savedInstanceState != null ? savedInstanceState.getString(GRADE) : null;
        mHours = savedInstanceState != null ? savedInstanceState.getInt(HOURS) : 0;

        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this), startingStepDemande);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP_POSITION_KEY, mStepperLayout.getCurrentStepPosition());
        outState.putString(ADRESSE, mAdresse);
        outState.putFloat(PRIX, mPrix);
        outState.putString(DISPO, mDispo);
        outState.putString(TITRE, mTitle);
        outState.putString(DESCRIPTION, mDescription);
        outState.putString(MATIERE, mMatiere);
        outState.putString(GRADE, mGrade);
        outState.putInt(HOURS, mHours);
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
    public void savePrix(Float p) {
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
    public void saveGrade(String g) {
        mGrade = g;
    }

    @Override
    public void saveHours(int h) {
        mHours = h;
    }

    @Override
    public String getAddress() {
        return mAdresse;
    }

    @Override
    public Float getPrix() {
        return mPrix;
    }

    @Override
    public String getDispo() {
        return mDispo;
    }

    @Override
    public String getTitre() {
        return mTitle;
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
    public String getGrade() {
        return mGrade;
    }

    @Override
    public int getHours() {
        return mHours;
    }
}
