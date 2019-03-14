package com.example.a707446.alloetudiant.general.model.dto;

import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.enumeration.Grade;
import com.example.a707446.alloetudiant.general.model.enumeration.Subject;
import com.example.a707446.alloetudiant.general.model.enumeration.WeekDay;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private String profileId;

    private AnnounceType announceType;

    private String title;

    private String address;

    private String description;

    private Subject subject;

    private Grade grade;

    private int hours;

    private float pricePerHour;

    private float total;

    private List<WeekDay> days;

}
