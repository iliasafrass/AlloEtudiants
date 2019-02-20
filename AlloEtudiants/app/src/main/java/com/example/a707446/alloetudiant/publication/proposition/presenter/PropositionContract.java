package com.example.a707446.alloetudiant.publication.proposition.presenter;

import com.example.a707446.alloetudiant.general.model.dto.OfferDto;

public interface PropositionContract {
    interface View {
        void createOffer(OfferDto offerDto);
        void showSuccessMsg();
        void showFailedMsg();

    }

    interface Presenter {
        void startCreateOffer(OfferDto offerDto);
    }
}
