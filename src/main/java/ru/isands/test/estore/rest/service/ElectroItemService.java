package ru.isands.test.estore.rest.service;

import ru.isands.test.estore.dao.entity.ElectroItem;

import java.util.List;

public interface ElectroItemService {
    public List<ElectroItem> getAllElectroItems();
    public ElectroItem getElectroItemByName(String name);
}
