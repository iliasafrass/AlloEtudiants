package com.example.a707446.alloetudiant.publication.demande.fragments;


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
import com.example.a707446.alloetudiant.general.model.dto.RequestDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.enumeration.Grade;
import com.example.a707446.alloetudiant.general.model.enumeration.Subject;
import com.example.a707446.alloetudiant.general.model.enumeration.WeekDay;
import com.example.a707446.alloetudiant.publication.demande.DataManager;
import com.example.a707446.alloetudiant.publication.demande.presenter.DemandeContract;
import com.example.a707446.alloetudiant.publication.demande.presenter.DemandePresenter;
import com.google.gson.Gson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecapRequestFragment extends Fragment implements BlockingStep,DemandeContract.View  {

    private final String profileId = SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext());

    //Views
    TextView titre;
    TextView description;
    TextView address;
    TextView date;
    TextView matiere;
    TextView niveau;
    TextView total;

    //variables

    String days = "";
    List<WeekDay> listDays;
    private DemandeContract.Presenter mPresenter;
    private String mTitre, mDescription, mMatiere, mAddress, dispoStr,mNiveau;
    private int mHours;
    private float mPrix;
    private float mTotal;

    private DataManager dataManager;
    private List<Integer> listDispo;
    private List<Double> listDispoTmp;
    private RequestDto mRequest;

    public RecapRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mPresenter = new DemandePresenter(this);
        return inflater.inflate(R.layout.fragment_recap_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titre = (TextView) view.findViewById(R.id.title_request_detail);
        niveau = (TextView) view.findViewById(R.id.niveau_request_detail);
        date = (TextView) view.findViewById(R.id.date_request_detail);
        address = (TextView) view.findViewById(R.id.address_request_detail);
        matiere = (TextView) view.findViewById(R.id.matiere_request_detail);
        total = (TextView) view.findViewById(R.id.total_request_detail);
        description = (TextView) view.findViewById(R.id.description_request_detail);


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
        createRequest(mRequest);
    }

    public void createRequest(RequestDto requestDto) {
        mPresenter.startCreateRequest(requestDto);
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
        mNiveau = dataManager.getGrade();
        mAddress = dataManager.getAddress();
        mPrix = dataManager.getPrix();
        mHours = dataManager.getHours();
        mTotal = mPrix*mHours;
        dispoStr = dataManager.getDispo();
        listDispoTmp = new Gson().fromJson(dispoStr, ArrayList.class);


        listDispo = new ArrayList<>();
        listDays = new ArrayList<>();


        mRequest = new RequestDto(profileId, AnnounceType.REQUEST, mTitre, mAddress, mDescription, Subject.valueOf(mMatiere),Grade.valueOf(mNiveau),mHours,mPrix,mPrix*mHours, listDays);

        for (Double d : listDispoTmp) {
            Log.d("listDispo", " : " + d);
            listDispo.add(d.intValue());
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(mDescription);


        titre.setText(mTitre);
        description.setText(stringBuilder.toString());
        matiere.setText(mMatiere);
        niveau.setText(mNiveau);

        address.setText(mAddress);
        date.setText(getDays());
        total.setText(mTotal + " €");
    }

    private void mydialogOK() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                //set icon
                .setIcon(R.drawable.ic_thankful)
                //set title
                .setTitle(R.string.annonce_thx)
                //set message
                .setMessage(R.string.confirm_sent)
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
                .setMessage(R.string.error_demande_server)
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

    private String getDays() {

        for (Integer i : listDispo) {
            switch (i) {
                case 1:
                    listDays.add(WeekDay.SUNDAY);
                    days = days.concat("Dimanche");
                    break;

                case 2:
                    listDays.add(WeekDay.MONDAY);
                    days = days.concat("-Lundi");
                    break;

                case 3:
                    listDays.add(WeekDay.TUESDAY);
                    days = days.concat("-Mardi");
                    break;

                case 4:
                    listDays.add(WeekDay.WEDNESDAY);
                    days = days.concat("-Mercredi");
                    break;

                case 5:
                    listDays.add(WeekDay.THURSDAY);
                    days = days.concat("-Jeudi");
                    break;

                case 6:
                    listDays.add(WeekDay.FRIDAY);
                    days = days.concat("-Vendredi");
                    break;

                case 7:
                    listDays.add(WeekDay.SATURDAY);
                    days = days.concat("-Samedi");
                    break;
            }
        }
        return days;
    }
    @Override
    public void onError(@NonNull VerificationError error) {

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
