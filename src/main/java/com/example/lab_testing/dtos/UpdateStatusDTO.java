package com.example.lab_testing.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data //directamente añade metodos get, set, toString, equals, hashCode
public class UpdateStatusDTO {

    @NotBlank(message = "El status no puede estar vacío")
    private String status;

}
