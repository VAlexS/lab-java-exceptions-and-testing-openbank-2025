package com.example.lab_testing.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class CreatePatientDTO {

    private String name;

    private Date dateOfBirth;



    private int admittedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(int admittedBy) {
        this.admittedBy = admittedBy;
    }



}
