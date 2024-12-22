package ru.isands.test.estore.dto;

import lombok.Data;

@Data
public class ElectroItemDto {
    Long id;
    String name;
    String electroTypeName;
    Long price;
    Integer count;
    String archived;
    String description;
}
