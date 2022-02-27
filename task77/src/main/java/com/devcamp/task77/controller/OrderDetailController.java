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

import com.devcamp.task77.model.OrderDetail;
import com.devcamp.task77.repository.OrderDetailRepo;

@CrossOrigin
@RestController
public class OrderDetailController {
	@Autowired
	OrderDetailRepo orderDetailRepo;
	
	@GetMapping("/order-details")
	public ResponseEntity<Object> getAllOrderDetail() {
		try {
			return new ResponseEntity<>(orderDetailRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order-details/{orderDetailId}")
	public ResponseEntity<Object> getOrderDetailById(@PathVariable Integer orderDetailId) {
		try {
			Optional<OrderDetail> orderDetailFound = orderDetailRepo.findById(orderDetailId);
			if (orderDetailFound.isPresent()) {
				return new ResponseEntity<>(orderDetailFound.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/order-details")
	public ResponseEntity<Object> createOrderDetail(@Valid @RequestBody OrderDetail newOrderDetail) {
		try {
			return new ResponseEntity<>(orderDetailRepo.save(newOrderDetail), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/order-details/{orderDetailId}")
	public ResponseEntity<Object> updateOrderDetail(@PathVariable Integer orderDetailId, @RequestBody OrderDetail newOrderDetail) {
		try {
			Optional<OrderDetail> orderDetailFound = orderDetailRepo.findById(orderDetailId);
			if (orderDetailFound.isPresent()) {
				OrderDetail updateOrderDetail = orderDetailFound.get();
				updateOrderDetail.setPriceEach(newOrderDetail.getPriceEach());
				updateOrderDetail.setQuantityOrder(newOrderDetail.getQuantityOrder());
				return new ResponseEntity<>(orderDetailRepo.save(updateOrderDetail), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/order-details/{orderDetailId}")
	public ResponseEntity<Object> deleteOrderDetailById (@PathVariable Integer orderDetailId) {
		try {
			Optional<OrderDetail> orderDetailFound = orderDetailRepo.findById(orderDetailId);
			if (orderDetailFound.isPresent()) {
				orderDetailRepo.deleteById(orderDetailId);
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/order-details")
	public ResponseEntity<Object> deleteAllOrderDetail() {
		try {
			orderDetailRepo.deleteAll();
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
