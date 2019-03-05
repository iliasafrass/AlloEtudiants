package com.example.a707446.alloetudiant.publication.evenement.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.publication.evenement.DataManager;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventStep0 extends Fragment implements BlockingStep {

    private TextInputLayout titre;
    private TextInputLayout description;
    private TextInputLayout adresse;
    private TextInputLayout date;
    private TextInputLayout heure;

    private TextInputLayout mDisplayDate;
    private TextInputLayout mDisplayHeure;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private String titreInput;
    private String dateInput;
    private String heureInput;
    private String adresseInput;
    private String descriptionInput;
    private int year;
    private int month;
    private int day;

    private TimePickerDialog timePickerDialog;
    private Calendar calendar;
    private int currentHour;
    private int currentMinute;
    private String amPm;

    private DataManager dataManager;

    public EventStep0() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_step0, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDisplayDate = (TextInputLayout) view.findViewById(R.id.datePicker);
        mDisplayHeure = (TextInputLayout) view.findViewById(R.id.heurePicker);
        titre = (TextInputLayout) view.findViewById(R.id.title_event);
        description = (TextInputLayout) view.findViewById(R.id.description_event);
        adresse = (TextInputLayout) view.findViewById(R.id.adresse);

    //region DatePicker

        mDisplayDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d("Select Date", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year ;
                mDisplayDate.getEditText().setText(date);
            }
        };
    //endregion

    //region HeurePicker
        mDisplayHeure.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        mDisplayHeure.getEditText().setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });
    //endregion
    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //you can do anythings you want
                titreInput = titre.getEditText().getText().toString();
                descriptionInput = description.getEditText().getText().toString();
                adresseInput = adresse.getEditText().getText().toString();
                dateInput = mDisplayDate.getEditText().getText().toString();
                heureInput = mDisplayHeure.getEditText().getText().toString();

                if (titreInput.isEmpty() || titreInput == null) {
                    titre.setError("titre est obligatoire! ");
                } else
                    titre.setError(null);

                if (descriptionInput.isEmpty() || descriptionInput == null) {
                    description.setError("description est obligatoire! ");
                } else
                    description.setError(null);

                if (adresseInput.isEmpty() || adresseInput == null) {
                    adresse.setError("adresse est obligatoire! ");
                } else
                    adresse.setError(null);

                if (dateInput.isEmpty() || dateInput == null) {
                    mDisplayDate.setError("date est obligatoire! ");

                } else {
                    mDisplayDate.setError(null);
                }

                if (heureInput.isEmpty() || heureInput == null) {
                    mDisplayHeure.setError("heure est obligatoire! ");

                } else {
                    mDisplayHeure.setError(null);
                }

                if (!dateInput.isEmpty() && dateInput != null && !heureInput.isEmpty() && heureInput != null && !titreInput.isEmpty() && titreInput != null && !adresseInput.isEmpty() && adresseInput != null  && !descriptionInput.isEmpty() && descriptionInput != null) {
                    dataManager.saveTitle(titreInput);
                    dataManager.saveDescription(descriptionInput);
                    dataManager.saveAddress(adresseInput);
                    dataManager.saveDates(dateInput+" "+heureInput);
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

    }
}
