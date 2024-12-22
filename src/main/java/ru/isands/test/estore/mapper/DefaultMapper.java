package ru.isands.test.estore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dto.*;

@Mapper(componentModel = "spring")
public interface DefaultMapper {

    @Mapping(target = "position", source = "positionId.name")
    @Mapping(target = "workShop", source = "shopId.name")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "genderToString")
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    EmployeeDto employeeToDto(Employee employee);

    @Named("genderToString")
    default String genderToString(boolean gender) {
        return gender ? "Male" : "Female";
    }


    @Mapping(target = "electroItemName", source = "electroId.name")
    @Mapping(target = "employeeLastname", source = "employeeId.lastName")
    @Mapping(target = "purchaseDate", source = "purchaseDate", dateFormat = "dd-MM-yyyy" )
    @Mapping(target = "purchaseType", source = "typeId.name")
    @Mapping(target = "shopName", source = "shopId.name")
    PurchaseDto purchaseToDto(Purchase purchase);

    @Mapping(target = "electroTypeName", source = "etypeId.name")
    @Mapping(target = "archived", source = "archived", qualifiedByName = "archivedToString")
    ElectroItemDto electroItemToDto(ElectroItem electroItem);

    @Named("archivedToString")
    default String archivedToString(boolean archived) {
        return archived ? "Out of stock" : "In stock";
    }

    @Mapping(target = "shopName", source = "shop.name")
    @Mapping(target = "electroItemName", source = "electroItem.name")
    ElectroShopDto electroShopToDto(ElectroShop electroShop);

    ShopDto shopToDto(Shop shop);
    Shop dtoToShop(ShopDto shopDto);

    PositionTypeDto positionTypeToDto(PositionType positionType);
    PositionType dtoToPositionType(PositionTypeDto PositionTypeDto);

    ElectroTypeDto electroTypeToDto(ElectroType electroType);
    ElectroType dtoToElectroType(ElectroTypeDto electroTypeDto);

    PurchaseTypeDto purchaseTypeToDto(PurchaseType purchaseType);
    PurchaseType dtoToPurchaseType(PurchaseTypeDto purchaseTypeDto);


}
