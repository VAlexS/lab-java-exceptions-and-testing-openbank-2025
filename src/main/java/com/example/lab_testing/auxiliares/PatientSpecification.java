package com.example.lab_testing.auxiliares;


import com.example.lab_testing.models.Patient;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class PatientSpecification {

    //esto sirve para que el endpoint me detecte todos los parámetros

    public static Specification<Patient> withFilters(Map<String, String> params) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            params.forEach((key, value) -> {
                switch (key) {
                    case "dateOfBirth":
                        predicates.getExpressions().add(criteriaBuilder.equal(root.get("dateOfBirth"), java.sql.Date.valueOf(value)));
                        break;
                    case "department":
                        // Filtrar por departamento del médico que admitió al paciente
                        predicates.getExpressions().add(criteriaBuilder.like(root.get("employee").get("department"), "%" + value + "%"));
                        break;
                    default:
                        // Ignorar parámetros desconocidos
                        break;
                }
            });

            return predicates;
        };
    }
}
