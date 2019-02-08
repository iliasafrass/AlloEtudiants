package com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRequestPresenter implements DetailRequestConstract.Presenter {
    // Constants
    private static final String TAG = DetailRequestConstract.class.getSimpleName();

    // Globals
    private DetailRequestConstract.View mView;
    private Repo mRepo;

    public DetailRequestPresenter(DetailRequestConstract.View view) {
        mView = view;
        mRepo = new RepoImpl();

    }
    @Override
    public void startgetRequestById(String id) {
        mRepo.getRequestById(id).enqueue(
                new Callback<Request>() {
                    @Override
                    public void onResponse(Call<Request> call, Response<Request> response) {
                        if(response.body() != null)
                            mView.getRequestById(response.body());
                        else
                            Log.d("response", " is null");
                    }

                    @Override
                    public void onFailure(Call<Request> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong in detail Request presenter ! :(");
                    }
                });
    }
}
