package ru.isands.test.estore.rest.service;

import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dto.PurchaseDto;

import java.text.ParseException;
import java.util.List;

public interface PurchaseService {
    public List<Purchase> getAllPurchases();
    public Purchase getPurchaseById(Long id);
    public Purchase createPurchase(PurchaseDto purchaseDto) throws ParseException;
    public Purchase updatePurchase(PurchaseDto purchaseDto) throws ParseException;
    public void deletePurchase(Long id);

}
