package com.example.a707446.alloetudiant.general.model.dto;

import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private String profileId;

    private AnnounceType announceType;

    private String title;

    private String address;

    private String description;

    private List<Date> dates;

}
