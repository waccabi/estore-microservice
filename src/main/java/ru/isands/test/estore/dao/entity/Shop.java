package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store")
public class Shop implements Serializable {

    /**
     * Идентификатор магазина
     */
    @Id
    @Column(name = "id_", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "shop_counter")
    @TableGenerator(
            name = "shop_counter",
            pkColumnName = "name",
            pkColumnValue = "ru.isands.test.estore.dao.entity.Shop",
            table = "counter",
            valueColumnName = "currentid",
            allocationSize = 1
    )
    Long id;

    /**
     * Название магазина
     */
    @Column(name="name", nullable = false, length = 250)
    String name;

    /**
     * Адресс магазина
     */
    @Column(name ="address", nullable = false)
    String address;

    @OneToMany(mappedBy = "shop")
    List<ElectroShop> electroShops;

    @OneToMany(mappedBy = "shopId")
    List<Employee> employees;

    @OneToMany(mappedBy = "shopId")
    List<Purchase> purchases;
}
