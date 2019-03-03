package com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.model.pojo.Slot;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter.DetailRequestConstract;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter.DetailRequestPresenter;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRequestFragment extends AbstractFragment implements DetailRequestConstract.View{

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

    private DetailRequestConstract.Presenter mPresenter;

    private Request mRequest;
    private String idRequest;





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
        View view = inflater.inflate(R.layout.fragment_detail_request,null);
        mUnbinder = ButterKnife.bind(this,view);
        NavigationActivity.inDetail = true;
        getActivity().setTitle(R.string.toolbar_details);

        mPresenter = new DetailRequestPresenter(this);

        mPresenter.startgetRequestById(idRequest);
        return view;
    }

    @OnClick(R.id.reserver_demande)
    public void askRequest(){
        mPresenter.startAskingRequest(this.mRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationActivity.firstChildFragment = false;
        Toast.makeText(view.getContext(), idRequest, Toast.LENGTH_LONG).show();
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

        if(mRequest != null) {
            Toast.makeText(this.getView().getContext(), mRequest.getId(), Toast.LENGTH_LONG).show();
            Log.d("mEvent", mRequest.toString());
            title.setText(mRequest.getTitle());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            // TODO : Show days and price
            /*for (Slot slot: mRequest.getSlots()
                 ) {
                date.append(slot.getDay() + " de " + sdf.format(slot.getStartTime()) + " à " + sdf.format(slot.getEndTime()) + "\n" );
            }*/

            icon.setImageResource(R.drawable.ic_requests);
            description.setText(mRequest.getDescription());
            address.setText(mRequest.getAddress());
            getActivity().setTitle(R.string.toolbar_details);
        }
        else{
            Toast.makeText(this.getView().getContext(), "null", Toast.LENGTH_LONG).show();
            Log.d("mRrequest", "null");
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity().getApplicationContext(),"ERROR : " + error,Toast.LENGTH_LONG).show();
        errorSnackbar(error);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(),message,Toast.LENGTH_LONG).show();
        snackbar(message);
    }

    @Override
    public Context applicationContext() {
        return getActivity().getApplicationContext();
    }

    private void errorSnackbar(String error){
        TSnackbar errorSnackBar = TSnackbar.make(getActivity().findViewById(R.id.navigationActivity_toolbar),error,TSnackbar.LENGTH_LONG);
        errorSnackBar.setActionTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.errorSnackBarText));
        View snackBarView = errorSnackBar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackBarView.getLayoutParams();
        params.height = 80;
        snackBarView.setPadding(0,-20,0,-15);
        snackBarView.setLayoutParams(params);
        snackBarView.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.errorSnackBarBackground));
        TextView textView = (TextView) snackBarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.errorSnackBarText));
        textView.setTextSize(12);
        textView.setGravity(Gravity.CENTER);
        errorSnackBar.show();
    }

    private void snackbar(String message){
        TSnackbar errorSnackBar = TSnackbar.make(getActivity().findViewById(R.id.navigationActivity_toolbar),message,TSnackbar.LENGTH_LONG);
        errorSnackBar.setActionTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.snackBarText));
        View snackBarView = errorSnackBar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackBarView.getLayoutParams();
        params.height = 80;
        snackBarView.setPadding(0,-20,0,-15);
        snackBarView.setLayoutParams(params);
        snackBarView.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.snackBarBackground));
        TextView textView = (TextView) snackBarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.snackBarText));
        textView.setTextSize(12);
        textView.setGravity(Gravity.CENTER);
        errorSnackBar.show();
    }

}
