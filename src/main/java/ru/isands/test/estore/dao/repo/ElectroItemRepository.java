package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.ElectroItem;

import java.util.Optional;

public interface ElectroItemRepository extends JpaRepository<ElectroItem, Long> {
    public Optional<ElectroItem> findByName(String name);
}
