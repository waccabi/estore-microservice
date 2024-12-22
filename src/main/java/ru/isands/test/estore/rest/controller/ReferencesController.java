package ru.isands.test.estore.rest.controller;


import com.fasterxml.jackson.annotation.OptBoolean;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dto.*;
import ru.isands.test.estore.mapper.DefaultMapper;
import ru.isands.test.estore.rest.service.ReferencesService;

import javax.persistence.EntityNotFoundException;
import java.lang.invoke.CallSite;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = "References", description = "Сервис для работы со справочниками")
@RequestMapping("/estore/api/references")
@RequiredArgsConstructor
public class ReferencesController {

    private final ReferencesService referencesService;
    private final DefaultMapper defaultMapper;

    @GetMapping("/shops")
    public ResponseEntity<List<ShopDto>> getAllShops(){
        List<Shop> shops = referencesService.getAllShops();
        List<ShopDto> shopDtos = shops.stream()
                .map(shop -> defaultMapper.shopToDto(shop))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(shopDtos);
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable Long id) {
        Shop shop = referencesService.getShopById(id);
        ShopDto shopDto = defaultMapper.shopToDto(shop);
        return ResponseEntity.status(HttpStatus.OK).body(shopDto);
    }

    @PostMapping("/shops")
    public ResponseEntity<ShopDto> createShop(@RequestBody ShopDto shopDto) {
        Shop shop = defaultMapper.dtoToShop(shopDto);
        Shop createdShop = referencesService.createShop(shop);
        ShopDto createdShopDto = defaultMapper.shopToDto(createdShop);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShopDto);
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<ShopDto> updateShop(@PathVariable Long id, @RequestBody ShopDto shopDto) {
        Shop updatedShop = defaultMapper.dtoToShop(shopDto);
        Shop savedShop = referencesService.updateShop(id, updatedShop);
        ShopDto savedShopDto = defaultMapper.shopToDto(savedShop);
        return ResponseEntity.status(HttpStatus.OK).body(savedShopDto);
    }

    @DeleteMapping("/shops/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        referencesService.deleteShop(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/electro-shops")
    public ResponseEntity<List<ElectroShopDto>> getAllElectroShops() {
        List<ElectroShop> electroShops = referencesService.getAllElectroShops();
        List<ElectroShopDto> electroShopDtos = electroShops.stream()
                .map(defaultMapper::electroShopToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(electroShopDtos);
    }

    @GetMapping("/electro-shops/{shopId}/{electroItemId}")
    public ResponseEntity<ElectroShopDto> getElectroShopById(@PathVariable Long shopId, @PathVariable Long electroItemId) {
        ElectroShopPK id = new ElectroShopPK(shopId, electroItemId);
        ElectroShop electroShop = referencesService.getElectroShopById(id);
        return ResponseEntity.ok(defaultMapper.electroShopToDto(electroShop));
    }

    @PostMapping("/electro-shops")
    public ResponseEntity<ElectroShopDto> createElectroShop(@RequestBody ElectroShopDto electroShopDto) {
        ElectroShop electroShop = Optional.ofNullable(referencesService.createElectroShop(electroShopDto))
                        .orElseThrow(() -> new EntityNotFoundException("Failed to create ElectroShop"));
        return ResponseEntity.status(HttpStatus.CREATED).body(defaultMapper.electroShopToDto(electroShop));
    }

    @PutMapping("/electro-shops/{shopId}/{electroItemId}")
    public ResponseEntity<ElectroShopDto> updateElectroShop(@PathVariable Long shopId, @PathVariable Long electroItemId,
                                                            @RequestBody ElectroShopDto electroShopDto) {
        ElectroShopPK id = new ElectroShopPK(shopId, electroItemId);
        ElectroShop electroShop = Optional.ofNullable(referencesService.updateElectroShop(id,electroShopDto))
                .orElseThrow(()->new EntityNotFoundException("Failed to update ElectroShop"));
        return ResponseEntity.status(HttpStatus.OK).body(defaultMapper.electroShopToDto(electroShop));
    }

    @DeleteMapping("/electro-shops/{shopId}/{electroItemId}")
    public ResponseEntity<Void> deleteElectroShop(@PathVariable Long shopId, @PathVariable Long electroItemId) {
        ElectroShopPK id = new ElectroShopPK(shopId, electroItemId);
        referencesService.deleteElectroShop(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/electro-types")
    public ResponseEntity<List<ElectroTypeDto>> getAllElectroTypes() {
        List<ElectroType> electroTypes = referencesService.getAllElectroTypes();
        List<ElectroTypeDto> electroTypeDtos = electroTypes.stream()
                .map(defaultMapper::electroTypeToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(electroTypeDtos);
    }

    @GetMapping("/electro-types/{id}")
    public ResponseEntity<ElectroTypeDto> getElectroTypeById(@PathVariable Long id) {
        ElectroType electroType = referencesService.getElectroTypeById(id);
        return ResponseEntity.ok(defaultMapper.electroTypeToDto(electroType));
    }

    @PostMapping("/electro-types")
    public ResponseEntity<ElectroTypeDto> createElectroType(@RequestBody ElectroTypeDto electroTypeDto) {
        ElectroType electroType = defaultMapper.dtoToElectroType(electroTypeDto);
        ElectroType createdElectroType = referencesService.createElectroType(electroType);
        return ResponseEntity.status(HttpStatus.CREATED).body(defaultMapper.electroTypeToDto(createdElectroType));
    }

    @PutMapping("/electro-types/{id}")
    public ResponseEntity<ElectroTypeDto> updateElectroType(@PathVariable Long id, @RequestBody ElectroTypeDto electroTypeDto) {
        ElectroType updatedElectroType = defaultMapper.dtoToElectroType(electroTypeDto);
        ElectroType savedElectroType = referencesService.updateElectroType(id, updatedElectroType);
        return ResponseEntity.ok(defaultMapper.electroTypeToDto(savedElectroType));
    }

    @DeleteMapping("/electro-types/{id}")
    public ResponseEntity<Void> deleteElectroType(@PathVariable Long id) {
        referencesService.deleteElectroType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/position-types")
    public ResponseEntity<List<PositionTypeDto>> getAllPositionTypes() {
        List<PositionType> positionTypes = referencesService.getAllPositionTypes();
        List<PositionTypeDto> positionTypeDtos = positionTypes.stream()
                .map(defaultMapper::positionTypeToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(positionTypeDtos);
    }

    @GetMapping("/position-types/{id}")
    public ResponseEntity<PositionTypeDto> getPositionTypeById(@PathVariable Long id) {
        PositionType positionType = referencesService.getPositionTypeById(id);
        return ResponseEntity.ok(defaultMapper.positionTypeToDto(positionType));
    }

    @PostMapping("/position-types")
    public ResponseEntity<PositionTypeDto> createPositionType(@RequestBody PositionTypeDto positionTypeDto) {
        PositionType positionType = defaultMapper.dtoToPositionType(positionTypeDto);
        PositionType createdPositionType = referencesService.createPositionType(positionType);
        return ResponseEntity.status(HttpStatus.CREATED).body(defaultMapper.positionTypeToDto(createdPositionType));
    }

    @PutMapping("/position-types/{id}")
    public ResponseEntity<PositionTypeDto> updatePositionType(@PathVariable Long id, @RequestBody PositionTypeDto positionTypeDto) {
        PositionType updatedPositionType = defaultMapper.dtoToPositionType(positionTypeDto);
        PositionType savedPositionType = referencesService.updatePositionType(id, updatedPositionType);
        return ResponseEntity.ok(defaultMapper.positionTypeToDto(savedPositionType));
    }

    @DeleteMapping("/position-types/{id}")
    public ResponseEntity<Void> deletePositionType(@PathVariable Long id) {
        referencesService.deletePositionType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/purchase-types")
    public ResponseEntity<List<PurchaseTypeDto>> getAllPurchaseTypes() {
        List<PurchaseType> purchaseTypes = referencesService.getAllPurchaseTypes();
        List<PurchaseTypeDto> purchaseTypeDtos = purchaseTypes.stream()
                .map(defaultMapper::purchaseTypeToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(purchaseTypeDtos);
    }

    @GetMapping("/purchase-types/{id}")
    public ResponseEntity<PurchaseTypeDto> getPurchaseTypeById(@PathVariable Long id) {
        PurchaseType purchaseType = referencesService.getPurchaseTypeById(id);
        return ResponseEntity.ok(defaultMapper.purchaseTypeToDto(purchaseType));
    }

    @PostMapping("/purchase-types")
    public ResponseEntity<PurchaseTypeDto> createPurchaseType(@RequestBody PurchaseTypeDto purchaseTypeDto) {
        PurchaseType purchaseType = defaultMapper.dtoToPurchaseType(purchaseTypeDto);
        PurchaseType createdPurchaseType = referencesService.createPurchaseType(purchaseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(defaultMapper.purchaseTypeToDto(createdPurchaseType));
    }

    @PutMapping("/purchase-types/{id}")
    public ResponseEntity<PurchaseTypeDto> updatePurchaseType(@PathVariable Long id, @RequestBody PurchaseTypeDto purchaseTypeDto) {
        PurchaseType updatedPurchaseType = defaultMapper.dtoToPurchaseType(purchaseTypeDto);
        PurchaseType savedPurchaseType = referencesService.updatePurchaseType(id, updatedPurchaseType);
        return ResponseEntity.ok(defaultMapper.purchaseTypeToDto(savedPurchaseType));
    }

    @DeleteMapping("/purchase-types/{id}")
    public ResponseEntity<Void> deletePurchaseType(@PathVariable Long id) {
        referencesService.deletePurchaseType(id);
        return ResponseEntity.noContent().build();
    }
}
