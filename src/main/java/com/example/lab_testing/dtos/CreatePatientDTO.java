package com.example.lab_testing.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class CreatePatientDTO {

    private String name;

    private Date dateOfBirth;

    private int admittedBy;




}
