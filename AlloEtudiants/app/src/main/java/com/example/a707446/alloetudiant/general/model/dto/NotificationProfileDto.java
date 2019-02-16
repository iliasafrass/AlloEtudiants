package com.example.a707446.alloetudiant.general.model.dto;

import com.example.a707446.alloetudiant.general.model.pojo.Notification;
import com.example.a707446.alloetudiant.general.model.pojo.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationProfileDto {

    private Notification notification;

    private Profile profile;
}
