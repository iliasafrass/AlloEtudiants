package com.example.a707446.alloetudiant.model.dto;

import com.example.a707446.alloetudiant.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.model.enumeration.Subject;
import com.example.a707446.alloetudiant.model.enumeration.WeekDay;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {

    private String profileId;

    private AnnounceType announceType;

    private String title;

    private String address;

    private String description;

    private Subject subject;

    private float price;

    private List<WeekDay> days;

}
