package com.example.a707446.alloetudiant.notifications.presenter;

import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.model.pojo.Notification;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionContract;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsPresenter implements NotificationsContract.Presenter {

    // Constants
    private static final String TAG = InscriptionPresenter.class.getSimpleName();

    // Globals
    private NotificationsContract.View mView;
    private Repo mRepo;

    public NotificationsPresenter(NotificationsContract.View mView) {
        this.mView = mView;
        mRepo = new RepoImpl();
    }

    @Override
    public void getNotifications() {
        mRepo.getNotificationsByProfileId(/*SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext())*/"5c52008b9c184a1b3f724309",true)
                .enqueue(
                        new Callback<List<NotificationProfileDto>>() {
                            @Override
                            public void onResponse(Call<List<NotificationProfileDto>> call, Response<List<NotificationProfileDto>> response) {
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

}
