package com.example.lab_testing.services;

import com.example.lab_testing.exceptions.ObjectAlreadyExistException;
import com.example.lab_testing.models.Employee;
import com.example.lab_testing.models.Status;
import com.example.lab_testing.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //todo: investigar como ver el mensaje de error en postman
    public Employee createEmployee(Employee employee){
        var employeeFoundByName = employeeRepository.findEmployeeByName(employee.getName());

        var employeeFoundById = employeeRepository.findById(employee.getEmployeeId());

       if (employeeFoundByName.isPresent())
           throw new ObjectAlreadyExistException("El empleado ya existe en la base de datos");


       if (employeeFoundById.isPresent())
           throw new ObjectAlreadyExistException("El empleado que quieres crear tiene un id existente");



        return employeeRepository.save(employee);
    }

    public Employee changeStatusEmployee(int id, String status){
        Employee employeeToChange = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));


        try {
            Status newStatus = Status.valueOf(status.toUpperCase());
            employeeToChange.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value: " + status);
        }

        return employeeRepository.save(employeeToChange);

    }

    public Employee changeDepartmentEmployee(int id, String department) {
        Employee employeeToChange = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        if (department == null || department.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El departamento no puede estar vacío");
        }

        employeeToChange.setDepartment(department);
        return employeeRepository.save(employeeToChange);
    }

    public List<Employee> getAllEmployees(Status status, String department){
        List<Employee> result = employeeRepository.findEmployeesByFilters(
                status != null ? status : null,
                department != null ? department : null
        );

        if (!result.isEmpty()) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron empleados con los filtros proporcionados");
    }

    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado"));
    }




}
