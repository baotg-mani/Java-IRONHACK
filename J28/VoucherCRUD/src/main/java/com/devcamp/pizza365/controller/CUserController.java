package com.devcamp.pizza365.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.devcamp.pizza365.model.CUser;
import com.devcamp.pizza365.model.CVoucher;
import com.devcamp.pizza365.repository.IUserRepository;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CUserController {
	
	@Autowired
	private IUserRepository pUserRepository;
	
	@GetMapping("/user/all")
	public ResponseEntity<Object> getAllUSer() {
		try {
			 return new ResponseEntity<>(pUserRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/details/{id}")
	public Object getUserById(@PathVariable Long id) {
		if (pUserRepository.findById(id).isPresent())
			return pUserRepository.findById(id).get();
		else
			return "Can't find special user: " + id;
	}
	
	@PostMapping("/user/create")
	public ResponseEntity<Object> createUser(@RequestBody CUser cUser) {
		try {
			CUser newUser = new CUser();
			newUser.setFirstname(cUser.getFirstname());
			newUser.setLastname(cUser.getLastname());
			newUser.setCountry(cUser.getCountry());
			newUser.setCustomerType(cUser.getCustomerType());
			newUser.setRegisterStatus(cUser.getRegisterStatus());
			newUser.setSubject(cUser.getSubject());
			newUser.setUsername(cUser.getUsername());
			newUser.setOrders(cUser.getOrders());
			
			CUser savedUser = pUserRepository.save(cUser);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			//return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified User: "+e.getCause().getCause().getMessage());
		}
	}
	
	@PutMapping("/user/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody CUser cUser) {
		Optional<CUser> userData = pUserRepository.findById(id);
		if (userData.isPresent()) {
			CUser newUser = userData.get();
			
			newUser.setFirstname(cUser.getFirstname());
			newUser.setLastname(cUser.getLastname());
			newUser.setCountry(cUser.getCountry());
			newUser.setCustomerType(cUser.getCustomerType());
			newUser.setRegisterStatus(cUser.getRegisterStatus());
			newUser.setSubject(cUser.getSubject());
			newUser.setUsername(cUser.getUsername());
			newUser.setOrders(cUser.getOrders());
			
			CUser savedUser = pUserRepository.save(newUser);
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} else {
			return ResponseEntity.unprocessableEntity().body("Failed to Update specified User: " + id);
		}
	}
	
	
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
		try {
			pUserRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("=================="+e.getCause().getCause().getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
