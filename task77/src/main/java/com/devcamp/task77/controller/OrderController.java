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

import com.devcamp.task77.model.Order;
import com.devcamp.task77.repository.OrderRepo;

@RestController
@CrossOrigin
public class OrderController {
	@Autowired
	OrderRepo orderRepo;
	
	@GetMapping("/orders")
	public ResponseEntity<Object> getAllOrder() {
		try {
			return new ResponseEntity<>(orderRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Object> getOrderById(@PathVariable Integer orderId) {
		try {
			Optional<Order> orderFound = orderRepo.findById(orderId);
			if (orderFound.isPresent()) {
				return new ResponseEntity<>(orderFound.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Object> createOrder (@Valid @RequestBody Order newOrder) {
		try {
			return new ResponseEntity<>(orderRepo.save(newOrder), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/orders/{orderId}")
	public ResponseEntity<Object> updateOrder(@PathVariable Integer orderId, @Valid @RequestBody Order newOrder) {
		try {
			Optional<Order> orderFound = orderRepo.findById(orderId);
			if (orderFound.isPresent()) {
				Order updateOrder = orderFound.get();
				updateOrder.setComments(newOrder.getComments());
				updateOrder.setOrderDate(newOrder.getOrderDate());
				updateOrder.setRequiredDate(newOrder.getRequiredDate());
				updateOrder.setShippedDate(newOrder.getShippedDate());
				updateOrder.setStatus(newOrder.getStatus());
				return new ResponseEntity<>(orderRepo.save(updateOrder), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<Object> deleteOrderById(@PathVariable Integer orderId) {
		try {
			Optional<Order> orderFound = orderRepo.findById(orderId);
			if (orderFound.isPresent()) {
				orderRepo.deleteById(orderId);
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/orders")
	public ResponseEntity<Object> deleteAllOrder() {
		try {
			orderRepo.deleteAll();
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
