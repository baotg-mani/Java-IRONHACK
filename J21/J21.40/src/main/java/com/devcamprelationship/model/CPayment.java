package com.devcamprelationship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class CPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "total_price")
	private long totalPrice;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "address")
	private String address;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id", nullable = false)
	private COrder order;
	
	public CPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CPayment(long id, long totalPrice, String status, String address, COrder order) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.status = status;
		this.address = address;
		this.order = order;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public COrder getOrder() {
		return order;
	}
	public void setOrder(COrder order) {
		this.order = order;
	}
	
}
