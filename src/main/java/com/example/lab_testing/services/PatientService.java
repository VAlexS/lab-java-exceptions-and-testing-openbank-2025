package com.example.lab_testing.services;

import com.example.lab_testing.exceptions.ObjectAlreadyExistException;
import com.example.lab_testing.models.Patient;
import com.example.lab_testing.repositories.EmployeeRepository;
import com.example.lab_testing.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public Patient createPatient(Patient patient){

        var patientFoundByName = patientRepository.findPatientByName(patient.getName());

        if (patientFoundByName.isPresent())
            throw new ObjectAlreadyExistException("El paciente con este nombre ya existe");

        Patient patientToSave = new Patient(patient.getName(), patient.getDateOfBirth());
        patientToSave.setEmployee(patient.getEmployee());

        return patientRepository.save(patient);

    }

    public Patient updatePatient(int id, Patient patient){
        var patientToUpdate = patientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        patient.setId(patientToUpdate.getId());
        patient.setEmployee(patientToUpdate.getEmployee());

        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients(Date startDate, Date endDate, String department){

        List<Patient> result;

        if (startDate != null || endDate != null) {
            // Filtrar por rango de fecha de nacimiento
            result = patientRepository.findPatientsByDateOfBirthRange(
                    startDate != null ? startDate : null,
                    endDate != null ? endDate : null
            );
        } else if (department != null) {
            // Filtrar por el departamento del médico
            result = patientRepository.findPatientsByDoctorDepartment(department);
        } else {
            // Obtener todos los pacientes
            result = patientRepository.findAll();
        }

        if (!result.isEmpty()) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron pacientes con los filtros proporcionados");
    }

    public Patient getPatientById(int id){
        return patientRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
    }

    public List<Patient> getPatientsByDoctorWithStatusOff(){
        List<Patient> result = patientRepository.findPatientsByDoctorWithStatusOff();
        if (!result.isEmpty()) {
            return result;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron pacientes con un médico en estado OFF");
    }




}
