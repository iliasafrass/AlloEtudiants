package com.example.a707446.alloetudiant.profil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.pojo.Profile;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.profil.presenter.ProfileContract;
import com.example.a707446.alloetudiant.profil.presenter.ProfilePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilFragment extends AbstractFragment implements ProfileContract.View {

    //Views
    @BindView(R.id.mail_user)
    public TextView mEmail;

    @BindView(R.id.description_profil)
    public TextView mBio;

    @BindView(R.id.name_user)
    public TextView mNom;

    @BindView(R.id.textView11)
    public TextView mTelephone;

    @BindView(R.id.textView12)
    public TextView mSexe;

    private ProfileContract.Presenter mPresenter;




    public ProfilFragment() {
        // Requires empty public constructor
    }

    public static  ProfilFragment newInstance() {return  new ProfilFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profil_fragment,null);
        mUnbinder = ButterKnife.bind(this,view);

        getActivity().setTitle(R.string.toolbar_profil);
        mPresenter = new ProfilePresenter(this);

        mPresenter.startgetProfileById(SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext()));
        return view;
    }

    @Override
    public void getProfileById(Profile profile) {
        mNom.setText(profile.getFirstName()+" "+profile.getLastName());
        mEmail.setText(profile.getEmail());
        mTelephone.setText(profile.getPhoneNumber());
        mSexe.setText(profile.getGender().toString());
        mBio.setText(profile.getBio());
    }
}
