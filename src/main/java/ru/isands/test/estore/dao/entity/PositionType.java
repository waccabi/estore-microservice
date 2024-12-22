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
@Table(name = "position_type")
public class PositionType implements Serializable {

    /**
     * Идейнтификаторо дожности
     */
    @Id
    @Column(name = "id_", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "position_typeid_counter")
    @TableGenerator(
            name = "position_typeid_counter",
            pkColumnName = "name",
            pkColumnValue = "ru.isands.test.estore.dao.entity.PositionType",
            table = "counter",
            valueColumnName = "currnetid",
            allocationSize = 1
    )
    Long id;

    /**
     * Название должности
     */
    @Column(name = "name", nullable = false, length = 150)
    String name;

    @OneToMany(mappedBy = "positionId")
    List<Employee> employees;
}
