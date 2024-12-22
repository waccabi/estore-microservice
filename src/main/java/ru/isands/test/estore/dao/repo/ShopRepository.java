package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Shop;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    public Optional<Shop> findByName(String name);
}
