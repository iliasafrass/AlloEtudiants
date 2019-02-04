package com.example.a707446.alloetudiant.inscription.presenter;

import com.example.a707446.alloetudiant.inscription.repository.InscriptionRepository;
import com.example.a707446.alloetudiant.inscription.repository.InscriptionRepositoryImpl;
import com.example.a707446.alloetudiant.model.dto.ProfileDto;
import com.example.a707446.alloetudiant.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.model.payload.RegisterMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionPresenter implements InscriptionContract.Presenter {
    // Constants
    private static final String TAG = InscriptionPresenter.class.getSimpleName();

    // Globals
    private InscriptionContract.View mView;

    private InscriptionRepository inscriptionRepository = new InscriptionRepositoryImpl();

    public InscriptionPresenter(InscriptionContract.View view) {
        mView = view;
    }

    @Override
    public void startInscrire(RegisterProfileDto profileDto) {
        inscriptionRepository.inscription(profileDto)
        .enqueue(new Callback<RegisterMessage>() {
            @Override
            public void onResponse(Call<RegisterMessage> call, Response<RegisterMessage> response) {
                mView.toast("Code : "+response.code());;
            }

            @Override
            public void onFailure(Call<RegisterMessage> call, Throwable t) {
                mView.toast("Failure : " + t.toString());
            }
        });;
    }

    @Override
    public void startAnnuler() {
        mView.annuler();
    }

    @Override
    public void startToast(String message) {
        mView.toast(message);
    }

    @Override
    public void endInscrire(String message, int code) {
        mView.endInscrire(message, code);
    }


}
