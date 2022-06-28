package com.upgroup.webapp.repository;

import com.upgroup.webapp.configuration.CustomProperties;
import com.upgroup.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    CustomProperties props;

    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();

        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {
                }
        );

        log.debug("Get Employees call " + response.getStatusCode());

        return response.getBody();
    }

    public Employee getEmployee(int id) {
        String baseUrlApi = props.getApiUrl();
        String getEmployeeUrl = baseUrlApi + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );

        log.debug("Get employee call" + response.getStatusCode());

        return response.getBody();
    }

    public Employee createEmployee(Employee e) {
        String baseUrlApi = props.getApiUrl();
        String createEmployeeUrl = baseUrlApi + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class
        );

        log.debug("Create Employee call " + response.getStatusCode());

        return response.getBody();
    }

    public Employee updateEmployee(Employee e) {
        String baseUrlApi = props.getApiUrl();
        String updateEmployeeUrl = baseUrlApi + "/employee/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class
        );

        log.debug("Update Employee call " + response.getStatusCode());

        return response.getBody();
    }

    public void deleteEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getDeleteEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                getDeleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("Delete Employee call " + response.getStatusCode());
    }
}
