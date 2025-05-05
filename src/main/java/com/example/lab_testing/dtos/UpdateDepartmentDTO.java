package com.example.lab_testing.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateDepartmentDTO {

    @NotBlank(message = "El departamento no puede estar vacio")
    private String department;

}
