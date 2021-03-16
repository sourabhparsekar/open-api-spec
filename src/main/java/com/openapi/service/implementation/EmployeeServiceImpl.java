package com.openapi.service.implementation;

import com.openapi.model.Employee;
import com.openapi.model.EmployeeRequest;
import com.openapi.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author sourabh
 * @implNote Implementation class for Employee Service
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    //we can integrate this with db, but as we only want to see swagger demo, we put data in map
    private final Map<String, Employee> employeeStore = new HashMap<>();

    @Override
    public Employee addNewEmployee(EmployeeRequest request) {

        logger.debug("Add new employee to the employee store");
        String id = UUID.randomUUID().toString();

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setId(id);

        employeeStore.put(id, employee);
        logger.debug("Employee Count {}", employeeStore.size());

        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        logger.debug("Get all employees from the employee store");
        List<Employee> employeeList = new ArrayList<>();
        employeeStore.forEach((id, employee) -> employeeList.add(employee));
        logger.debug("Employee Count {}", employeeStore.size());
        return employeeList;
    }

    @Override
    public Employee getEmployee(String employeeId) {
        logger.debug("Get employee from the employee store");
        Employee employee = null;
        if (employeeStore.containsKey(employeeId)) {
            employee = employeeStore.get(employeeId);
            logger.debug("Employee exists");
        } else {
            employee = new Employee();
            logger.debug("Employee does not exists");
        }
        return employee;
    }

    @Override
    public Employee removeEmployee(String employeeId) {
        logger.debug("Remove employee from the employee store");
        Employee employee = null;
        if (employeeStore.containsKey(employeeId)) {
            employee = employeeStore.remove(employeeId);
            logger.debug("Employee removed");
        } else {
            employee = new Employee();
            logger.debug("Employee does not exists");
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        logger.debug("Update employee from the employee store");
        Employee updatedEmployee = null;
        if (employeeStore.containsKey(employee.getId())) {
            updatedEmployee = employee;
            removeEmployee(employee.getId());
            employeeStore.put(employee.getId(), employee);
            logger.debug("Employee updated");
        } else {
            EmployeeRequest request = new EmployeeRequest();
            request.setFirstName(employee.getFirstName());
            request.setLastName(employee.getLastName());
            updatedEmployee = addNewEmployee(request);
            logger.debug("New Employee created");
        }
        return updatedEmployee;
    }
}
