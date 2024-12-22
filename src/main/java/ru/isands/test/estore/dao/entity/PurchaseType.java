package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "purchase_type")
public class PurchaseType implements Serializable {

    /**
     * Идейтинтификатор типа покупки
     */
    @Id
    @Column(name = "id_", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "purchase_typeid_counter")
    @TableGenerator(
            name = "purchase_typeid_counter",
            pkColumnName = "name",
            pkColumnValue = "ru.isands.test.estore.dao.entity.PurchaseType",
            table = "counter",
            valueColumnName = "currentid",
            allocationSize = 1)
    Long id;

    /**
     * Навзание покупки
     */
    @Column(name = "name", nullable = false, length = 150)
    String name;

    @OneToMany(mappedBy = "typeId")
    List<Purchase> purchases;
}
