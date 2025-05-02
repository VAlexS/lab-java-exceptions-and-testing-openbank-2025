package com.example.lab_testing.dtos;

import jakarta.validation.constraints.NotBlank;

public class UpdateDepartmentDTO {

    @NotBlank(message = "El departamento no puede estar vacio")
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
