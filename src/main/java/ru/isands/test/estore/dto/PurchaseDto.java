package ru.isands.test.estore.dto;


import lombok.Data;

@Data
public class PurchaseDto {
    Long id;
    String electroItemName;
    String employeeLastname;
    String purchaseDate;
    String purchaseType;
    String shopName;
}
