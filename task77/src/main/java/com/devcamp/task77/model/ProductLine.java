package com.devcamp.task77.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "product_lines")
public class ProductLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "product_line", nullable = false)
	private String productLine;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany(mappedBy = "productLineId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
