package com.devcamp.pizza365.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="payments")
public class Payment  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private BigDecimal ammount;

	@Column(name="check_number")
	private String checkNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date")
	private Date paymentDate;

	@ManyToOne
	@JsonIgnore
	private Customer customer;

	public Payment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmmount() {
		return this.ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}

	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}