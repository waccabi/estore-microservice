package ru.isands.test.estore.rest.service.implemeentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dao.repo.PurchaseRepository;
import ru.isands.test.estore.dto.PurchaseDto;
import ru.isands.test.estore.rest.service.ElectroItemService;
import ru.isands.test.estore.rest.service.EmployeeService;
import ru.isands.test.estore.rest.service.PurchaseService;
import ru.isands.test.estore.rest.service.ReferencesService;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository repository;
    private final ElectroItemService electroItemService;
    private final ReferencesService referencesService;
    private final EmployeeService employeeService;


    @Override
    public List<Purchase> getAllPurchases() {
        Optional<List<Purchase>> purchases = Optional.ofNullable(repository.findAll());
        return purchases.get();
    }

    @Override
    public Purchase getPurchaseById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Purchase with id " + id + " not found"));
    }

    @Override
    public Purchase createPurchase(PurchaseDto purchaseDto) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        ElectroItem electroItem = electroItemService.getElectroItemByName(purchaseDto.getElectroItemName());
        Shop shop = referencesService.getShopByName(purchaseDto.getShopName());
        Employee employee = employeeService.getEmployeeByName(purchaseDto.getEmployeeLastname());
        PurchaseType purchaseType = referencesService.getPurchaseTypeByName(purchaseDto.getPurchaseType());
        Date date = formatter.parse(purchaseDto.getPurchaseDate());

        Purchase purchase = new Purchase();
        purchase.setId(purchaseDto.getId());
        purchase.setElectroId(electroItem);
        purchase.setEmployeeId(employee);
        purchase.setShopId(shop);
        purchase.setTypeId(purchaseType);
        purchase.setPurchaseDate(date);

        return purchase;
    }

    @Override
    public Purchase updatePurchase(PurchaseDto purchaseDto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Optional <Purchase> purchase = Optional.ofNullable(repository.findPurchaseById(purchaseDto.getId()));
        ElectroItem electroItem = electroItemService.getElectroItemByName(purchaseDto.getElectroItemName());
        Shop shop = referencesService.getShopByName(purchaseDto.getShopName());
        Employee employee = employeeService.getEmployeeByName(purchaseDto.getEmployeeLastname());
        PurchaseType purchaseType = referencesService.getPurchaseTypeByName(purchaseDto.getPurchaseType());
        Date date = formatter.parse(purchaseDto.getPurchaseDate());

        purchase.get().setElectroId(electroItem);
        purchase.get().setEmployeeId(employee);
        purchase.get().setShopId(shop);
        purchase.get().setTypeId(purchaseType);
        purchase.get().setPurchaseDate(date);
        return purchase.get();
    }

    @Override
    public void deletePurchase(Long id) {

    }


}
