package com.example.a707446.alloetudiant.general.model.dto;

import com.example.a707446.alloetudiant.general.model.enumeration.Gender;
import com.example.a707446.alloetudiant.general.model.enumeration.Grade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProfileDto {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private Grade grade;
    private String phoneNumber;

    private Gender gender;

}
