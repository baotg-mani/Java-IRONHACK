package com.devcamp.shop_pizza365.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product_lines")
public class ProductLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "product_line")
	private String productLine;
	
	private String description;
	
	@OneToMany(mappedBy = "productLine")
	@JsonManagedReference
	private List<Product> products;

	public ProductLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductLine(int id, String productLine, String description, List<Product> products) {
		super();
		this.id = id;
		this.productLine = productLine;
		this.description = description;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
