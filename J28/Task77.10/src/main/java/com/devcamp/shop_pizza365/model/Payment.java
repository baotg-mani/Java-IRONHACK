package com.devcamp.shop_pizza365.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "check_number")
	private String checkNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date") 
	private Date paymentDate;
	
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int id, String checkNumber, Date paymentDate, BigDecimal amount, Customer customer) {
		super();
		this.id = id;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
