package com.example.a707446.alloetudiant.publierService;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a707446.alloetudiant.publierService.pageFragments.PageFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    Context ctxt = null;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1, position == getCount() - 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
