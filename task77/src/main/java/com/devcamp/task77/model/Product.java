package com.devcamp.task77.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "product_code", nullable = false)
	private String productCode;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "product_description")
	private String productDescription;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "product_line_id")
	@JsonIgnore
	private ProductLine productLineId;

	@Column(name = "product_scale", nullable = false)
	private String productScale;

	@Column(name = "product_vendor", nullable = false)
	private String productVendor;

	@Column(name = "quantity_in_stock", nullable = false)
	private int quantityInStock;

	@Column(name = "buy_price", nullable = false)
	private java.math.BigDecimal buyPrice;

	@OneToMany(mappedBy = "productId", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<OrderDetail> orderDetail;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String productCode, String productName, String productDescription, ProductLine productLineId,
			String productScale, String productVendor, int quantityInStock, BigDecimal buyPrice,
			List<OrderDetail> orderDetail) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productLineId = productLineId;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.orderDetail = orderDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public ProductLine getProductLineId() {
		return productLineId;
	}

	public void setProductLineId(ProductLine productLineId) {
		this.productLineId = productLineId;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public java.math.BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(java.math.BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
