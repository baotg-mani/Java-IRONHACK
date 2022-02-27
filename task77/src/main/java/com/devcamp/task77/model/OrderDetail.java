package com.devcamp.task77.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_details")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "order_id", nullable = false)
	@JsonIgnore
	private Order orderId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinColumn(name = "product_id", nullable = false)
	@JsonIgnore
	private Product productId;

	@NotNull
	@Column(name = "quantity_order", nullable = false)
	private int quantityOrder;

	@NotNull
	@Column(name = "price_each", nullable = false)
	private java.math.BigDecimal priceEach;

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(int id, Order orderId, Product productId, @NotNull int quantityOrder, BigDecimal priceEach) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantityOrder = quantityOrder;
		this.priceEach = priceEach;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

	public int getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public java.math.BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(java.math.BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

}
