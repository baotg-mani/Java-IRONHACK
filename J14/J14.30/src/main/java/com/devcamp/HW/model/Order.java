package com.devcamp.HW.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "order_code")
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
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Product> products;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Order(long id, String orderCode, String pizzaSize, String pizzaType, String voucherCode, long price,
			long paid, Set<Product> products, User user) {
		super();
		this.id = id;
		this.orderCode = orderCode;
		this.pizzaSize = pizzaSize;
		this.pizzaType = pizzaType;
		this.voucherCode = voucherCode;
		this.price = price;
		this.paid = paid;
		this.products = products;
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
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
