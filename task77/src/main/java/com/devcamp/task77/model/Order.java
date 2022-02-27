package com.devcamp.task77.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(name = "order_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@NotNull
	@Column(name = "required_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date requiredDate;

	@Column(name = "shipped_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date shippedDate;

	@Column(name = "status")
	private String status;

	@Column(name = "comments")
	private String comments;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnore
	private Customer customerId;

	@OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<OrderDetail> orderDetails;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int id, Date orderDate, Date requiredDate, Date shippedDate, String status, String comments) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}
}
