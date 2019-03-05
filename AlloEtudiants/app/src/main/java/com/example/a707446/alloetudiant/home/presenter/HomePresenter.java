package com.example.a707446.alloetudiant.home.presenter;

import android.content.Intent;

import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;
import com.example.a707446.alloetudiant.notifications.presenter.NotificationsContract;
import com.example.a707446.alloetudiant.start.StartActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter{

    // Globals
    private HomeContract.View mView;
    private Repo mRepo;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
        mRepo = new RepoImpl();
    }

    @Override
    public void getNotifications() {
        mRepo.getNotificationsByProfileId(SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext()),false)
                .enqueue(
                        new Callback<List<NotificationProfileDto>>() {
                            @Override
                            public void onResponse(Call<List<NotificationProfileDto>> call, Response<List<NotificationProfileDto>> response) {
                                if(response.code() == 403){
                                    SharedPreferencesSingleton.clear(BaseApplication.getAppContext());
                                    System.out.print(response.errorBody());
                                    mView.goToStartActivity();
                                }
                                if(response.isSuccessful()){
                                    mView.showNotifications(response.body());
                                } else {
                                    mView.showError("Internal server error");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<NotificationProfileDto>> call, Throwable t) {
                                mView.showError(t.toString());
                            }
                        }
                );
    }

    @Override
    public void sendNotificationAnswer(NotificationProfileDto dto, final int position) {
        mRepo.sendNotificationAnswer(dto.getNotification().getId(), SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext()),dto)
                .enqueue(new Callback<List<NotificationProfileDto>>() {
                    @Override
                    public void onResponse(Call<List<NotificationProfileDto>> call, Response<List<NotificationProfileDto>> response) {
                        if(response.isSuccessful()){
                            mView.showNotificationsAfterAnswer(response.body(), position);
                        } else {
                            mView.showError("Internal server error");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NotificationProfileDto>> call, Throwable t) {
                        mView.showError(t.toString());
                    }
                });
    }
}
