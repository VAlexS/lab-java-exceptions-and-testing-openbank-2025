package com.example.lab_testing.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class CreatePatientDTO {

    private String name;

    private Date dateOfBirth;

    private int admittedBy;




}
