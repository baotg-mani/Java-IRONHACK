package com.devcamp.shop_pizza365.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "order_details")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "quantity_order")
	private int quantityOrder;
	
	@Column(name = "price_each") 
	private BigDecimal priceEach;
	
	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Order order;
	
	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Product product;

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(int id, int quantityOrder, BigDecimal priceEach, Order order, Product product) {
		super();
		this.id = id;
		this.quantityOrder = quantityOrder;
		this.priceEach = priceEach;
		this.order = order;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
