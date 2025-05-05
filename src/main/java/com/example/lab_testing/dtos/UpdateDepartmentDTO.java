package com.example.lab_testing.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UpdateDepartmentDTO {

    @NotBlank(message = "El departamento no puede estar vacio")
    private String department;

}
