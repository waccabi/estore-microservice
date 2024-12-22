package ru.isands.test.estore.rest.service.implemeentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.repo.EmployeeRepository;
import ru.isands.test.estore.rest.service.EmployeeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.getReferenceById(id);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Optional<List<Employee>> employees = Optional.ofNullable(employeeRepository.findAll());
        return employees.get();
    }

    @Override
    public Employee getEmployeeByName(String lastName) {
        return employeeRepository.findByLastName(lastName).orElseThrow(() -> new EntityNotFoundException("Employee with name " + lastName + " not found"));
    }


}
