package ru.isands.test.estore.rest.service;

import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dto.ElectroShopDto;

import java.security.PublicKey;
import java.util.List;

public interface ReferencesService {

    public List<Shop> getAllShops();
    public Shop getShopById(Long id);
    public Shop getShopByName(String name);
    public Shop createShop(Shop shop);
    public Shop updateShop(Long id, Shop updateShop);
    public void deleteShop(Long id);

    public List<ElectroShop> getAllElectroShops();
    public ElectroShop getElectroShopById(ElectroShopPK id);
    public ElectroShop createElectroShop(ElectroShopDto electroShopDto);
    public ElectroShop updateElectroShop(ElectroShopPK id, ElectroShopDto updatedElectroShopDto);
    public void deleteElectroShop(ElectroShopPK id);

    public List<ElectroType> getAllElectroTypes();
    public ElectroType getElectroTypeById(Long id);
    public ElectroType createElectroType(ElectroType electroType);
    public ElectroType updateElectroType(Long id, ElectroType updatedElectroType);
    public void deleteElectroType(Long id);

    public List<PositionType> getAllPositionTypes();
    public PositionType getPositionTypeById(Long id);
    public PositionType createPositionType(PositionType positionType);
    public PositionType updatePositionType(Long id, PositionType updatedPositionType);
    public void deletePositionType(Long id);

    public List<PurchaseType> getAllPurchaseTypes();
    public PurchaseType getPurchaseTypeById(Long id);
    public PurchaseType createPurchaseType(PurchaseType purchaseType);
    public PurchaseType updatePurchaseType(Long id, PurchaseType updatedPurchaseType);
    public void deletePurchaseType(Long id);
    public PurchaseType getPurchaseTypeByName(String name);
}
