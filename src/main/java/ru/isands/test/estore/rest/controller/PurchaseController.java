package ru.isands.test.estore.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dto.PurchaseDto;
import ru.isands.test.estore.mapper.DefaultMapper;
import ru.isands.test.estore.rest.service.PurchaseService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Purchase", description = "Сервис для выполнения операций с покупками")
@RequestMapping("/estore/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final DefaultMapper defaultMapper;

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseDto>> getPurchases(){
        List<Purchase> purchases = purchaseService.getAllPurchases();
        List<PurchaseDto> purchaseDtos = purchases.stream()
                .map(purchase -> defaultMapper.purchaseToDto(purchase))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseDtos);
    }

        public ResponseEntity<PurchaseDto> getPurchaseById(){
            //Purchase purchase = purchaseService;
            return null;
        }



}
