package com.example.a707446.alloetudiant.recherche.tabFragments.proposition.detail.presenter;

import android.util.Log;

import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.dto.NotificationDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.pojo.Notification;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOfferPresenter implements DetailOfferContract.Presenter {
    // Constants
    private static final String TAG = DetailOfferContract.class.getSimpleName();

    // Globals
    private DetailOfferContract.View mView;
    private Repo mRepo;

    public DetailOfferPresenter(DetailOfferContract.View view) {
        mView = view;
        mRepo = new RepoImpl();

    }

    @Override
    public void startgetOfferById(String id) {
        mRepo.getOfferById(id).enqueue(
                new Callback<Offer>() {
                    @Override
                    public void onResponse(Call<Offer> call, Response<Offer> response) {
                        if (response.body() != null)
                            mView.getOfferById(response.body());
                        else
                            Log.d("response", " is null");
                    }

                    @Override
                    public void onFailure(Call<Offer> call, Throwable t) {
                        System.out.println("#########");
                        System.out.println("Something went wrong in detail Offer presenter ! :(");
                    }
                });
    }

    @Override
    public void startAskingOffer(Offer offer) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setAnnounceId(offer.getId());
        notificationDto.setAnnounceTitle(offer.getTitle());
        notificationDto.setAskerProfileId(SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext()));
        notificationDto.setAnnounceType(AnnounceType.OFFER);
        notificationDto.setAskedProfileId(offer.getProfileId());
        mRepo.askForAnnounce(notificationDto)
                .enqueue(new Callback<Notification>() {
                    @Override
                    public void onResponse(Call<Notification> call, Response<Notification> response) {
                        if (response.code() == 200) {
                            mView.showMessage("Vous avez bien envoyé une invitation.");
                        } else {
                            mView.showMessage("Vous avez déja envoyé cette invitation !");
                        }
                    }

                    @Override
                    public void onFailure(Call<Notification> call, Throwable t) {
                        mView.showMessage("Erreur inconnue. Veuillez réessayer plus tard.");
                    }
                });
    }
}
