package com.devcamp.task77.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customerId;

	@NotEmpty(message = "check number can't be empty")
	@Column(name = "check_number", nullable = false)
	private String checkNumber;

	@NotNull
	@Column(name = "payment_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	@NotNull
	@Column(name = "ammount", nullable = false)
	private java.math.BigDecimal amount;

	public Payment() {
		super();
	}

	public Payment(int id, Customer customerId, @NotEmpty(message = "check number can't be empty") String checkNumber,
			@NotNull Date paymentDate, BigDecimal amount) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
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

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}

}
