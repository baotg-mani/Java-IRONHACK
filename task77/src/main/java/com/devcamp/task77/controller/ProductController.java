package com.devcamp.task77.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.task77.model.Product;
import com.devcamp.task77.repository.ProductRepo;

@CrossOrigin
@RestController
public class ProductController {
	@Autowired
	ProductRepo productRepo;
	
	@GetMapping("/products")
	public ResponseEntity<Object> getAllProducts() {
		try {
			return new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Object> getProductById(@PathVariable Integer productId) {
		try {
			Optional<Product> productFound = productRepo.findById(productId);
			if (productFound.isPresent()) {
				return new ResponseEntity<>(productFound.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody Product newProduct) {
		try {
			return new ResponseEntity<>(productRepo.save(newProduct), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<Object> updateProduct(@PathVariable Integer productIdId, @RequestBody Product newProduct) {
		try {
			Optional<Product> productFound = productRepo.findById(productIdId);
			if (productFound.isPresent()) {
				Product updateProduct = productFound.get();
				updateProduct.setBuyPrice(newProduct.getBuyPrice());
				updateProduct.setProductCode(newProduct.getProductCode());
				updateProduct.setProductDescription(newProduct.getProductDescription());
				updateProduct.setProductName(newProduct.getProductName());
				updateProduct.setProductScale(newProduct.getProductScale());
				updateProduct.setProductVendor(newProduct.getProductVendor());
				updateProduct.setQuantityInStock(newProduct.getQuantityInStock());
				return new ResponseEntity<>(productRepo.save(updateProduct), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Object> deleteProductById(@PathVariable Integer productId) {
		try {
			Optional<Product> productFound = productRepo.findById(productId);
			if (productFound.isPresent()) {
				productRepo.deleteById(productId);
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/products")
	public ResponseEntity<Object> deleteAllProduct() {
		try {
			productRepo.deleteAll();
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
