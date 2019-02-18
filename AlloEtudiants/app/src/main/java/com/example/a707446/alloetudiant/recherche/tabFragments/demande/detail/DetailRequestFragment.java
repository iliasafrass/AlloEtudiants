package com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.BottomBar;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter.DetailRequestConstract;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter.DetailRequestPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

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

//    public BottomBar.EnableBottomBar enableBottomBar;

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

        getActivity().setTitle(R.string.toolbar_details);

        mPresenter = new DetailRequestPresenter(this);

        mPresenter.startgetRequestById(idRequest);
        return view;
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
/*        enableBottomBar = (BottomBar.EnableBottomBar)getContext();
        enableBottomBar.enableBottomBar();*/
    }

    @Override
    public void getRequestById(Request request) {
        Log.d("event", request.getId());
        this.mRequest = request;

        if(mRequest != null) {
            Toast.makeText(this.getView().getContext(), mRequest.getId(), Toast.LENGTH_LONG).show();
            Log.d("mEvent", mRequest.toString());
            title.setText(mRequest.getTitle());
            icon.setImageResource(R.drawable.ic_requests);
            date.setText(mRequest.getSlots().toString());
            description.setText(mRequest.getDescription());
            address.setText(mRequest.getAddress());
            getActivity().setTitle(R.string.toolbar_details);
        }
        else{
            Toast.makeText(this.getView().getContext(), "null", Toast.LENGTH_LONG).show();
            Log.d("mRrequest", "null");
        }
    }
}
