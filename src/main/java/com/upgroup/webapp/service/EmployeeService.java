package com.upgroup.webapp.service;

import com.upgroup.webapp.model.Employee;
import com.upgroup.webapp.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Data
@Service
public class EmployeeService {

    @Autowired
    EmployeeProxy employeeProxy;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;

        employee.setLastName(employee.getLastName().toUpperCase());

        if (employee.getId() == null) {
            savedEmployee = employeeProxy.createEmployee(employee);
        }
        else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }

        return savedEmployee;
    }

    public void deleteEmployee(final int id) {
        employeeProxy.deleteEmployee(id);
    }
}
