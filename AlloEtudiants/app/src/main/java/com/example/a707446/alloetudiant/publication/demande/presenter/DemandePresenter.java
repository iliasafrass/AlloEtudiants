package com.example.a707446.alloetudiant.publication.demande.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.dto.RequestDto;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemandePresenter implements DemandeContract.Presenter {
    // Constants
    private static final String TAG = DemandePresenter.class.getSimpleName();

    // Globals
    private DemandeContract.View mView;
    private Repo mRepo;

    public DemandePresenter(DemandeContract.View view) {
        mView = view;
        mRepo = new RepoImpl();
    }


    @Override
    public void startCreateRequest(RequestDto requestDto) {
        mRepo.createRequest(requestDto).enqueue(
                new Callback<Request>() {
                    @Override
                    public void onResponse(Call<Request> call, Response<Request> response) {
                        if(response.body() != null)
                            mView.showSuccessMsg();
                        else
                            mView.showFailedMsg();
                        Log.d("POST_OFFER", " response = "+response.body());
                    }

                    @Override
                    public void onFailure(Call<Request> call, Throwable t) {
                        Log.d("POST_OFFER", " Error = "+t.toString());
                        mView.showFailedMsg();
                    }
                }
        );
    }
}
