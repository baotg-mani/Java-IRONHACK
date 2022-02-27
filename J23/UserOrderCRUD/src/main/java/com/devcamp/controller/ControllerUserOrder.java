package com.devcamp.controller;

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

import com.devcamp.model.COrder;
import com.devcamp.model.CUser;
import com.devcamp.repository.IOrderRepository;
import com.devcamp.repository.IUserRepository;

@RestController
public class ControllerUserOrder {
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IOrderRepository orderRepository;
	
	////////////////////////USER///////////////////////////
	
	@CrossOrigin
	@PostMapping("/user/create")
	public ResponseEntity<Object> createUser(@RequestBody CUser cUser) {
		try {
			CUser savedRole = userRepository.save(cUser);
			return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@PutMapping("/user/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody CUser cUser) {
		try {
			Optional<CUser> userData = userRepository.findById(id);
			if (userData.isPresent()) {
				CUser newRole = userData.get();
				newRole.setFullname(cUser.getFullname());
				newRole.setEmail(cUser.getEmail());
				newRole.setAddress(cUser.getAddress());
				newRole.setPhone(cUser.getPhone());
				newRole.setOrders(cUser.getOrders());
				CUser savedCountry = userRepository.save(newRole);
				return new ResponseEntity<>(savedCountry, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/user/details/{id}")
	public CUser getUserById(@PathVariable Long id) {
		if (userRepository.findById(id).isPresent())
			return userRepository.findById(id).get();
		else
			return null;
	}

	@CrossOrigin
	@GetMapping("/user/all")
	public ResponseEntity<Object> getAllUser() {
		try {
			return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/user/count")
	public ResponseEntity<Object> countUser() {
		try {
			if (userRepository.count() != 0) {
				return new ResponseEntity<>(userRepository.count(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/user/check/{id}")
	public ResponseEntity<Object> checkUserById(@PathVariable("id") Long id) {
		try {
			if (userRepository.findById(id).isPresent()) {
				return new ResponseEntity<>(userRepository.existsById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/////////////////////////ORDER//////////////////////////
	
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
