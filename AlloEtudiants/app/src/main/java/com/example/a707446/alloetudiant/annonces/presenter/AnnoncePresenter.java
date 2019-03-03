package com.example.a707446.alloetudiant.annonces.presenter;

import android.widget.Toast;

import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.dto.AnnouncementDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.repository.Repo;
import com.example.a707446.alloetudiant.general.repository.RepoImpl;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.presenter.RequestPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnoncePresenter implements AnnonceContract.Presenter{

    private static final String TAG = AnnoncePresenter.class.getSimpleName();

    private AnnonceContract.View mView;
    private Repo mRepo;

    public AnnoncePresenter(AnnonceContract.View view){
        this.mView = view;
        mRepo = new RepoImpl();
    }

    @Override
    public void getAnnouncements() {
        mRepo.getMyAnnouncements(SharedPreferencesSingleton.getProfileId(BaseApplication.getAppContext()))
                .enqueue(new Callback<List<AnnouncementDto>>() {
                    @Override
                    public void onResponse(Call<List<AnnouncementDto>> call, Response<List<AnnouncementDto>> response) {
                        if(response.isSuccessful()){
                            mView.showAnnouncements(response.body());
                        } else {
                            mView.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<AnnouncementDto>> call, Throwable t) {
                        mView.showError();
                    }
                });
    }

    @Override
    public void deleteAnnouncement(AnnouncementDto announcement, final int position) {
        String id = null;
        AnnounceType announceType = null;
        switch (announcement.getAnnounceType()) {
            case EVENT:
                id = announcement.getEvent().getId();
                announceType = AnnounceType.EVENT;
                break;
            case REQUEST:
                id = announcement.getRequest().getId();
                announceType = AnnounceType.REQUEST;
                break;
            case OFFER:
                id = announcement.getOffer().getId();
                announceType = AnnounceType.OFFER;
                break;
            default:
                break;
        }
        mRepo.deleteAnnouncement(id, announceType)
                .enqueue(new Callback<List<AnnouncementDto>>() {
                    @Override
                    public void onResponse(Call<List<AnnouncementDto>> call, Response<List<AnnouncementDto>> response) {
                        if(response.isSuccessful()){
                            mView.showAnnouncementsAfterDelete(response.body(),position);
                        } else {
                            mView.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<AnnouncementDto>> call, Throwable t) {
                        mView.showError();
                    }
                });
    }

}
