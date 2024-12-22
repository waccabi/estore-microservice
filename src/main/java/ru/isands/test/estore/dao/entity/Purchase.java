package ru.isands.test.estore.dao.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "store_purchase")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Идентификатор покупки
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "purchase_counter")
	@TableGenerator(
			name = "purchase_counter",
			pkColumnName = "name",
			pkColumnValue = "ru.isands.test.estore.dao.entity.Purchase",
			table = "counter",
			valueColumnName = "currentid",
			allocationSize = 1)
	@Column(name = "id_", unique = true, nullable = false)
	Long id;
	
	/**
	 * Идентификатор электротовара
	 */
	@JoinColumn(name = "electroId", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	ElectroItem electroId;
	
	/**
	 * Идентификатор сотрудника
	 */
	@JoinColumn(name = "employeeId", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	Employee employeeId;
	
	/**
	 * Идентификатор магазина
	 */
	@JoinColumn(name = "shopId", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	Shop shopId;

	/**
	 * Тип покупки
	 */
	@JoinColumn(name = "typeId", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	PurchaseType typeId;

	/**
	 * Дата совершения покупки
	 */
	@Column(name = "purchaseDate", nullable = false)
	Date purchaseDate;

	
}