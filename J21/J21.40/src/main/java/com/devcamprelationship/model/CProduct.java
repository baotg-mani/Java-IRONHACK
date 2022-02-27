package com.devcamprelationship.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class CProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private long price;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "products")
	private Set<COrder> orders;
	
	public CProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CProduct(long id, String name, long price, Set<COrder> orders) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.orders = orders;
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Set<COrder> getOrders() {
		return orders;
	}
	public void setOrders(Set<COrder> orders) {
		this.orders = orders;
	}
	
}
