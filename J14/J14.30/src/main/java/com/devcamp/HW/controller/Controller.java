package com.devcamp.HW.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.HW.model.Order;
import com.devcamp.HW.model.Product;
import com.devcamp.HW.model.User;
import com.devcamp.HW.repository.IOrderRepository;
import com.devcamp.HW.repository.IProductRepository;
import com.devcamp.HW.repository.IUserRepository;

@CrossOrigin
@RestController
public class Controller {
	@Autowired
	IUserRepository pUserRepository;
	@Autowired
	IOrderRepository pOrderRepository;
	@Autowired
	IProductRepository pProductRepository;
	
	@GetMapping("/devcamp-orders")
	public ResponseEntity<Set<Order>> getOdersByUserId(@RequestParam(value = "userId") String paramUserId){
		try {
			Long vId = Long.parseLong(paramUserId);
			Optional<User> oUser = pUserRepository.findById(vId);
			if(oUser.isPresent()) {
				User vUser = oUser.get();
				return new ResponseEntity<>(vUser.getOrders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/devcamp-products")
	public ResponseEntity<Set<Product>> getOdersByCustomerId(@RequestParam(value = "orderId") String paramOrderId){
		try {
			Long vId = Long.parseLong(paramOrderId);
			Optional<Order> oOrder = pOrderRepository.findById(vId);
			if(oOrder.isPresent()) {
				Order vOrder = oOrder.get();
				return new ResponseEntity<>(vOrder.getProducts(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order")
	public ResponseEntity<List<Order>> getAllOrder(){
		try {
			List<Order> pOrders = new ArrayList<Order>();
			pOrderRepository.findAll().forEach(pOrders::add);
			return new ResponseEntity<>(pOrders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/protect")
	public ResponseEntity<List<Product>> getAllProduct(){
		try {
			List<Product> pProducts = new ArrayList<Product>();
			pProductRepository.findAll().forEach(pProducts::add);
			return new ResponseEntity<>(pProducts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
