package com.example.lab_testing.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id")
    private int employeeID;

    @NotNull
    private String department;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Patient> patients = new ArrayList<>();


}

