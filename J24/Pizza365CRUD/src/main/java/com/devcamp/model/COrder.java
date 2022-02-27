package com.devcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_table")
public class COrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties(value = "orders")
//	@JsonBackReference
	private CUser user;

	public COrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public COrder(long id, String orderCode, String pizzaSize, String pizzaType, String voucherCode, long price,
			long paid, CUser user) {
		super();
		this.id = id;
		this.orderCode = orderCode;
		this.pizzaSize = pizzaSize;
		this.pizzaType = pizzaType;
		this.voucherCode = voucherCode;
		this.price = price;
		this.paid = paid;
		this.user = user;
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

	public CUser getUser() {
		return user;
	}

	public void setUser(CUser user) {
		this.user = user;
	}

}
