package com.devcamp.HW.model;

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
@Table(name = "product_table")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name_product")
	private String name;
	
	@Column(name = "type_product")
	private String type;
	
	@Column(name = "color_product")
	private String color;
	
	@Column(name = "price_product")
	private String price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(long id, String name, String type, String color, String price, Order order) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.color = color;
		this.price = price;
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
