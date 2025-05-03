package com.example.lab_testing.controllers;


import com.example.lab_testing.dtos.*;
import com.example.lab_testing.models.Employee;
import com.example.lab_testing.models.Status;
import com.example.lab_testing.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /*Los requestParam con required false quiere decir que son opcionales, con esto me aseguro que el método asociado permita endpoints dinámicos.
    Además, de hacerlos en métodos a parte, tendría que rellenar los GetMapping, ya que JPA, si encuentra más de un getMaping vacío, se produciría
    errores y no se podría levantar el servidor al detectar ambiguedad. Y la idea es que al meter parametros en un endpoint, este no sea
    modificado*/
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployess(@RequestParam(required = false) Status status,
                                          @RequestParam(required = false) String department) {
        return employeeService.getAllEmployees(status, department);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Valid Employee employee){
        return employeeService.createEmployee(employee);
    }



    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateDoctorStatus(@PathVariable int id, @Valid @RequestBody UpdateStatusDTO dto) {

        return employeeService.changeStatusEmployee(id, dto.getStatus());
    }



    @PatchMapping("/{id}/department")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateDoctorDepartment(@PathVariable int id, @Valid @RequestBody UpdateDepartmentDTO dto) {
        return employeeService.changeDepartmentEmployee(id, dto.getDepartment());
    }





}
