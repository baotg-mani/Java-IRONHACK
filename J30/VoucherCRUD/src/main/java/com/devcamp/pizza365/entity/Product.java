package com.devcamp.pizza365.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "buy_price")
	private BigDecimal buyPrice;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_scale")
	private String productScale;

	@Column(name = "product_vendor")
	private String productVendor;

	@Column(name = "quantity_in_stock")
	private int quantityInStock;

	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

	@ManyToOne
	@JoinColumn(name = "product_line_id")
	@JsonIgnore
	private ProductLine productLine;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getBuyPrice() {
		return this.buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductScale() {
		return this.productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return this.productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public int getQuantityInStock() {
		return this.quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setProduct(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setProduct(null);

		return orderDetail;
	}

	@JsonIgnore
	public ProductLine getProductLine() {
		return this.productLine;
	}

	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}

}