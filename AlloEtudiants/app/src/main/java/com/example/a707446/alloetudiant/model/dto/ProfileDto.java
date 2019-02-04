package com.example.a707446.alloetudiant.model.dto;

import com.example.a707446.alloetudiant.model.enumeration.Gender;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phoneNumber;

    private String bio;

    private Gender gender;

}
