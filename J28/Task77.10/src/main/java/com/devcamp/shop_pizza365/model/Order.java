package com.devcamp.shop_pizza365.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date")
	private Date orderDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "required_date")
	private Date requiredDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shipped_date")
	private Date shippedDate;

	private String status;

	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	private List<OrderDetail> orderDetails;

	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Customer customer;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int id, String comments, Date orderDate, Date requiredDate, Date shippedDate, String status,
			List<OrderDetail> orderDetails, Customer customer) {
		super();
		this.id = id;
		this.comments = comments;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.orderDetails = orderDetails;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
