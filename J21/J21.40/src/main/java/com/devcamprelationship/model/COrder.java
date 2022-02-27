package com.devcamprelationship.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class COrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "create_at")
	private Date createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private CCustomer customer;

	@OneToOne(mappedBy = "order", 
			cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY)
	private CPayment payment;

	@ManyToMany(fetch = FetchType.LAZY, 
			  cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE 
					})
	@JoinTable(name = "order_product", 
		joinColumns = { @JoinColumn(name = "order_id") }, 
 inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private Set<CProduct> products;

	public COrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public COrder(long id, Date createAt, CCustomer customer, CPayment payment, Set<CProduct> products) {
		super();
		this.id = id;
		this.createAt = createAt;
		this.customer = customer;
		this.payment = payment;
		this.products = products;
	}

	public CPayment getPayment() {
		return payment;
	}

	public void setPayment(CPayment payment) {
		this.payment = payment;
	}

	public Set<CProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<CProduct> products) {
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public CCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CCustomer customer) {
		this.customer = customer;
	}

}
