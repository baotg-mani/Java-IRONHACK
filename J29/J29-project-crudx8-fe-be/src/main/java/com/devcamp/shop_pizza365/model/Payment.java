package com.devcamp.shop_pizza365.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Thiếu checkNumber!")
	@Column(name = "check_number")
	private String checkNumber;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date", nullable = true)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date paymentDate;

	@NotNull
	@Column(nullable = false)
	private BigDecimal amount;

//	Khi dùng @JsonIgnoreProperties thì bên n-1 không được dùng 'fetch = FetchType.LAZY', nếu dùng sẽ không hiện obj của FK bên n-1
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnoreProperties(value = "payments")
	private Customer customer;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int id, @NotEmpty(message = "Thiếu checkNumber!") String checkNumber, @NotNull Date paymentDate,
			@NotNull BigDecimal amount, Customer customer) {
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
