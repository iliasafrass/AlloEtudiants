package com.example.a707446.alloetudiant.model.pojo;

import com.example.a707446.alloetudiant.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.model.enumeration.Subject;
import com.example.a707446.alloetudiant.model.enumeration.WeekDay;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    private String id;

    private Date createdDate;

    private Date lastModifiedDate;

    private String profileId;

    private AnnounceType announceType;

    private String title;

    private String address;

    private String description;

    private Subject subject;

    private float price;

    private String icon;

    private List<WeekDay> days;
}
