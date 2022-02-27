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

import com.devcamp.shop_pizza365.model.Office;
import com.devcamp.shop_pizza365.repository.OfficeRepository;

@RestController
@CrossOrigin
public class OfficeController  {
	@Autowired
	private OfficeRepository pOfficeRepository;
	
	@GetMapping("/office/all")
	public ResponseEntity<Object> getAllOffice() {
		try {
			return new ResponseEntity<>(pOfficeRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/office/{id}")
	public ResponseEntity<Object> getOfficeById(@PathVariable int id){
		try {
			return new ResponseEntity<>(pOfficeRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/office") 
	public ResponseEntity<Object> createOffice(@RequestBody Office cOffice){
		try {
			pOfficeRepository.save(cOffice);
			return new ResponseEntity<>(pOfficeRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getCause().getCause().getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/office/{id}")
	public ResponseEntity<Object> updateOffice(@PathVariable Integer id, @RequestBody Office cOffice) {
		Optional<Office> officeData = pOfficeRepository.findById(id);
		if (officeData.isPresent()) {
			try {
				Office newUpdate = officeData.get();
				newUpdate.setAddressLine(cOffice.getAddressLine());
				newUpdate.setCity(cOffice.getCity());
				newUpdate.setCountry(cOffice.getCountry());
				newUpdate.setPhone(cOffice.getPhone());
				newUpdate.setState(cOffice.getState());
				newUpdate.setTerritory(cOffice.getTerritory());
				return new ResponseEntity<>(pOfficeRepository.save(newUpdate), HttpStatus.OK);
			} catch(Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Office's not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/office/{id}")
	public ResponseEntity<Object> deleteOfficeById(@PathVariable Integer id) {
		if (pOfficeRepository.findById(id).isPresent()) {
			try {
				pOfficeRepository.deleteById(id);
				return new ResponseEntity<>(pOfficeRepository.findAll(), HttpStatus.OK);
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
