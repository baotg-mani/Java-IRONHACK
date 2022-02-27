package com.devcamp.task77.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

import com.devcamp.task77.model.Customer;
import com.devcamp.task77.repository.CustomerRepo;

@CrossOrigin
@RestController
public class CustomerController {
	@Autowired
	CustomerRepo customerRepo;
	
	@GetMapping("/customers")
	public ResponseEntity<Object> getAllCustomer() {
		try {
			return new ResponseEntity<>(customerRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Object> getCustomerById(@PathVariable Integer customerId) {
		try {
			Optional<Customer> customerFound = customerRepo.findById(customerId);
			if (customerFound.isPresent()) {
				return new ResponseEntity<>(customerFound.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customers/firstname/{firstName}/lastname/{lastName}")
	public ResponseEntity<Object> getCustomerByFirstNameOrLastName(@PathVariable String firstName,
			@PathVariable String lastName) {
		try {
			return new ResponseEntity<>(customerRepo.findCustomerByFirstNameAndLastName(firstName, lastName)
					, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customers/city/{city}/state/{state}")
	public ResponseEntity<Object> getCustomerByCityAndState(@PathVariable String city,
			@PathVariable String state) {
		try {
			return new ResponseEntity<>(customerRepo.findCustomerByCityAndSate(city, state, PageRequest.of(1, 3)) 
					, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer newCustomer) {
		try {
			return new ResponseEntity<>(customerRepo.save(newCustomer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/customers/{customerId}")
	public ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer newCustomer, @PathVariable int customerId) {
		try {
			Optional<Customer> customerFound = customerRepo.findById(customerId);
			if (customerFound.isPresent()) {
				Customer updateCustomer = customerFound.get();
				updateCustomer.setAddress(newCustomer.getAddress());
				updateCustomer.setCity(newCustomer.getCity());
				updateCustomer.setCountry(newCustomer.getCountry());
				updateCustomer.setCreditLimit(newCustomer.getCreditLimit());
				updateCustomer.setFirstName(newCustomer.getFirstName());
				updateCustomer.setLastName(newCustomer.getLastName());
				updateCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
				updateCustomer.setPostalCode(newCustomer.getPostalCode());
				updateCustomer.setSalesRepEmployeeNumber(newCustomer.getSalesRepEmployeeNumber());
				updateCustomer.setState(newCustomer.getState());
				return new ResponseEntity<>(customerRepo.save(updateCustomer), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/customers")
	public ResponseEntity<Object> deleteAllCustomer() {
		try {
			customerRepo.deleteAll();
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Object> deleteCustomerById(@PathVariable Integer customerId) {
		try {
			Optional<Customer> customerFound = customerRepo.findById(customerId);
			if (customerFound.isPresent()) {
				customerRepo.deleteById(customerId);
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
