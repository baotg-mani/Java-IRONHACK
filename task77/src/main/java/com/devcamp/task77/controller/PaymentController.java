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

import com.devcamp.task77.model.Payment;
import com.devcamp.task77.repository.PaymentRepo;

@CrossOrigin
@RestController
public class PaymentController {
	@Autowired
	PaymentRepo paymentRepo;
	
	@GetMapping("/payments")
	public ResponseEntity<Object> getAllPayments() {
		try {
			return new ResponseEntity<>(paymentRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/payments/{paymentId}")
	public ResponseEntity<Object> getPaymentById(@PathVariable Integer paymentId) {
		try {
			Optional<Payment> paymentFound = paymentRepo.findById(paymentId);
			if (paymentFound.isPresent()) {
				return new ResponseEntity<>(paymentFound.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/payments")
	public ResponseEntity<Object> createPayment(@Valid @RequestBody Payment newPayment) {
		try {
			return new ResponseEntity<>(paymentRepo.save(newPayment), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/payments/{paymentId}")
	public ResponseEntity<Object> updatePayment(@PathVariable Integer paymentId, @Valid @RequestBody Payment newPayment) {
		try {
			Optional<Payment> paymentFound = paymentRepo.findById(paymentId);
			if (paymentFound.isPresent()) {
				Payment updatePayment = paymentFound.get();
				updatePayment.setAmount(newPayment.getAmount());
				updatePayment.setCheckNumber(newPayment.getCheckNumber());
				updatePayment.setPaymentDate(newPayment.getPaymentDate());
				return new ResponseEntity<>(paymentRepo.save(updatePayment), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/payments/{paymentId}")
	public ResponseEntity<Object> deletePaymentById(@PathVariable Integer paymentId) {
		try {
			Optional<Payment> paymentFound = paymentRepo.findById(paymentId);
			if (paymentFound.isPresent()) {
				paymentRepo.deleteById(paymentId);
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/payments")
	public ResponseEntity<Object> deleteAllPayment() {
		try {
			paymentRepo.deleteAll();
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
