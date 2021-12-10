package com.ouertani.notes.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Patient {

    private enum Gender {Not_Known, Male, Female, Not_Applicable}

    private Long patientId;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String gender;

    private String mainAdress;

    private String phone;

}