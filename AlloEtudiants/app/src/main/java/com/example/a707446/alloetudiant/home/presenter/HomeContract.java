package com.example.a707446.alloetudiant.home.presenter;

import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;

import java.util.List;

public interface HomeContract {

    interface View {
        void showMessage(String message);
        void showError(String error);
        void showNotifications(List<NotificationProfileDto> notifications);
        void showNotificationsAfterAnswer(List<NotificationProfileDto> notifications, int position);
    }

    interface Presenter {
        void getNotifications();
        void sendNotificationAnswer(NotificationProfileDto dto, int position);
    }

}
