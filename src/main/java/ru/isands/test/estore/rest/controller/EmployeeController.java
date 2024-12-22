package ru.isands.test.estore.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dto.EmployeeDto;
import ru.isands.test.estore.dto.PositionTypeDto;
import ru.isands.test.estore.mapper.DefaultMapper;
import ru.isands.test.estore.rest.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Employee", description = "Сервис для выполнения операций над сотрудниками магазина")
@RequestMapping("/estore/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DefaultMapper defaultMapper;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee ->defaultMapper.employeeToDto(employee))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtos);
    }


}
