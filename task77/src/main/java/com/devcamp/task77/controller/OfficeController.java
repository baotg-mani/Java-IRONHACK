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

import com.devcamp.task77.model.Office;
import com.devcamp.task77.repository.OfficeRepo;

@CrossOrigin
@RestController
public class OfficeController {
	@Autowired
	OfficeRepo officeRepo;
	
	@GetMapping("/offices")
	public ResponseEntity<Object> getAllOffice() {
		try {
			return new ResponseEntity<>(officeRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/offices/{officeId}")
	public ResponseEntity<Object> getOfficeById(@PathVariable Integer officeId) {
		try {
			Optional<Office> officeFound = officeRepo.findById(officeId);
			if (officeFound.isPresent()) {
				return new ResponseEntity<>(officeFound.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/offices")
	public ResponseEntity<Object> createOffice(@Valid @RequestBody Office newOffice) {
		try {
			return new ResponseEntity<>(officeRepo.save(newOffice), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/offices/{officeId}")
	public ResponseEntity<Object> updateOffice(@PathVariable Integer officeId, @RequestBody Office newOffice) {
		try {
			Optional<Office> officeFound = officeRepo.findById(officeId);
			if (officeFound.isPresent()) {
				Office updateOffice = officeFound.get();
				updateOffice.setAddressLine(newOffice.getAddressLine());
				updateOffice.setCity(newOffice.getCity());
				updateOffice.setCountry(newOffice.getCountry());
				updateOffice.setPhone(newOffice.getPhone());
				updateOffice.setState(newOffice.getState());
				updateOffice.setTerritory(newOffice.getTerritory());
				return new ResponseEntity<>(officeRepo.save(updateOffice), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/offices/{officeId}")
	public ResponseEntity<Object> deleteOfficeById(@PathVariable Integer officeId) {
		try {
			Optional<Office> officeFound = officeRepo.findById(officeId);
			if (officeFound.isPresent()) {
				officeRepo.deleteById(officeId);
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/offices")
	public ResponseEntity<Object> deleteAllOffice() {
		try {
			officeRepo.deleteAll();
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
