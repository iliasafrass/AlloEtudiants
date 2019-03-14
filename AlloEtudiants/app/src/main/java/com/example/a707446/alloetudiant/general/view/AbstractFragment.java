package com.example.a707446.alloetudiant.general.view;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;

public abstract class AbstractFragment extends Fragment {

    /**
     * Use to unbind the views binds with ButterKnife.
     */
    protected Unbinder mUnbinder;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
