package com.example.a707446.alloetudiant.general.model.pojo;

import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.enumeration.NotificationAnswer;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private String id;

    private Date createdDate;

    private Date lastModifiedDate;

    private String askerProfileId;

    private String askedProfileId;

    private String announceId;

    private String announceTitle;

    private AnnounceType announceType;

    private String message;

    private boolean done;

    private NotificationAnswer answer;

}
