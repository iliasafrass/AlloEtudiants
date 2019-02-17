package com.example.a707446.alloetudiant.publication.proposition.pageFragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.google.gson.Gson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecapOfferFragment extends Fragment implements BlockingStep{

    //Views
    TextView titre;
    TextView date;
    TextView address;
    TextView matiere;
    TextView prix;
    TextView description;

    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";

    private static final String TITRE = "titre";
    private static final String DESCRIPTION = "description";
    private static final String MATIERE = "matiere";
    private static final String ADDRESS = "address";
    private static final String PRIX = "prix";
    private static final String DISPO = "dispo";


    private String mTitre,mDescription,mMatiere,mAddress,mPrix,dispoStr;


    private List<Integer> mDispo;

    public RecapOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        if (getArguments() != null) {
            mTitre = getArguments().getString(TITRE, "titre");
            mDescription = getArguments().getString(DESCRIPTION, "description");
            mMatiere = getArguments().getString(MATIERE, "matiere");

/*            mAddress = getArguments().getString(ADDRESS, "adresse_default");
            mPrix = getArguments().getString(PRIX, "prix_default");
            dispoStr = getArguments().getString(DISPO, "dispo");
            //mDispo = new Gson().fromJson(dispoStr, ArrayList.class);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        titre.setText(mTitre);
        description.setText(mDescription);
        matiere.setText(mMatiere);

        address.setText(mAddress);
        date.setText(dispoStr);
        prix.setText(mPrix);


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
        mydialog();
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
//        Toast.makeText(getContext(), dispoStr+";"+mAddress+";"+mPrix, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    private void mydialog() {
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

}
