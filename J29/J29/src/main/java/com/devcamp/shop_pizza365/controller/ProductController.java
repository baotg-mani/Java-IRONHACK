package com.devcamp.shop_pizza365.controller;

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

import com.devcamp.shop_pizza365.model.Product;
import com.devcamp.shop_pizza365.model.ProductLine;
import com.devcamp.shop_pizza365.repository.ProductLineRepository;
import com.devcamp.shop_pizza365.repository.ProductRepository;

@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductRepository pProductRepository;

	@Autowired
	private ProductLineRepository pProductLineRepository;

	@GetMapping("/product/all")
	public ResponseEntity<Object> getAllProduct() {
		try {
			return new ResponseEntity<>(pProductRepository.findAllProduct(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable int id) {
		try {
			return new ResponseEntity<>(pProductRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product/productLineId/{productLineId}")
	public ResponseEntity<Object> getOrdersByCustomerId(@PathVariable int productLineId) {
		try {
			return new ResponseEntity<>(pProductRepository.findProductsByProductLineId(productLineId, null),
					HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/product/{productLineId}")
	public ResponseEntity<Object> createProductByProductLineId(@Valid @RequestBody Product cProduct,
			@PathVariable Integer productLineId) {
		Optional<ProductLine> productLineData = pProductLineRepository.findById(productLineId);
		if (productLineData.isPresent()) {

			Product vProduct = new Product();
			vProduct.setBuyPrice(cProduct.getBuyPrice());
			vProduct.setOrderDetails(cProduct.getOrderDetails());
			vProduct.setProductCode(cProduct.getProductCode());
			vProduct.setProductName(cProduct.getProductName());
			vProduct.setProductDescription(cProduct.getProductDescription());
			vProduct.setProductScale(cProduct.getProductScale());
			vProduct.setProductVendor(cProduct.getProductVendor());
			vProduct.setQuantityInStock(cProduct.getQuantityInStock());

			vProduct.setProductLineObj(productLineData.get()); // memo!
			pProductRepository.save(vProduct);
			try {
				return new ResponseEntity<>(pProductRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Product Line not found by ID");
			return new ResponseEntity<>("Product line ID NOT_FOUND, enter again!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Object> updateProduct(@Valid @PathVariable Integer id, @RequestBody Product cProduct) {
		Optional<Product> productData = pProductRepository.findById(id);
		if (productData.isPresent()) {
			Product newUpdate = productData.get();
			newUpdate.setBuyPrice(cProduct.getBuyPrice());
			newUpdate.setProductCode(cProduct.getProductCode());
			newUpdate.setProductName(cProduct.getProductName());
			newUpdate.setProductDescription(cProduct.getProductDescription());
			newUpdate.setProductScale(cProduct.getProductScale());
			newUpdate.setProductVendor(cProduct.getProductVendor());
			newUpdate.setQuantityInStock(cProduct.getQuantityInStock());

			try {
				return new ResponseEntity<>(pProductRepository.save(newUpdate), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Product not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Object> deleteProductById(@PathVariable Integer id) {
		if (pProductRepository.findById(id).isPresent()) {
			try {
				pProductRepository.deleteById(id);
				return new ResponseEntity<>(pProductRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e);
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Id not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
