package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.enumeration.WeekDay;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter.DetailOfferContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter.DetailOfferPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailOfferFragment extends AbstractFragment implements DetailOfferContract.View {
    //Views
    @BindView(R.id.title_offer_detail)
    public TextView title;

    @BindView(R.id.offer_image_detail)
    public ImageView icon;

    @BindView(R.id.date_offer_detail)
    public TextView date;

    @BindView(R.id.description_offer_recap)
    public TextView description;

    @BindView(R.id.address_offer_detail)
    public TextView address;

    @BindView(R.id.price_offer_detail)
    public TextView price;

    @BindView(R.id.reserver_proposition)
    public Button reserver;

    private DetailOfferContract.Presenter mPresenter;
    private Offer mOffer;
    private String idOffer;

    private ProgressDialog progressDialog;

    public DetailOfferFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        if (getArguments() != null) {
            idOffer = getArguments().getString("id", "-1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_offer, null);
        mUnbinder = ButterKnife.bind(this, view);
        NavigationActivity.inDetail = true;
        getActivity().setTitle(R.string.toolbar_details);
        mPresenter = new DetailOfferPresenter(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Chargement");

        mPresenter.startgetOfferById(idOffer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationActivity.firstChildFragment = false;
//        Toast.makeText(view.getContext(), idOffer, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        NavigationActivity.inDetail = false;
    }

    @Override
    public void getOfferById(Offer offer) {
        Log.d("event", offer.getId());
        this.mOffer = offer;

        if (mOffer != null) {
            if (!mOffer.getProfileId().equals(SharedPreferencesSingleton.getProfileId(getContext()))) {
                reserver.setVisibility(View.VISIBLE);
            }
            Log.d("mOffer", mOffer.toString());
            title.setText(mOffer.getTitle());
            icon.setImageResource(R.drawable.ic_offers);

            String listDays = "";
            String myDay = "";
            for (WeekDay day : mOffer.getDays()) {
                myDay = day.name();
                switch (day) {
                    case MONDAY:
                        myDay = "Lundi";
                        break;
                    case TUESDAY:
                        myDay = "Mardi";
                        break;

                    case WEDNESDAY:
                        myDay = "Mercredi";
                        break;

                    case THURSDAY:
                        myDay = "Jeudi";
                        break;
                    case FRIDAY:
                        myDay = "Vendredi";
                        break;

                    case SATURDAY:
                        myDay = "Samedi";
                        break;
                    case SUNDAY:
                        myDay = "Dimanche";
                        break;
                }
                listDays += myDay + "  ";
            }

            date.setText(listDays);

            description.setText(mOffer.getDescription());
            address.setText(mOffer.getAddress());
            if (offer.getPrice() == 0) {
                price.setText("Gratuit");
            } else {
                price.setText(offer.getPrice() + " € par heure");
            }
            getActivity().setTitle(R.string.toolbar_details);
        } else {
            Log.d("mOffer", "null");
        }
    }

    @OnClick(R.id.reserver_proposition)
    public void askOffer() {
        progressDialog.show();
        mPresenter.startAskingOffer(this.mOffer);
    }

    @Override
    public void showMessage(String message) {
        progressDialog.dismiss();
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
    }
}
