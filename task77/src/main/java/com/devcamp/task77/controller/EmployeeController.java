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

import com.devcamp.task77.model.Employee;
import com.devcamp.task77.repository.EmployeeRepo;

@CrossOrigin
@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepo employeeRepo;
	
	@GetMapping("/employees")
	public ResponseEntity<Object> getAllEmployee() {
		try {
			return new ResponseEntity<>(employeeRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Integer employeeId) {
		try {
			Optional<Employee> employeeFound = employeeRepo.findById(employeeId);
			if (employeeFound.isPresent()) {
				return new ResponseEntity<>(employeeFound.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee newEmployee) {
		try {
			return new ResponseEntity<>(employeeRepo.save(newEmployee), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Object> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee newEmployee) {
		try {
			Optional<Employee> employeeFound = employeeRepo.findById(employeeId);
			if (employeeFound.isPresent()) {
				Employee updateEmployee = employeeFound.get();
				updateEmployee.setEmail(newEmployee.getEmail());
				updateEmployee.setExtension(newEmployee.getExtension());
				updateEmployee.setFirstName(newEmployee.getFirstName());
				updateEmployee.setJobTitle(newEmployee.getJobTitle());
				updateEmployee.setLastName(newEmployee.getLastName());
				updateEmployee.setOfficeCode(newEmployee.getOfficeCode());
				updateEmployee.setReportTo(newEmployee.getReportTo());
				return new ResponseEntity<>(employeeRepo.save(updateEmployee), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable Integer employeeId) {
		try {
			Optional<Employee> employeeFound = employeeRepo.findById(employeeId);
			if (employeeFound.isPresent()) {
				employeeRepo.deleteById(employeeId);
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/employees")
	public ResponseEntity<Object> deleteAllEmployee() {
		try {
			employeeRepo.deleteAll();
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
