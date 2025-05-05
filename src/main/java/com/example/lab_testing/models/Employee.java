package com.example.lab_testing.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

    @Id
    @Column(name = "employee_id")
    private int employeeID;

    private String department;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Patient> patients = new ArrayList<>();

    public Employee() {
    }

    public Employee(int employeeID, String department, String name, Status status) {
        this.employeeID = employeeID;
        this.department = department;
        this.name = name;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", employeeID=" + employeeID +
                '}';
    }
}

