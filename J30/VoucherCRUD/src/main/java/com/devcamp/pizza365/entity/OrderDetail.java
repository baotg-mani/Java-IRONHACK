package com.devcamp.pizza365.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "price_each")
	private BigDecimal priceEach;

	@Column(name = "quantity_order")
	private int quantityOrder;

	@ManyToOne
	@JsonIgnore
	private Product product;

	@ManyToOne
	@JsonIgnore
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

	@JsonIgnore
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@JsonIgnore
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}