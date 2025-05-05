package com.example.lab_testing;

import com.example.lab_testing.models.Employee;
import com.example.lab_testing.models.Status;
import com.example.lab_testing.repositories.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LabTestingApplicationTests {

	//crea un contexto de la aplicación para poder inicializar MockMVC, esto genera un entorno de pruebas
	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mockMvc; //esta clase nos servirá para simular peticiones HTTP

	//es una herramienta para convertir objetos a json
	final ObjectMapper objectMapper = new ObjectMapper();

	final int EMPLOYEE_ID = 58453;

	final String DEPARTMENT = "fisioterapia";

	final String NAME = "Marina Sanz";

	@Autowired
	EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}


	@AfterEach
	void deleteEmployee(){
		var employeeToDelete = employeeRepository.findById(EMPLOYEE_ID);

		if (employeeToDelete.isPresent())
			employeeRepository.deleteById(employeeToDelete.get().getEmployeeID());

	}

	@Test
	void createEmployee() {
		Employee employee = new Employee(EMPLOYEE_ID, DEPARTMENT, NAME, Status.ON);

        try {
            String employeeJSON = objectMapper.writeValueAsString(employee); //hemos convertido el empleado a json

			System.out.println("==========================");
			System.out.println(employeeJSON);
			System.out.println("==========================");


			MvcResult result = mockMvc.perform(post("/employee")
					.contentType(MediaType.APPLICATION_JSON)
					.content(employeeJSON) //esto es el body
			).andExpect(status().isCreated()).andReturn();
			//estos metodos van a verificar que la respuesta es un 201(created) y que devuelven un resultado

			//mostrar la respuesta en la terminal
			System.out.println("============================");
			System.out.println("La respuesta recibida");
			System.out.println(result.getResponse().getContentAsString()); //convierte el contenido de la respuesta a una string
			System.out.println("============================");

        } catch (JsonProcessingException e) {
			System.out.println("Error al convertir a json");
            throw new RuntimeException(e);
        } catch (Exception e) {
			System.out.println("Error al realizar el perfom");
            throw new RuntimeException(e);
        }
    }

}
