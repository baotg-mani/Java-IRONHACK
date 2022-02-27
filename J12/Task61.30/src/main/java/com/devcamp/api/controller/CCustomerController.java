package com.devcamp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.api.model.CCustomer;
import com.devcamp.api.repository.ICustomerRepository;

@CrossOrigin
@RestController
public class CCustomerController {
	@Autowired
	ICustomerRepository pCustomerRepository;

	@GetMapping("/customers")
	public ResponseEntity<List<CCustomer>> getAllCustomers() {
		try {
			List<CCustomer> listCustomer = new ArrayList<CCustomer>();

			pCustomerRepository.findAll().forEach(listCustomer::add);

			return new ResponseEntity<>(listCustomer, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customers1")
	public List<CCustomer> getAllVouchers1() {
		try {
			List<CCustomer> listCustomer = new ArrayList<CCustomer>();
			pCustomerRepository.findAll().forEach(listCustomer::add);
			return listCustomer;
		} catch (Exception e) {
			return null;
		}
	}
}
