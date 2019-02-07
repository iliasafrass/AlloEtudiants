package com.example.a707446.alloetudiant.publication.proposition;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a707446.alloetudiant.publication.proposition.pageFragments.PropositionStep1;
import com.example.a707446.alloetudiant.publication.proposition.pageFragments.PropositionStep2;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        // TODO replace with switch case for all the steps
        if(position==1){
            return PropositionStep2.newInstance(position + 1, position == getCount() - 1);
        }
        return PropositionStep1.newInstance(position + 1, position == getCount() - 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
