package com.example.a707446.alloetudiant.general.model.pojo;

import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    private String id;

    private Date createdDate;

    private Date lastModifiedDate;

    private String profileId;

    private AnnounceType announceType;

    private String title;

    private String address;

    private String description;

    private String imgUrl;

    private String dates;
}
