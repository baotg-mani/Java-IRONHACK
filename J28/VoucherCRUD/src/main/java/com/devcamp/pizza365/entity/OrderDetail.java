package com.devcamp.pizza365.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_details")
public class OrderDetail  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="price_each")
	private BigDecimal priceEach;

	@Column(name="quantity_order")
	private int quantityOrder;

	private int product_id;

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@ManyToOne
	private Order order;

	public OrderDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPriceEach() {
		return this.priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public int getQuantityOrder() {
		return this.quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}