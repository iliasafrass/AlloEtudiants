package com.example.a707446.alloetudiant.publication.proposition;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a707446.alloetudiant.publication.proposition.pageFragments.PropositionStep0;
import com.example.a707446.alloetudiant.publication.proposition.pageFragments.PropositionStep1;
import com.example.a707446.alloetudiant.publication.proposition.pageFragments.PropositionStep2;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PropositionStep0.newInstance(position + 1, position == getCount() - 1);
            case 1:
                return PropositionStep1.newInstance(position + 1, position == getCount() - 1);
            case 2:
                return PropositionStep2.newInstance(position + 1, position == getCount() - 1);
            default:
                return PropositionStep1.newInstance(position + 1, position == getCount() - 1);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
