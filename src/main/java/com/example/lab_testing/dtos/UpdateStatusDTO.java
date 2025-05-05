package com.example.lab_testing.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateStatusDTO {

    @NotBlank(message = "El status no puede estar vacío")
    private String status;

}
