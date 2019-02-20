package com.example.a707446.alloetudiant.general.model.dto;

import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.enumeration.NotificationAnswer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private String askerProfileId;

    private String askedProfileId;

    private String announceId;

    private String announceTitle;

    private AnnounceType announceType;

    private String message;

    private boolean done;

    private NotificationAnswer answer;
}
