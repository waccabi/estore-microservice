package ru.isands.test.estore.rest.service;

import ru.isands.test.estore.dao.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee getEmployeeById(Long id);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeByName(String lastName);

}
