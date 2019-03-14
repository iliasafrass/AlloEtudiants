package com.example.a707446.alloetudiant.annonces.presenter;

import com.example.a707446.alloetudiant.general.model.dto.AnnouncementDto;

import java.util.List;

public interface AnnonceContract {

    interface View {
        void showMessage(String message);

        void showError();

        void showAnnouncements(List<AnnouncementDto> announcements);

        void showAnnouncementsAfterDelete(List<AnnouncementDto> announcements, int position);
    }

    interface Presenter {
        void getAnnouncements();

        void deleteAnnouncement(AnnouncementDto announcement, int position);
    }
}
