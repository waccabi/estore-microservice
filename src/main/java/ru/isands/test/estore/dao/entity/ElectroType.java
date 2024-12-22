package ru.isands.test.estore.dao.entity;

import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "etype")
public class ElectroType implements Serializable {

    /**
     * Идентификатор электроники
     */
    @Id
    @Column(name = "id_", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "etype_counter")
    @TableGenerator(
            name = "etype_counter",
            pkColumnName = "name",
            pkColumnValue = "ru.isands.test.estore.dao.entity.ElectroType",
            table = "counter",
            valueColumnName = "currentid",
            allocationSize = 1
    )
    Long id;

    /**
     * Название электроники
     */
    @Column(name ="name", nullable = false,length=150)
    String name;

    @OneToMany(mappedBy = "electroType")
    List<ElectroEmployee> electroEmployees;

    @OneToMany(mappedBy = "etypeId")
    List<ElectroItem> electroItems;
}
