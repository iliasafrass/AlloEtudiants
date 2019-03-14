package com.example.a707446.alloetudiant.publication.demande.presenter;

import com.example.a707446.alloetudiant.general.model.dto.RequestDto;

public interface DemandeContract {
    interface View {
        void createRequest(RequestDto requestDto);

        void showSuccessMsg();

        void showFailedMsg();
    }

    interface Presenter {
        void startCreateRequest(RequestDto requestDto);
    }
}
