package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.RechercheFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter.DetailOfferContract;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter.DetailOfferPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailOfferFragment extends AbstractFragment implements DetailOfferContract.View{
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

    private DetailOfferContract.Presenter mPresenter;
    private Offer mOffer;
    private String idOffer;


    @OnClick(R.id.annuler_detail_offer)
    public void onAnnulerClick(){annuler();}

    private void annuler() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navigationActivity_fragmentContainer, RechercheFragment.newInstance());
        transaction.commit();
    }


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
        View view = inflater.inflate(R.layout.fragment_detail_offer,null);
        mUnbinder = ButterKnife.bind(this,view);
        NavigationActivity.inDetail = true;
        getActivity().setTitle(R.string.toolbar_details);
        mPresenter = new DetailOfferPresenter(this);

        mPresenter.startgetOfferById(idOffer);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationActivity.firstChildFragment = false;
        Toast.makeText(view.getContext(), idOffer, Toast.LENGTH_LONG).show();
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

        if(mOffer != null) {
            Toast.makeText(this.getView().getContext(), mOffer.getId(), Toast.LENGTH_LONG).show();
            Log.d("mOffer", mOffer.toString());
            title.setText(mOffer.getTitle());
            icon.setImageResource(R.drawable.ic_offers);
            date.setText(mOffer.getDays().toString());
            description.setText(mOffer.getDescription());
            address.setText(mOffer.getAddress());
            getActivity().setTitle(R.string.toolbar_details);
        }
        else{
            Toast.makeText(this.getView().getContext(), "null", Toast.LENGTH_LONG).show();
            Log.d("mOffer", "null");
        }
    }
}
