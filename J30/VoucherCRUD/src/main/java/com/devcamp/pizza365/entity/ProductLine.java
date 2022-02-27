package com.devcamp.pizza365.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product_lines database table.
 * 
 */
@Entity
@Table(name="product_lines")
@NamedQuery(name="ProductLine.findAll", query="SELECT p FROM ProductLine p")
public class ProductLine  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String description;

	@Column(name="product_line")
	private String productLine;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productLine")
	private List<Product> products;

	public ProductLine() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductLine() {
		return this.productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductLine(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductLine(null);

		return product;
	}

}