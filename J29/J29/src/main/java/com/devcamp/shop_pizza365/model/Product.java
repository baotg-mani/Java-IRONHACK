package com.devcamp.shop_pizza365.model;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnoreProperties(value = "product")
	private List<OrderDetail> orderDetails;
	
	@Column(name = "product_code", nullable = false)
	private String productCode;
	
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@Column(name = "product_description", nullable = false)
	private String productDescription;
	
//	Khi dùng @JsonIgnoreProperties thì bên n-1 không được dùng 'fetch = FetchType.LAZY', nếu dùng sẽ không hiện obj của FK bên n-1
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "product_line_id", nullable = false)
	@JsonIgnoreProperties(value = "products")
	private ProductLine productLineObj;
	
	@Column(name = "product_scale")
	private String productScale;
	
	@Column(name = "product_vendor", nullable = false)
	private String productVendor;
	
	@Column(name = "quantity_in_stock", nullable = false)
	private int quantityInStock;
	
	@Column(name = "buy_price", nullable = false)
	private BigDecimal buyPrice;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, List<OrderDetail> orderDetails, String productCode, String productName,
			String productDescription, ProductLine productLineObj, String productScale, String productVendor,
			int quantityInStock, BigDecimal buyPrice) {
		super();
		this.id = id;
		this.orderDetails = orderDetails;
		this.productCode = productCode;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productLineObj = productLineObj;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
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

	public ProductLine getProductLineObj() {
		return productLineObj;
	}

	public void setProductLineObj(ProductLine productLineObj) {
		this.productLineObj = productLineObj;
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

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	
}
