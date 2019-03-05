package com.example.a707446.alloetudiant.publication.evenement.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.dto.EventDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.publication.evenement.DataManager;
import com.example.a707446.alloetudiant.publication.evenement.presenter.EvenementContract;
import com.example.a707446.alloetudiant.publication.evenement.presenter.EvenementPresenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventStep1 extends Fragment implements BlockingStep, EvenementContract.View {

    //private final String profileId = SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext());
    private final String profileId = "5c3d00eb349dbb2908cbaf99";
    //Views
    TextView titre;
    TextView date;
    TextView address;
    TextView description;

    //variables

    private EvenementContract.Presenter mPresenter;
    private String mTitre, mDescription, mAddress,mDate;
    private DataManager dataManager;
    private EventDto mEvent;
    private String dateString;
    public EventStep1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataManager = (DataManager) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mPresenter = new EvenementPresenter(this);
        return inflater.inflate(R.layout.fragment_event_step1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        titre = (TextView) view.findViewById(R.id.title_event_detail);
        date = (TextView) view.findViewById(R.id.date_event_detail);
        address = (TextView) view.findViewById(R.id.address_event_detail);
        description = (TextView) view.findViewById(R.id.description_event_detail);


    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        Log.d("POST_EVENT", " mEvent = " + mEvent.toString());
        createEvent(mEvent);
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
        mTitre = dataManager.getTitre();
        mDescription = dataManager.getDescription();
        mAddress = dataManager.getAddress();
        mDate = dataManager.getDates();

        String[] parts = mDate.split(" ");
        String day = parts[0];
        String hour = parts[1];

        String[] parts2 = day.split("-");
        int YY = Integer.parseInt(parts2[0]);
        int MM = Integer.parseInt(parts2[1]);
        int DD = Integer.parseInt(parts2[2]);

        String[] parts3 = hour.split(":");
        int HH = Integer.parseInt(parts3[0]);
        int MIN = Integer.parseInt(parts3[1]);


        dateString = DD+"/"+MM+"/"+YY+" "+HH+":"+MIN;

        Date myDate = new Date(YY-1900,MM,DD,HH,MIN);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        dateString = dateFormat.format(myDate);


        mEvent = new EventDto(profileId, AnnounceType.EVENT,mTitre,mAddress,mDescription, dateString);

        titre.setText(mTitre);
        description.setText(mDescription);
        address.setText(mAddress);
        date.setText(dateString);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void createEvent(EventDto eventDto) {
        mPresenter.startCreateEvent(eventDto);
    }

    @Override
    public void showSuccessMsg() {
        mydialogOK();
    }

    @Override
    public void showFailedMsg() {
        mydialogKO();
    }

    private void mydialogOK() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                //set icon
                .setIcon(R.drawable.ic_thankful)
                //set title
                .setTitle("Merci pour votre annonce !")
                //set message
                .setMessage("Votre evenement est bien publiée !")
                //set positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        getActivity().finish();
                    }
                })
                .show();
    }

    private void mydialogKO() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Attention!")
                //set message
                .setMessage("Le service publier evenement semble interrompue. Veuillez réessayer.")
                //set negative button
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        getActivity().finish();
                    }
                })
                .show();
    }
}