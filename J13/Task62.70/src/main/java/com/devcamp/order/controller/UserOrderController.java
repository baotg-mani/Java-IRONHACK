package com.devcamp.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.order.model.CCustomer;
import com.devcamp.order.model.COrder;
import com.devcamp.order.repository.IUserRepository;
import com.devcamp.order.repository.IOrderRepository;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserOrderController {
	@Autowired
	IUserRepository pUserRepository;
	@Autowired
	IOrderRepository pOrderRepository;

	@GetMapping("/devcamp-users")
	public ResponseEntity<List<CCustomer>> getAllCustomer() {
		try {
			List<CCustomer> pCustomers = new ArrayList<CCustomer>();
			pUserRepository.findAll().forEach(pCustomers::add);
			return new ResponseEntity<>(pCustomers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/devcamp-orders")
	public ResponseEntity<Set<COrder>> getOrdersByCustomerId(@RequestParam(value = "userId") String customerId) {
		try {
			Long id = Long.parseLong(customerId);
			Optional<CCustomer> oCustomer = pUserRepository.findById(id);
			if (oCustomer.isPresent()) {
				CCustomer vCustomer = oCustomer.get();
				return new ResponseEntity<>(vCustomer.getOrders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
