package com.example.a707446.alloetudiant.notifications.presenter;

import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;

import java.util.List;

public interface NotificationsContract {

    interface View {
        void showMessage(String message);

        void showError(String error);

        void showNotifications(List<NotificationProfileDto> notifications);
    }

    interface Presenter {
        void getNotifications();
    }
}
