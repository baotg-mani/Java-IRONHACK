package com.devcamp.shop_pizza365.controller;

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

import com.devcamp.shop_pizza365.model.Employee;
import com.devcamp.shop_pizza365.repository.EmployeeRepository;

@RestController
@CrossOrigin
public class EmployeeController {
	@Autowired
	private EmployeeRepository pEmployeeRepository;
	
	@GetMapping("/employee/all")
	public ResponseEntity<Object> getAllEmployee() {
		try {
			return new ResponseEntity<>(pEmployeeRepository.findAllEmployee(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable int id){
		try {
			return new ResponseEntity<>(pEmployeeRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/employee") 
	public ResponseEntity<Object> createEmployee(@RequestBody @Valid Employee cEmployee){
		try {
			pEmployeeRepository.save(cEmployee);
			return new ResponseEntity<>(pEmployeeRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getCause().getCause().getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable Integer id, @RequestBody Employee cEmployee) {
		Optional<Employee> employeeData = pEmployeeRepository.findById(id);
		if (employeeData.isPresent()) {
			try {
				Employee newUpdate = employeeData.get();
				newUpdate.setEmail(cEmployee.getEmail());
				newUpdate.setExtension(cEmployee.getExtension());
				newUpdate.setFirstName(cEmployee.getFirstName());
				newUpdate.setLastName(cEmployee.getLastName());
				newUpdate.setJobTitle(cEmployee.getJobTitle());
				newUpdate.setOfficeCode(cEmployee.getOfficeCode());
				newUpdate.setReportTo(cEmployee.getReportTo());
				return new ResponseEntity<>(pEmployeeRepository.save(newUpdate), HttpStatus.OK);
			} catch(Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Employee not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable Integer id) {
		if (pEmployeeRepository.findById(id).isPresent()) {
			try {
				pEmployeeRepository.deleteById(id);
				return new ResponseEntity<>(pEmployeeRepository.findAll(), HttpStatus.OK);
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
