package com.example.a707446.alloetudiant.publication.proposition.pageFragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dpro.widgets.OnWeekdaysChangeListener;
import com.dpro.widgets.WeekdaysPicker;
import com.example.a707446.alloetudiant.R;
import com.google.gson.Gson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PropositionStep1 extends Fragment implements BlockingStep {

    private WeekdaysPicker widget;
    private TextInputLayout address;
    private TextInputLayout prix;

    public static List<Integer> selected_days;
    public static String addressInput;
    public static String prixInput;
    String dispo;

    private static final String ADDRESS = "address";
    private static final String PRIX = "prix";
    private static final String DISPO = "dispo";


    public PropositionStep1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.formulaire_publier_proposition1, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selected_days = Arrays.asList(Calendar.MONDAY,Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY);


        widget = (WeekdaysPicker) getView().findViewById(R.id.weekdays);
        address = (TextInputLayout) getView().findViewById(R.id.adresse_text_offer);
        prix = (TextInputLayout) getView().findViewById(R.id.prix_heure_offer);

        widget.setOnWeekdaysChangeListener(new OnWeekdaysChangeListener() {
            @Override
            public void onChange(View view, int clickedDayOfWeek, List<Integer> selectedDays) {
                // Do Something
                selected_days = selectedDays;
            }
        });


    }



    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //you can do anythings you want
                addressInput = address.getEditText().getText().toString();
                prixInput = prix.getEditText().getText().toString();
                dispo = new Gson().toJson(PropositionStep1.selected_days);

                if(addressInput.isEmpty() || address == null){
                    address.setError("adresse est obligatoire! ");
                }else
                    address.setError(null);
                if(prixInput.isEmpty() || prixInput== null){
                    prix.setError("prix est obligatoire! ");
                }else
                    prix.setError(null);

                if(selected_days.isEmpty() || selected_days == null){
                    Toast.makeText(getContext(), "disponibilité est obligatoire!", Toast.LENGTH_SHORT).show();
                }
                if( !addressInput.isEmpty() && addressInput != null && !selected_days.isEmpty() && selected_days != null && !prixInput.isEmpty() && prixInput != null) {

                    callback.goToNextStep();

                    RecapOfferFragment step3 = new RecapOfferFragment();
                    Bundle b3 = new Bundle();

                    Log.d("addressInput", " adresse is " + PropositionStep1.addressInput);
                    b3.putString(ADDRESS, PropositionStep1.addressInput);
                    b3.putString(PRIX, PropositionStep1.prixInput);
                    b3.putString(DISPO, dispo);
                    step3.setArguments(b3);
                }
            }
        }, 0L);// delay open another fragment,
        RecapOfferFragment step3 = new RecapOfferFragment();
        Bundle b3 = new Bundle();

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
        //you can do anythings you want

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

}
