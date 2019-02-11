package com.example.a707446.alloetudiant.publication.proposition;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.rakshakhegde.stepperindicator.StepperIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StartPublierServiceFragment extends AbstractFragment {

    StepperIndicator indicator;

//    @BindView(R.id.btn_next)
//    Button btnNext;
//
//    @BindView(R.id.btn_prev)
//    Button btnPrev;
//
//    @BindView(R.id.btn_submit)
//    Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_start_publier, null /*, false*/);

        mUnbinder = ButterKnife.bind(this,result);

        final ViewPager pager = result.findViewById(R.id.pager2);
        assert pager != null;
        pager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager()));

        indicator = result.findViewById(R.id.stepper_indicator2);
        // We keep last page for a "finishing" page
        indicator.setViewPager(pager, true);

        indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                pager.setCurrentItem(step, true);
            }
        });

//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position == 0) {
//                    btnPrev.setVisibility(View.GONE);
//                    btnNext.setVisibility(View.VISIBLE);
//                    btnSubmit.setVisibility(View.GONE);
//                } else if (position == 1) {
//                    btnPrev.setVisibility(View.VISIBLE);
//                    btnNext.setVisibility(View.VISIBLE);
//                    btnSubmit.setVisibility(View.GONE);
//                } else if (position == 2) {
//                    btnPrev.setVisibility(View.VISIBLE);
//                    btnNext.setVisibility(View.GONE);
//                    btnSubmit.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });

        getActivity().setTitle(R.string.toolbar_publier);

        return result;

    }


    public StartPublierServiceFragment() {
        // Required empty public constructor
    }
    public static StartPublierServiceFragment newInstance() {
        return new StartPublierServiceFragment();
    }

}
