package com.example.a707446.alloetudiant.publication.proposition.pageFragments;

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
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.enumeration.Subject;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class PropositionStep0 extends Fragment implements BlockingStep {

    //views
    TextInputLayout titre;
    TextInputLayout description;
    MaterialSpinner spinner;
    private String selectedSpinner;
    private String titreInput;
    private String descriptionInput;
    //variables
    private ArrayAdapter<String> spinnerAdapter;
    private List<String> listItems = new ArrayList<>();

    private DataManager dataManager;

    public PropositionStep0() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
         * spinner of subjects
         */
        selectedSpinner = "";
        spinner = (MaterialSpinner) getView().findViewById(R.id.matiere_proposition);
        titre = (TextInputLayout) getView().findViewById(R.id.title_proposition);
        description = (TextInputLayout) getView().findViewById(R.id.description_proposition);

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
        /*************************************************************/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.formulaire_publier_proposition0, container, false);

    }

    private void clearSpinnerItems() {
        listItems.clear();
    }

    private void initSpinnerItems() {
        listItems.add(Subject.MATHS.toString());
        listItems.add(Subject.PHYSICS.toString());
        listItems.add(Subject.CHEMISTRY.toString());
        listItems.add(Subject.MECHANICS.toString());
        listItems.add(Subject.COMPUTER_SCIENCE.toString());
    }


    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //you can do anythings you want
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

                if (!titreInput.isEmpty() && titreInput != null && !selectedSpinner.isEmpty() && selectedSpinner != null && !descriptionInput.isEmpty() && descriptionInput != null) {
                    dataManager.saveTitle(titreInput);
                    dataManager.saveDescription(descriptionInput);
                    dataManager.saveMatiere(selectedSpinner);
                    callback.goToNextStep();
                }

            }
        }, 0L);// delay open another fragment,

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataManager = (DataManager) context;
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
        Toast.makeText(getContext(), "onError! -> " + error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
