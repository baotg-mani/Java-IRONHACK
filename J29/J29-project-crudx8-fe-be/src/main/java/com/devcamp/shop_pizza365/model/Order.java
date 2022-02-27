package com.devcamp.shop_pizza365.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String comments;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "order_date", nullable = false)
	private Date orderDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "required_date", nullable = false)
	private Date requiredDate;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "shipped_date")
	private Date shippedDate;

	private String status;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnoreProperties(value = "order")
	private List<OrderDetail> orderDetails;

//	Khi dùng @JsonIgnoreProperties thì bên n-1 không được dùng 'fetch = FetchType.LAZY', nếu dùng sẽ không hiện obj của FK bên n-1
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnoreProperties(value = "orders")
	private Customer customer;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int id, String comments, @NotNull Date orderDate, @NotNull Date requiredDate, Date shippedDate,
			String status, List<OrderDetail> orderDetails, Customer customer) {
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
