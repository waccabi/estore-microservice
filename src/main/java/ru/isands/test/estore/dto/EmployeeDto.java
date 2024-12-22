package ru.isands.test.estore.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    Long id;
    String lastName;
    String firstName;
    String patronymic;
    String birthDate;
    String position;
    String workShop;
    String gender;
}
