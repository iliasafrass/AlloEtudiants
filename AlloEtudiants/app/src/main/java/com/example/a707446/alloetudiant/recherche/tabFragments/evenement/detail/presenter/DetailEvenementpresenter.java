package com.example.a707446.alloetudiant.recherche.tabFragments.evenement.detail.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEvenementpresenter implements DetailEvenementContract.Presenter {

    // Constants
    private static final String TAG = DetailEvenementContract.class.getSimpleName();

    // Globals
    private DetailEvenementContract.View mView;
    private Repo mRepo;

    public DetailEvenementpresenter(DetailEvenementContract.View view) {
        mView = view;
        mRepo = new RepoImpl();

    }

    @Override
    public void startgetEventById(String id) {
        mRepo.getEventById(id).enqueue(
                new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if (response.body() != null)
                            mView.getEventById(response.body());
                        else
                            Log.d("response", " is null");
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong in detail Event presenter ! :(");
                    }
                });
    }
}
