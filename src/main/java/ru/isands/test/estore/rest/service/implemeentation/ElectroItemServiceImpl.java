package ru.isands.test.estore.rest.service.implemeentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.repo.ElectroItemRepository;
import ru.isands.test.estore.rest.service.ElectroItemService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectroItemServiceImpl implements ElectroItemService {
    private final ElectroItemRepository repository;

    @Override
    public List<ElectroItem> getAllElectroItems() {
        Optional<List<ElectroItem>> electroItems = Optional.ofNullable(repository.findAll());
        return electroItems.get();
    }

    @Override
    public ElectroItem getElectroItemByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Electro item with name " + name + " not found"));
    }

}
