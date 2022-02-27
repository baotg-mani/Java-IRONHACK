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
import com.devcamp.shop_pizza365.model.Payment;
import com.devcamp.shop_pizza365.repository.CustomerRepository;
import com.devcamp.shop_pizza365.repository.PaymentRepository;

@RestController
@CrossOrigin
public class PaymentController {
	@Autowired
	private PaymentRepository pPaymentRepository;

	@Autowired
	private CustomerRepository pCustomerRepository;

	@GetMapping("/payment/all")
	public ResponseEntity<Object> getAllPayment() {
		try {
			return new ResponseEntity<>(pPaymentRepository.findAllPayment(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/payment/{id}")
	public ResponseEntity<Object> getPaymentById(@PathVariable int id) {
		try {
			return new ResponseEntity<>(pPaymentRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/payment/customerId/{customerId}")
	public ResponseEntity<Object> getPaymentsByCustomerId(@PathVariable int customerId) {
		try {
			return new ResponseEntity<>(pPaymentRepository.findPaymentsByCustomerId(customerId, null), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/payment/{customerId}")
	public ResponseEntity<Object> createPaymentByCustomerId(@Valid @PathVariable Integer customerId,
			@RequestBody Payment cPayment) {
		Optional<Customer> customerData = pCustomerRepository.findById(customerId);
		if (customerData.isPresent()) {
			try {
				Payment vPayment = new Payment();
				vPayment.setAmount(cPayment.getAmount());
				vPayment.setCheckNumber(cPayment.getCheckNumber());
				vPayment.setPaymentDate(cPayment.getPaymentDate());
				vPayment.setCustomer(customerData.get()); // memo! lấy theo customer id

				pPaymentRepository.save(vPayment);
				return new ResponseEntity<>(pPaymentRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("customer not found by id");
			return new ResponseEntity<>("customerID not found, enter again!", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/payment/{id}")
	public ResponseEntity<Object> updatePayment(@Valid @PathVariable Integer id, @RequestBody Payment cPayment) {
		Optional<Payment> paymentData = pPaymentRepository.findById(id);
		if (paymentData.isPresent()) {
			try {
				Payment newUpdate = paymentData.get();
				newUpdate.setAmount(cPayment.getAmount());
				newUpdate.setCheckNumber(cPayment.getCheckNumber());
				newUpdate.setPaymentDate(cPayment.getPaymentDate());

				return new ResponseEntity<>(pPaymentRepository.save(newUpdate), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("payment not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/payment/{id}")
	public ResponseEntity<Object> deletePaymentById(@PathVariable Integer id) {
		if (pPaymentRepository.findById(id).isPresent()) {
			try {
				pPaymentRepository.deleteById(id);
				return new ResponseEntity<>(pPaymentRepository.findAll(), HttpStatus.OK);
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