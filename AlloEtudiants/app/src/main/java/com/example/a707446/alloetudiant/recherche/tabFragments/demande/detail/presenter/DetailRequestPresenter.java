package com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.dto.NotificationDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.pojo.Notification;
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

    @Override
    public void startAskingRequest(Request request) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setAnnounceId(request.getId());
        notificationDto.setAnnounceTitle(request.getTitle());
        notificationDto.setAskerProfileId(SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext()));
        notificationDto.setAnnounceType(AnnounceType.REQUEST);
        notificationDto.setAskedProfileId(request.getProfileId());
        mRepo.askForAnnounce(notificationDto).enqueue(
                new Callback<Notification>() {
                    @Override
                    public void onResponse(Call<Notification> call, Response<Notification> response) {
                        if(response.code() == 200){
                           // mView.showToast("Notification created with id : " + response.body().getId());
                            mView.showToast("Vous avez bien envoyé une invitation.");
                        } else {
                            mView.showError("Vous avez déja envoyé cette invitation !");
                        }
                    }

                    @Override
                    public void onFailure(Call<Notification> call, Throwable t) {
                        mView.showToast("Erreur inconnue. Veuillez réessayer plus tard.");
                    }
                }
        );
    }
}
