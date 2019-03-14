package com.example.a707446.alloetudiant.inscription.presenter;

import com.example.a707446.alloetudiant.general.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.general.model.payload.RegisterMessage;
import com.example.a707446.alloetudiant.general.repository.inscription.InscriptionRepository;
import com.example.a707446.alloetudiant.general.repository.inscription.InscriptionRepositoryImpl;

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
                        if (response.code() == 200) {
                            startAnnuler();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterMessage> call, Throwable t) {
                        mView.toast("Erreur inconnue. Veuillez réssayer plus tard.");
                    }
                });
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
