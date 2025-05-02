package com.example.lab_testing.controllers;


import com.example.lab_testing.models.Patient;
import com.example.lab_testing.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /*Los requestParam con required false quiere decir que son opcionales, con esto me aseguro que el método asociado permita endpoints dinámicos.
    Además, de hacerlos en métodos a parte, tendría que rellenar los GetMapping, ya que JPA, si encuentra más de un getMaping vacío, se produciría
    errores y no se podría levantar el servidor al detectar ambiguedad. Y la idea es que al meter parametros en un endpoint, este no sea
    modificado*/
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients(
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) String department) {

        return patientService.getAllPatients(startDate, endDate, department);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable int id){
        return patientService.getPatientById(id);
    }

    @GetMapping("/by-doctor-status-off")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDoctorWithStatusOff() {
       return patientService.getPatientsByDoctorWithStatusOff();
    }

    //todo: probar este endpoint en postman
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody @Valid Patient patient){
        return patientService.createPatient(patient);
    }

    //todo: probar este endpoint en postman
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable int id, @RequestBody @Valid Patient patient){
        return patientService.updatePatient(id, patient);

    }




}
