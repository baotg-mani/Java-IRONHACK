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

import com.devcamp.UserOrder.model.CUser;
import com.devcamp.UserOrder.repository.IUserRepository;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@RestController
public class CUserController {
	@Autowired
	private IUserRepository userRepository;
	
	@CrossOrigin
	@PostMapping(value =  "/user/create", consumes = {"*/*"})
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
	public List<CUser> getAllUser() {
		return userRepository.findAll();
	}
}
