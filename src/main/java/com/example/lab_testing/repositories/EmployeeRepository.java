package com.example.lab_testing.repositories;


import com.example.lab_testing.models.Employee;
import com.example.lab_testing.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE "
            + "(:status IS NULL OR e.status = :status) "
            + "AND (:department IS NULL OR e.department LIKE %:department%)")
    List<Employee> findEmployeesByFilters(@Param("status") Status status, @Param("department") String department);


    Optional<Employee> findEmployeeByName(String name);
}
