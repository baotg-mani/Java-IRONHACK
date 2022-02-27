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

import com.devcamp.shop_pizza365.model.Customer;
import com.devcamp.shop_pizza365.model.Order;
import com.devcamp.shop_pizza365.repository.CustomerRepository;
import com.devcamp.shop_pizza365.repository.OrderRepository;

@RestController
@CrossOrigin
public class OrderController {
	@Autowired
	private OrderRepository pOrderRepository;
	@Autowired
	private CustomerRepository pCustomerRepository;
	
	@GetMapping("/order/all")
	public ResponseEntity<Object> getAllOrder() {
		try {
			return new ResponseEntity<>(pOrderRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable int id){
		try {
			return new ResponseEntity<>(pOrderRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/order/{customerId}") 
	public ResponseEntity<Object> createOrderByCustomerId(@PathVariable Integer customerId, @RequestBody Order cOrder){
		Optional<Customer> customerData = pCustomerRepository.findById(customerId);
		if (customerData.isPresent()) {
			try {
				Order vOrder = new Order();
				vOrder.setComments(cOrder.getComments());
				vOrder.setOrderDate(cOrder.getOrderDate());
				vOrder.setOrderDetails(cOrder.getOrderDetails());
				vOrder.setRequiredDate(cOrder.getRequiredDate());
				vOrder.setShippedDate(cOrder.getShippedDate());
				vOrder.setStatus(cOrder.getStatus());
				vOrder.setCustomer(customerData.get()); //memo! lấy theo customer id
				
				pOrderRepository.save(vOrder);
				return new ResponseEntity<>(pOrderRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("customer not found by id");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}	
	}
	
	@PutMapping("/order/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable Integer id, @RequestBody Order cOrder) {
		Optional<Order> orderData = pOrderRepository.findById(id);
		if (orderData.isPresent()) {
			try {
				Order newUpdate = orderData.get();
				newUpdate.setComments(cOrder.getComments());
				newUpdate.setOrderDate(cOrder.getOrderDate());
				newUpdate.setOrderDetails(cOrder.getOrderDetails());
				newUpdate.setRequiredDate(cOrder.getRequiredDate());
				newUpdate.setShippedDate(cOrder.getShippedDate());
				newUpdate.setStatus(cOrder.getStatus());
				
				return new ResponseEntity<>(pOrderRepository.save(newUpdate), HttpStatus.OK);
			} catch(Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Order not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Object> deleteOrderById(@PathVariable Integer id) {
		if (pOrderRepository.findById(id).isPresent()) {
			try {
				pOrderRepository.deleteById(id);
				return new ResponseEntity<>(pOrderRepository.findAll(), HttpStatus.OK);
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
