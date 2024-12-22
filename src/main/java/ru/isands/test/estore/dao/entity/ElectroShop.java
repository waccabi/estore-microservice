package ru.isands.test.estore.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ElectroShopPK.class)
@Table(name = "store_eshop")
public class ElectroShop {
	
	/**
	 * Идентификатор магазина
	 */
	@Id
	@Column(name = "shopId", nullable = false)
	Long shopId;
	
	/**
	 * Идентификатор электротовара
	 */
	@Id
	@Column(name = "electroItemId", nullable = false)
	Long electroItemId;
	
	/**
	 * Оставшееся количество
	 */
	@Column(name = "count_", nullable = false)
	int count;

	@ManyToOne
	@JoinColumn(name = "shopId", insertable = false, updatable = false)
	Shop shop;

	@ManyToOne
	@JoinColumn(name = "electroItemId", insertable = false, updatable = false)
	ElectroItem electroItem;

}
