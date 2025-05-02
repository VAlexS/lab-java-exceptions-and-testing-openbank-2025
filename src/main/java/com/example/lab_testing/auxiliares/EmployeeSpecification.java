package com.example.lab_testing.auxiliares;


import com.example.lab_testing.models.Employee;
import com.example.lab_testing.models.Status;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class EmployeeSpecification {

    //esto sirve para que el endpoint me detecte todos los parámetros.

    public static Specification<Employee> withFilters(Map<String, String> params) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            params.forEach((key, value) -> {
                switch (key) {
                    case "status":
                        predicates.getExpressions().add(criteriaBuilder.equal(root.get("status"), Status.valueOf(value)));
                        break;
                    case "department":
                        predicates.getExpressions().add(criteriaBuilder.like(root.get("department"), "%" + value + "%"));
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
