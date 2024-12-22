package ru.isands.test.estore.rest.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dto.ElectroItemDto;
import ru.isands.test.estore.dto.EmployeeDto;
import ru.isands.test.estore.mapper.DefaultMapper;
import ru.isands.test.estore.rest.service.ElectroItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "ElectroItem", description = "Сервис ждя выполнения операций над товарами")
@RequestMapping("/estore/api/electroitem")
@RequiredArgsConstructor
public class ElectroItemController {

    private final ElectroItemService electroItemService;
    private final DefaultMapper defaultMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ElectroItemDto>> getAllElectroItems() {
        List<ElectroItem> electroItems= electroItemService.getAllElectroItems();
        List<ElectroItemDto> electroItemDtos = electroItems.stream()
                .map(electroItem ->defaultMapper.electroItemToDto(electroItem))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(electroItemDtos);
    }

}
