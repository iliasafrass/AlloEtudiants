package com.example.a707446.alloetudiant.recherche;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.recherche.presenter.RechercheContract;
import com.example.a707446.alloetudiant.recherche.presenter.RecherchePresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechercheFragment extends AbstractFragment implements RechercheContract.View {

    // Views

    // Globals
    private RechercheContract.Presenter mPresenter;

    public RechercheFragment() {
        // Requires empty public constructor
    }

    public static RechercheFragment newInstance() {
        return new RechercheFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recherche_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        getActivity().setTitle(R.string.toolbar_recherche);
        mPresenter = new RecherchePresenter(this);

        return view;
    }

    @OnClick(R.id.test)
    public void onTestClick() {
        mPresenter.start();
    }

    //region RechercheContract.View

    @Override
    public void test() {
        Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
    }

    //region
}