package com.example.a707446.alloetudiant.publication.proposition.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.dto.OfferDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.enumeration.Subject;
import com.example.a707446.alloetudiant.general.model.enumeration.WeekDay;
import com.example.a707446.alloetudiant.publication.proposition.DataManager;
import com.example.a707446.alloetudiant.publication.proposition.presenter.PropositionContract;
import com.example.a707446.alloetudiant.publication.proposition.presenter.PropositionPresenter;
import com.google.gson.Gson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecapOfferFragment extends Fragment implements BlockingStep, PropositionContract.View {

    private final String profileId = SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext());
    //Views
    TextView titre;
    TextView date;
    TextView address;
    TextView matiere;
    TextView prix;
    TextView description;

    //variables

    String days = "";
    List<WeekDay> listDays;
    private PropositionContract.Presenter mPresenter;
    private String mTitre, mDescription, mMatiere, mAddress, mPrix, dispoStr;
    private DataManager dataManager;
    private List<Integer> listDispo;
    private List<Double> listDispoTmp;
    private OfferDto mOffer;

    public RecapOfferFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataManager = (DataManager) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mPresenter = new PropositionPresenter(this);
        return inflater.inflate(R.layout.fragment_recap_offer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titre = (TextView) view.findViewById(R.id.title_offer_recap);
        date = (TextView) view.findViewById(R.id.date_offer_recap);
        address = (TextView) view.findViewById(R.id.address_offer_recap);
        matiere = (TextView) view.findViewById(R.id.matiere_offer_recap);
        prix = (TextView) view.findViewById(R.id.prix_offer_recap);
        description = (TextView) view.findViewById(R.id.description_offer_recap);


    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //you can do anythings you want
                callback.goToNextStep();
            }
        }, 0L);// delay open another fragment,
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        //Toast.makeText(getActivity(), "onCompleted!", Toast.LENGTH_SHORT).show();
        Log.d("POST_OFFER", " mOffer = " + mOffer.toString());
        createOffer(mOffer);

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        listDays.clear();
        days = "";
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
        mMatiere = dataManager.getMatiere();

        mAddress = dataManager.getAddress();
        mPrix = dataManager.getPrix();
        dispoStr = dataManager.getDispo();
        listDispoTmp = new Gson().fromJson(dispoStr, ArrayList.class);


        listDispo = new ArrayList<>();
        listDays = new ArrayList<>();


        mOffer = new OfferDto(profileId, AnnounceType.OFFER, mTitre, mAddress, mDescription, Subject.valueOf(mMatiere), Float.parseFloat(mPrix), listDays);

        for (Double d : listDispoTmp) {
            Log.d("listDispo", " : " + d);
            listDispo.add(d.intValue());
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(mDescription);


        titre.setText(mTitre);
        description.setText(stringBuilder.toString());
        matiere.setText(mMatiere);


        address.setText(mAddress);
        date.setText(getDays());
        prix.setText(mPrix + " € par heure");
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    private void mydialogOK() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                //set icon
                .setIcon(R.drawable.ic_thankful)
                //set title
                .setTitle("Merci pour votre annonce !")
                //set message
                .setMessage("Votre proposition est bien publiée !")
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
                .setMessage("Le service publier proposition semble interrompue. Veuillez réessayer.")
                //set negative button
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                    }
                })
                .show();
    }

    private String getDays() {

        for (Integer i : listDispo) {
            switch (i) {
                case 1:
                    listDays.add(WeekDay.SUNDAY);
                    days = days.concat("Dimanche  ");
                    break;

                case 2:
                    listDays.add(WeekDay.MONDAY);
                    days = days.concat("Lundi  ");
                    break;

                case 3:
                    listDays.add(WeekDay.TUESDAY);
                    days = days.concat("Mardi  ");
                    break;

                case 4:
                    listDays.add(WeekDay.WEDNESDAY);
                    days = days.concat("Mercredi  ");
                    break;

                case 5:
                    listDays.add(WeekDay.THURSDAY);
                    days = days.concat("Jeudi  ");
                    break;

                case 6:
                    listDays.add(WeekDay.FRIDAY);
                    days = days.concat("Vendredi  ");
                    break;

                case 7:
                    listDays.add(WeekDay.SATURDAY);
                    days = days.concat("Samedi  ");
                    break;
            }
        }
        return days;
    }

    @Override
    public void createOffer(OfferDto offerDto) {
        mPresenter.startCreateOffer(offerDto);
    }

    @Override
    public void showSuccessMsg() {
        mydialogOK();
    }

    @Override
    public void showFailedMsg() {
        mydialogKO();
    }

}
