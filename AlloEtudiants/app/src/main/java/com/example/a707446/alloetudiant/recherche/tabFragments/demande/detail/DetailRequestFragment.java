package com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail;


import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter.DetailRequestConstract;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter.DetailRequestPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRequestFragment extends AbstractFragment implements DetailRequestConstract.View {

    //Views
    @BindView(R.id.title_request_detail)
    public TextView title;

    @BindView(R.id.request_image_detail)
    public ImageView icon;

    @BindView(R.id.date_request_detail)
    public TextView date;

    @BindView(R.id.description_request_detail)
    public TextView description;

    @BindView(R.id.address_request_detail)
    public TextView address;

    @BindView(R.id.prix_request_detail)
    public TextView price;

    @BindView(R.id.reserver_demande)
    public Button reserver;


    private DetailRequestConstract.Presenter mPresenter;

    private Request mRequest;
    private String idRequest;


    private ProgressDialog progressDialog;

    public DetailRequestFragment() {
        // Required empty public constructor
    }

    public static DetailRequestFragment newInstance() {
        return new DetailRequestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        if (getArguments() != null) {
            idRequest = getArguments().getString("id", "-1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_request, null);
        mUnbinder = ButterKnife.bind(this, view);
        NavigationActivity.inDetail = true;
        getActivity().setTitle(R.string.toolbar_details);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Chargement");

        mPresenter = new DetailRequestPresenter(this);

        mPresenter.startgetRequestById(idRequest);
        return view;
    }

    @OnClick(R.id.reserver_demande)
    public void askRequest() {
        progressDialog.show();
        mPresenter.startAskingRequest(this.mRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationActivity.firstChildFragment = false;

    }

    @Override
    public void onPause() {
        super.onPause();
        NavigationActivity.inDetail = false;
    }

    @Override
    public void getRequestById(Request request) {



        Log.d("event", request.getId());
        this.mRequest = request;

        if (mRequest != null) {
            if(!mRequest.getProfileId().equals(SharedPreferencesSingleton.getProfileId(getContext()))){
                reserver.setVisibility(View.VISIBLE);
            }
            Log.d("mEvent", mRequest.toString());
            title.setText(mRequest.getTitle());
            String listDays = "";
            String myDay = "";
            for (WeekDay day : mRequest.getDays()) {
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
            icon.setImageResource(R.drawable.ic_requests);
            description.setText(mRequest.getDescription());
            address.setText(mRequest.getAddress());
            if (request.getTotal() == 0) {
                price.setText("Gratuit");
            } else {
                price.setText(request.getTotal() + " € pour " + request.getHours() + " heure(s)");
            }
            getActivity().setTitle(R.string.toolbar_details);
        } else {
            Log.d("mRrequest", "null");
        }
    }

    @Override
    public void showError(String error) {
        progressDialog.dismiss();
        Toast.makeText(getActivity().getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String message) {
        progressDialog.dismiss();
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context applicationContext() {
        return getActivity().getApplicationContext();
    }


}
