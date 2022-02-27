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

import com.devcamp.shop_pizza365.model.Customer;
import com.devcamp.shop_pizza365.repository.CustomerRepository;

@RestController
@CrossOrigin
public class CustomerController {
	@Autowired
	private CustomerRepository pCustomerRepository;
	
	@GetMapping("/customer/all")
	public ResponseEntity<Object> getAllCustomer() {
		try {
			return new ResponseEntity<>(pCustomerRepository.findAllCustomer(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable int id){
		try {
			return new ResponseEntity<>(pCustomerRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// GET số lượng customer theo 4 nước usa, singapore, france, pain
	@GetMapping("/number-countries")
	public ResponseEntity<Object> countCusOf4Countries() {
		try {
			return new ResponseEntity<>(pCustomerRepository.numberCusOf4Countries(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/customer") 
	public ResponseEntity<Object> createCustomer(@RequestBody @Valid Customer cCustomer){
			try {
				Customer vCustomer = new Customer();
				
				vCustomer.setAddress(cCustomer.getAddress());
				vCustomer.setCity(cCustomer.getCity());
				vCustomer.setCountry(cCustomer.getCountry());
				vCustomer.setCreditLimit(cCustomer.getCreditLimit());
				vCustomer.setFirstName(cCustomer.getFirstName());
				vCustomer.setLastName(cCustomer.getLastName());
				vCustomer.setPhoneNumber(cCustomer.getPhoneNumber());
				vCustomer.setPostalCode(cCustomer.getPostalCode());
				vCustomer.setSalesRepEmployeeNumber(cCustomer.getSalesRepEmployeeNumber());
				vCustomer.setState(cCustomer.getState());
				
				pCustomerRepository.save(vCustomer);
				return new ResponseEntity<>(pCustomerRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable Integer id, @RequestBody Customer cCustomer) {
		Optional<Customer> customerData = pCustomerRepository.findById(id);
		if (customerData.isPresent()) {
			try {
				Customer newUpdate = customerData.get();
				newUpdate.setAddress(cCustomer.getAddress());
				newUpdate.setCity(cCustomer.getCity());
				newUpdate.setCountry(cCustomer.getCountry());
				newUpdate.setCreditLimit(cCustomer.getCreditLimit());
				newUpdate.setFirstName(cCustomer.getFirstName());
				newUpdate.setLastName(cCustomer.getLastName());
				newUpdate.setPhoneNumber(cCustomer.getPhoneNumber());
				newUpdate.setPostalCode(cCustomer.getPostalCode());
				newUpdate.setSalesRepEmployeeNumber(cCustomer.getSalesRepEmployeeNumber());
				newUpdate.setState(cCustomer.getState());
				
				return new ResponseEntity<>(pCustomerRepository.save(newUpdate), HttpStatus.OK);
			} catch(Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Customer not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Object> deleteCustomerById(@PathVariable Integer id) {
		if (pCustomerRepository.findById(id).isPresent()) {
			try {
				pCustomerRepository.deleteById(id);
				return new ResponseEntity<>(pCustomerRepository.findAll(), HttpStatus.OK);
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
