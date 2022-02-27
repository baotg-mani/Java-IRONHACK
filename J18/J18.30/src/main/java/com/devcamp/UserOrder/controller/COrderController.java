package com.devcamp.UserOrder.controller;

import java.util.List;
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

import com.devcamp.UserOrder.model.COrder;
import com.devcamp.UserOrder.model.CUser;
import com.devcamp.UserOrder.repository.IOrderRepository;
import com.devcamp.UserOrder.repository.IUserRepository;

@RestController
public class COrderController {
	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private IUserRepository userRepository;

	@CrossOrigin
	@PostMapping("/order/create/{id}")
	public ResponseEntity<Object> createOrder(@PathVariable("id") Long id, @RequestBody COrder cOrder) {
		try {
			Optional<CUser> userData = userRepository.findById(id);
			if (userData.isPresent()) {
				CUser _user = userData.get();
				cOrder.setUser(_user);
				COrder savedRole = orderRepository.save(cOrder);
				return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity()
					.body("Failed to Create specified Voucher: " + e.getCause().getCause().getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@CrossOrigin
	@PutMapping("/order/update/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable("id") Long id, @RequestBody COrder cOrder) {
		Optional<COrder> orderData = orderRepository.findById(id);
		if (orderData.isPresent()) {
			COrder newOrder = orderData.get();
			newOrder.setPizzaSize(cOrder.getPizzaSize());
			newOrder.setPizzaType(cOrder.getPizzaType());
			COrder savedOrder = orderRepository.save(newOrder);
			return new ResponseEntity<>(savedOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@DeleteMapping("/order/delete/{id}")
	public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {
		try {
			orderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/order/details/{id}")
	public COrder getOrderById(@PathVariable Long id) {
		if (orderRepository.findById(id).isPresent())
			return orderRepository.findById(id).get();
		else
			return null;
	}
	
	@CrossOrigin
	@GetMapping("/order/all")
	public List<COrder> getAllOrder(){
		return orderRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}/orders")
	public List<COrder> getOrdersByUserId(@PathVariable(value = "userId") Long userId) {
		return orderRepository.findByUserId(userId);
	}
}

