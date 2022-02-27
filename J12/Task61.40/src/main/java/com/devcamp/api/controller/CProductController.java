package com.devcamp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.api.model.CProduct;
import com.devcamp.api.repository.IProductRepository;

@CrossOrigin
@RestController
public class CProductController {
	@Autowired
	IProductRepository pCustomerRepository;

	@GetMapping("/products")
	public ResponseEntity<List<CProduct>> getAllProducts() {
		try {
			List<CProduct> listProduct = new ArrayList<CProduct>();

			pCustomerRepository.findAll().forEach(listProduct::add);

			return new ResponseEntity<>(listProduct, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products1")
	public List<CProduct> getAllProducts1() {
		try {
			List<CProduct> listProduct = new ArrayList<CProduct>();
			pCustomerRepository.findAll().forEach(listProduct::add);
			return listProduct;
		} catch (Exception e) {
			return null;
		}
	}
}
