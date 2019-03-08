package com.example.a707446.alloetudiant.profil.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.pojo.Profile;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.Presenter {
    // Constants
    private static final String TAG = ProfileContract.class.getSimpleName();

    // Globals
    private ProfileContract.View mView;
    private Repo mRepo;

    public ProfilePresenter(ProfileContract.View view) {
        mView = view;
        mRepo = new RepoImpl();

    }


    @Override
    public void startgetProfileById(String id) {
        mRepo.getProfileById(id).enqueue(
                new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.body() != null)
                            mView.getProfileById(response.body());
                        else
                            Log.d("response", " is null");
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong in profile presenter ! :(");
                    }
                }
        );
    }
}
