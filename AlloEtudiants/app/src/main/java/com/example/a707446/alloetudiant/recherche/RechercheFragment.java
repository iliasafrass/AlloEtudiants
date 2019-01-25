package com.example.a707446.alloetudiant.recherche;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.recherche.presenter.RechercheContract;
import com.example.a707446.alloetudiant.recherche.presenter.RecherchePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechercheFragment extends AbstractFragment implements RechercheContract.View {

    // Views
    @BindView(R.id.tabs)
    public TabLayout tabLayout;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;
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

        View view = inflater.inflate(R.layout.recherche_fragment, null);
        mUnbinder = ButterKnife.bind(this, view);


        getActivity().setTitle(R.string.toolbar_recherche);
        mPresenter = new RecherchePresenter(this);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch(tab.getPosition()){
                    case 0:
                        getActivity().setTitle(R.string.toolbar_recherche_demande);
                        break;
                    case 1:
                        getActivity().setTitle(R.string.toolbar_recherche_proposition);
                        break;
                    case 2:
                        getActivity().setTitle(R.string.toolbar_recherche_evenement);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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