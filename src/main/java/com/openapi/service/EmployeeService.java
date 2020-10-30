package com.openapi.service;

import com.openapi.model.Employee;
import com.openapi.model.EmployeeRequest;

import java.util.List;

/**
 * @author sourabh
 * @implNote service class for employee CRUD operation
 */
public interface EmployeeService {

    /**
     * Method will add a new employee and generate a unique id for employee
     * @param request
     * @return
     */
    Employee addNewEmployee(EmployeeRequest request);

    /**
     * Method returns list of employees in Employee Store
     * @return
     */
    List<Employee> getEmployees();

    /**
     * Given an employee id, return employee details
     * @param employeeId
     * @return
     */
    Employee getEmployee(String employeeId);

    /**
     * Method to delete employee
     * @param employeeId
     * @return
     */
    Employee removeEmployee(String employeeId);

    /**
     * Given an employee identified by id, update employee name
     * @param employee
     * @return
     */
    Employee updateEmployee(Employee employee);
}
