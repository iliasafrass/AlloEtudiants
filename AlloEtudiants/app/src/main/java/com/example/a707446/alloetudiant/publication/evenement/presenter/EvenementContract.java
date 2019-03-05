package com.example.a707446.alloetudiant.publication.evenement.presenter;

import com.example.a707446.alloetudiant.general.model.dto.EventDto;

public interface EvenementContract {
    interface View {
        void createEvent(EventDto eventDto);
        void showSuccessMsg();
        void showFailedMsg();
    }

    interface Presenter {
        void startCreateEvent(EventDto eventDto);
    }
}
