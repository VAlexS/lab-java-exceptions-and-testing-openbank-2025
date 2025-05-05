package com.example.lab_testing.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

    @Id
    @NonNull
    @Column(name = "employee_id")
    private int employeeId;

    @NonNull
    private String department;

    @NonNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Patient> patients = new ArrayList<>();


}

