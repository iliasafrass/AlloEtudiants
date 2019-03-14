package com.example.a707446.alloetudiant.general.model.pojo;

import com.example.a707446.alloetudiant.general.model.enumeration.WeekDay;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slot {

    private WeekDay day;

    private Date startTime;

    private Date endTime;
}
