package com.example.a707446.alloetudiant.recherche;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.a707446.alloetudiant.recherche.tabFragments.demande.RechercheDemandeFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.EventDetailFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.evenement.RechercheEvenementFragment;
import com.example.a707446.alloetudiant.recherche.tabFragments.proposition.RecherchePropositionFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new RechercheDemandeFragment();
            case 1:
                return new RecherchePropositionFragment();
            case 2:
                return new RechercheEvenementFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Demande";
            case 1:
                return "Proposition";
            case 2:
                return "Evenement";

            default:
                return null;
        }
    }
}