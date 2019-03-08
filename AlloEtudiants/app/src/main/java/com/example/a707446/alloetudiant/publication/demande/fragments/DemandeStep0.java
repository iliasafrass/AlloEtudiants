package com.example.a707446.alloetudiant.publication.demande.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.enumeration.Subject;
import com.example.a707446.alloetudiant.general.model.enumeration.Grade;
import com.example.a707446.alloetudiant.publication.demande.DataManager;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemandeStep0 extends Fragment  implements BlockingStep {

    //variables
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayAdapter<String> spinnerNiveauAdapter;
    private List<String> listItems = new ArrayList<>();
    private List<String> listItemsNiveau = new ArrayList<>();
    MaterialSpinner spinner;
    MaterialSpinner spinnerNiveau;
    private String selectedSpinner;
    private String selectedSpinnerNiveau;
    TextInputLayout titre;
    TextInputLayout description;
    private String titreInput;
    private String descriptionInput;


    private DataManager dataManager;

    public DemandeStep0() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titre = (TextInputLayout) getView().findViewById(R.id.title_demande);
        description = (TextInputLayout) getView().findViewById(R.id.description_demande);

        //region sppiner Matiere
        selectedSpinner = "";
        spinner = (MaterialSpinner) getView().findViewById(R.id.matiere_demande);

        clearSpinnerItems();
        initSpinnerItems();
        spinnerAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, listItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1)// -1 hint choose..
                {
                    selectedSpinner = spinner.getItemAtPosition(position).toString();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //region sppiner niveau
        selectedSpinnerNiveau = "";
        spinnerNiveau  = (MaterialSpinner) getView().findViewById(R.id.niveau_demande);

        clearSpinnerNiveauItems();
        initSpinnerNiveauItems();
        spinnerNiveauAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, listItemsNiveau);
        spinnerNiveauAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNiveau.setAdapter(spinnerNiveauAdapter);
        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1)// -1 hint choose..
                {
                    selectedSpinnerNiveau = spinnerNiveau.getItemAtPosition(position).toString();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

    }

    private void initSpinnerNiveauItems() {
        listItemsNiveau.add(Grade.L1.toString());
        listItemsNiveau.add(Grade.L2.toString());
        listItemsNiveau.add(Grade.L3.toString());
        listItemsNiveau.add(Grade.M1.toString());
        listItemsNiveau.add(Grade.M2.toString());
    }

    private void clearSpinnerNiveauItems() {
        listItemsNiveau.clear();
    }

    private void clearSpinnerItems() {
        listItems.clear();
    }

    private void initSpinnerItems() {
        listItems.add(Subject.MATHEMATIQUES.toString());
        listItems.add(Subject.PHYSIQUE.toString());
        listItems.add(Subject.CHIMIE.toString());
        listItems.add(Subject.MECANIQUE.toString());
        listItems.add(Subject.INFORMATIQUE.toString());
        listItems.add(Subject.ELECTRONIQUE.toString());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demande_step0, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataManager = (DataManager) context;
    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                titreInput = titre.getEditText().getText().toString();
                descriptionInput = description.getEditText().getText().toString();

                if (titreInput.isEmpty() || titreInput == null) {
                    titre.setError("titre est obligatoire! ");
                } else
                    titre.setError(null);

                if (descriptionInput.isEmpty() || descriptionInput == null) {
                    description.setError("description est obligatoire! ");
                } else
                    description.setError(null);

                if (selectedSpinner.isEmpty() || selectedSpinner == null) {
                    spinner.setError("matiere est obligatoire! ");
                } else
                    spinner.setError(null);

                if (selectedSpinnerNiveau.isEmpty() || selectedSpinnerNiveau == null) {
                    spinnerNiveau.setError("niveau est obligatoire! ");
                } else
                    spinnerNiveau.setError(null);

                if (!titreInput.isEmpty() && titreInput != null && !selectedSpinner.isEmpty() && selectedSpinner != null && !selectedSpinnerNiveau.isEmpty() && selectedSpinnerNiveau != null && !descriptionInput.isEmpty() && descriptionInput != null) {
                    dataManager.saveTitle(titreInput);
                    dataManager.saveDescription(descriptionInput);
                    dataManager.saveMatiere(selectedSpinner);
                    dataManager.saveGrade(selectedSpinnerNiveau);
                    callback.goToNextStep();
                }
            }
        }, 0L);// delay open another fragment,
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
