package com.devcamp.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pizza_order")
public class COrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "order_code", unique = true)
	private String orderCode;

	@Column(name = "pizza_size")
	private String pizzaSize;

	@Column(name = "pizza_type")
	private String pizzaType;

	@Column(name = "voucher_code")
	private String voucherCode;

	@Column(name = "price")
	private long price;

	@Column(name = "paid")
	private long paid;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	// @JsonBackReference
	private CCustomer customer;

	public COrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public COrder(long id, String orderCode, String pizzaSize, String pizzaType, String voucherCode, long price,
			long paid, CCustomer customer) {
		super();
		this.id = id;
		this.orderCode = orderCode;
		this.pizzaSize = pizzaSize;
		this.pizzaType = pizzaType;
		this.voucherCode = voucherCode;
		this.price = price;
		this.paid = paid;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public String getPizzaType() {
		return pizzaType;
	}

	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getPaid() {
		return paid;
	}

	public void setPaid(long paid) {
		this.paid = paid;
	}

	public CCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CCustomer customer) {
		this.customer = customer;
	}

}
