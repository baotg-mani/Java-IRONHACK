package com.devcamp.shop_pizza365.controller;

import java.util.Optional;

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

import com.devcamp.shop_pizza365.model.ProductLine;
import com.devcamp.shop_pizza365.repository.ProductLineRepository;

@RestController
@CrossOrigin
public class ProductLineController {
	@Autowired
	private ProductLineRepository pProductLineRepository;
	
	@GetMapping("/product-line/all")
	public ResponseEntity<Object> getAllProductLine() {
		try {
			return new ResponseEntity<>(pProductLineRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product-line/{id}")
	public ResponseEntity<Object> getProductLineById(@PathVariable int id){
		try {
			return new ResponseEntity<>(pProductLineRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/product-line") 
	public ResponseEntity<Object> createProductLine(@RequestBody ProductLine cProductLine){
		try {
			pProductLineRepository.save(cProductLine);
			return new ResponseEntity<>(pProductLineRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getCause().getCause().getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/product-line/{id}")
	public ResponseEntity<Object> updateProductLine(@PathVariable Integer id, @RequestBody ProductLine cProductLine) {
		Optional<ProductLine> productLineData = pProductLineRepository.findById(id);
		if (productLineData.isPresent()) {
			try {
				ProductLine newUpdate = productLineData.get();
				newUpdate.setDescription(cProductLine.getDescription());
				newUpdate.setProductLine(cProductLine.getProductLine());
				newUpdate.setProducts(cProductLine.getProducts());
				
				return new ResponseEntity<>(pProductLineRepository.save(newUpdate), HttpStatus.OK);
			} catch(Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Product Line not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/product-line/{id}")
	public ResponseEntity<Object> deleteProductLineById(@PathVariable Integer id) {
		if (pProductLineRepository.findById(id).isPresent()) {
			try {
				pProductLineRepository.deleteById(id);
				return new ResponseEntity<>(pProductLineRepository.findAll(), HttpStatus.OK);
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
