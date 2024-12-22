package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "eitem")
public class ElectroItem implements Serializable {

    /**
     * Идентификатор товара
     */
    @Id
    @Column(name = "id_", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "eitem_counter")
    @TableGenerator(
            name = "eitem_counter",
            pkColumnName = "name",
            pkColumnValue = "ru.isands.test.estore.dao.entity.ElectroItem",
            table = "counter",
            valueColumnName = "currentid",
            allocationSize = 1
    )
    Long id;

    /**
     * Навзвание товара
     */
    @Column(name = "name", nullable = false, length = 150)
    String name;

    /**
     * Тип товара
     */
    @JoinColumn(name = "etype", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    ElectroType etypeId;

    /**
     * Цена товара
     */
    @Column(name = "price", nullable = false)
    Long price;

    /**
     * Количество товара
     */
    @Column(name = "count", nullable = false)
    Integer count;

    /**
     * Признак архивности товара
     */
    @Column(name = "archived", nullable = false)
    Boolean archived;

    /**
     * Описание товара
     */
    @Column(name = "description", nullable = false)
    String description;

    @OneToMany(mappedBy = "electroItem")
    List<ElectroShop> electroShops;

    @OneToMany(mappedBy = "electroId")
    List<Purchase> purchases;
}
