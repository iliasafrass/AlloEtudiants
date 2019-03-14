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
import android.widget.Toast;

import com.dpro.widgets.OnWeekdaysChangeListener;
import com.dpro.widgets.WeekdaysPicker;
import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.publication.demande.DataManager;
import com.google.gson.Gson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemandeStep1 extends Fragment implements BlockingStep {

    private WeekdaysPicker widget;
    private TextInputLayout address;
    private TextInputLayout prix;
    private TextInputLayout heures;

    private List<Integer> selected_days;
    private String addressInput;
    private String prixInput;
    private String dispo;
    private String heuresInput;

    private DataManager dataManager;

    public DemandeStep1() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selected_days = Arrays.asList(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY);


        widget = (WeekdaysPicker) getView().findViewById(R.id.weekdays);
        address = (TextInputLayout) getView().findViewById(R.id.adresse);
        prix = (TextInputLayout) getView().findViewById(R.id.prix);
        heures = (TextInputLayout) getView().findViewById(R.id.heure);

        widget.setOnWeekdaysChangeListener(new OnWeekdaysChangeListener() {
            @Override
            public void onChange(View view, int clickedDayOfWeek, List<Integer> selectedDays) {
                // Do Something
                selected_days = selectedDays;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demande_step1, container, false);
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

                //you can do anythings you want
                addressInput = address.getEditText().getText().toString();
                prixInput = prix.getEditText().getText().toString();
                heuresInput = heures.getEditText().getText().toString();
                dispo = new Gson().toJson(selected_days);

                if (addressInput.isEmpty() || address == null) {
                    address.setError("adresse est obligatoire! ");
                } else
                    address.setError(null);
                if (prixInput.isEmpty() || prixInput == null) {
                    prix.setError("total est obligatoire! ");
                } else
                    prix.setError(null);
                if (heuresInput.isEmpty() || heuresInput == null) {
                    heures.setError("nombre d'heures est obligatoire! ");
                } else
                    heures.setError(null);

                if (selected_days.isEmpty() || selected_days == null) {
                    Toast.makeText(getContext(), "disponibilité est obligatoire!", Toast.LENGTH_SHORT).show();
                }
                if (!heuresInput.isEmpty() && heuresInput != null && !addressInput.isEmpty() && addressInput != null && !selected_days.isEmpty() && selected_days != null && !prixInput.isEmpty() && prixInput != null) {
                    dataManager.saveHours(Integer.parseInt(heuresInput));
                    dataManager.saveAddress(addressInput);
                    dataManager.savePrix(Float.valueOf(prixInput));
                    dataManager.saveDispo(dispo);
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
