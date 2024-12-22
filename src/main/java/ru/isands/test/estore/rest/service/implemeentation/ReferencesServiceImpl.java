package ru.isands.test.estore.rest.service.implemeentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dao.repo.*;
import ru.isands.test.estore.dto.ElectroShopDto;
import ru.isands.test.estore.rest.service.ElectroItemService;
import ru.isands.test.estore.rest.service.ReferencesService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReferencesServiceImpl implements ReferencesService {
    private final ShopRepository shopRepository;
    private final ElectroShopRepository electroShopRepository;
    private final ElectroTypeRepository electroTypeRepository;
    private final PositionTypeRepository positionTypeRepository;
    private final PurchaseTypeRepository purchaseTypeRepository;

    private final ElectroItemService electroItemService;

    /**
     * Магазин
     */
    @Override
    public List<Shop> getAllShops() {
        Optional<List<Shop>> shops = Optional.ofNullable(shopRepository.findAll());
        return shops.get();
    }

    @Override
    public Shop getShopById(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shop with id " + id + " not found"));
    }

    @Override
    public Shop getShopByName(String name) {
            return shopRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Shop with name " + name + " not found"));
    }

    @Override
    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop updateShop(Long id, Shop updatedShop) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shop with id " + id + " not found"));
        shop.setName(updatedShop.getName());
        shop.setAddress(updatedShop.getAddress());
        return shopRepository.save(shop);
    }

    @Override
    public void deleteShop(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shop with id " + id + " not found"));
        shopRepository.delete(shop);
    }

    /**
     * Электро магазин
     */

    @Override
    public List<ElectroShop> getAllElectroShops() {
        Optional<List<ElectroShop>> electroShops = Optional.ofNullable(electroShopRepository.findAll());
        return electroShops.get();
    }

    @Override
    public ElectroShop getElectroShopById(ElectroShopPK id) {
        return electroShopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ElectroShop with id " + id + " not found"));
    }

    @Override
    public ElectroShop createElectroShop(ElectroShopDto electroShopDto) {
        Shop s = getShopByName(electroShopDto.getShopName());
        ElectroItem ei =  electroItemService.getElectroItemByName(electroShopDto.getElectroItemName());

        ElectroShop electroShop = new ElectroShop();
        electroShop.setShopId(s.getId());
        electroShop.setElectroItemId(ei.getId());
        electroShop.setCount(electroShopDto.getCount());

        return electroShopRepository.save(electroShop);
    }

    @Override
    public ElectroShop updateElectroShop(ElectroShopPK id, ElectroShopDto updatedElectroShopDto) {
        ElectroShop electroShop = electroShopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ElectroShop with id " + id + " not found"));

        electroShop.setShopId(getShopByName(updatedElectroShopDto.getShopName()).getId());
        electroShop.setElectroItemId(electroItemService.getElectroItemByName(updatedElectroShopDto.getElectroItemName()).getId());
        electroShop.setCount(updatedElectroShopDto.getCount());

        return electroShopRepository.save(electroShop);
    }

    @Override
    public void deleteElectroShop(ElectroShopPK id) {
        ElectroShop electroShop = electroShopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ElectroShop with id " + id + " not found"));
        electroShopRepository.delete(electroShop);
    }


    /**
     * Типы электоронники
     */

    @Override
    public List<ElectroType> getAllElectroTypes() {
        Optional<List<ElectroType>> electroTypes = Optional.ofNullable(electroTypeRepository.findAll());
        return electroTypes.get();
    }

    @Override
    public ElectroType getElectroTypeById(Long id) {
        return electroTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ElectroType with id " + id + " not found"));
    }

    @Override
    public ElectroType createElectroType(ElectroType electroType) {
        return electroTypeRepository.save(electroType);
    }

    @Override
    public ElectroType updateElectroType(Long id, ElectroType updatedElectroType) {
        ElectroType electroType = electroTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ElectroType with id " + id + " not found"));
        electroType.setName(updatedElectroType.getName());
        return electroTypeRepository.save(electroType);
    }

    @Override
    public void deleteElectroType(Long id) {
        ElectroType electroType = electroTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ElectroType with id " + id + " not found"));
        electroTypeRepository.delete(electroType);
    }

    /**
     * Должности
     */

    @Override
    public List<PositionType> getAllPositionTypes() {
        Optional<List<PositionType>> positionTypes = Optional.ofNullable(positionTypeRepository.findAll());
        return positionTypes.get();
    }

    @Override
    public PositionType getPositionTypeById(Long id) {
        return positionTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PositionType with id " + id + " not found"));
    }

    @Override
    public PositionType createPositionType(PositionType positionType) {
        return positionTypeRepository.save(positionType);
    }

    @Override
    public PositionType updatePositionType(Long id, PositionType updatedPositionType) {
        PositionType positionType = positionTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PositionType with id " + id + " not found"));
        positionType.setName(updatedPositionType.getName());
        return positionTypeRepository.save(positionType);
    }

    @Override
    public void deletePositionType(Long id) {
        PositionType positionType = positionTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PositionType with id " + id + " not found"));
        positionTypeRepository.delete(positionType);
    }

    @Override
    public List<PurchaseType> getAllPurchaseTypes() {
        return List.of();
    }

    /**
     * Типы покупок
     */

    @Override
    public PurchaseType getPurchaseTypeById(Long id) {
        return purchaseTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PurchaseType with id " + id + " not found"));
    }

    @Override
    public PurchaseType createPurchaseType(PurchaseType purchaseType) {
        return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public PurchaseType updatePurchaseType(Long id, PurchaseType updatedPurchaseType) {
        PurchaseType purchaseType = purchaseTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PurchaseType with id " + id + " not found"));
        purchaseType.setName(updatedPurchaseType.getName());
        return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public void deletePurchaseType(Long id) {
        PurchaseType purchaseType = purchaseTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PurchaseType with id " + id + " not found"));
        purchaseTypeRepository.delete(purchaseType);
    }

    @Override
    public PurchaseType getPurchaseTypeByName(String name) {
        return purchaseTypeRepository.getPurchaseTypeByName(name).orElseThrow(() -> new EntityNotFoundException("Purchase type with " + name + " not found"));
    }
}
