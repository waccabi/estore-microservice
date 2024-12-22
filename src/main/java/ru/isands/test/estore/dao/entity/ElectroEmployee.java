package ru.isands.test.estore.dao.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(ElectroEmployeePK.class)
@Table(name = "electro_employee")
public class ElectroEmployee {

    @Id
    @Column(name = "employeeId", nullable = false)
    Long employeeId;

    @Id
    @Column(name = "electroTypeId", nullable = false)
    Long electroTypeId;

    @ManyToOne
    @JoinColumn(name = "electro_type", insertable = false, updatable = false)
    ElectroType electroType;

    @ManyToOne
    @JoinColumn(name = "employee_", insertable = false, updatable = false)
    Employee employee;
}
