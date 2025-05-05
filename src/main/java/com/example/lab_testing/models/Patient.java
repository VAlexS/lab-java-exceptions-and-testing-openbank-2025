package com.example.lab_testing.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "patient")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;


    @ManyToOne
    @JoinColumn(name = "admitted_by")
    @JsonBackReference
    private Employee employee;


}

