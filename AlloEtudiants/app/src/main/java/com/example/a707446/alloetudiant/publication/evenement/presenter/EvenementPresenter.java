package com.example.a707446.alloetudiant.publication.evenement.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.model.dto.EventDto;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvenementPresenter implements EvenementContract.Presenter {
    // Constants
    private static final String TAG = EvenementPresenter.class.getSimpleName();

    // Globals
    private EvenementContract.View mView;
    private Repo mRepo;

    public EvenementPresenter(EvenementContract.View view) {
        mView = view;
        mRepo = new RepoImpl();
    }


    @Override
    public void startCreateEvent(EventDto eventDto) {
        mRepo.createEvent(eventDto).enqueue(
                new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if (response.body() != null)
                            mView.showSuccessMsg();
                        else {
                            mView.showFailedMsg();
                            try {
                                Log.d("POST_EVENT_ERROR", " response = " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("POST_EVENT", " response = " + response.body());

                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        Log.d("POST_EVENT", " Error = " + t.toString());
                        mView.showFailedMsg();
                    }
                }
        );
    }
}
