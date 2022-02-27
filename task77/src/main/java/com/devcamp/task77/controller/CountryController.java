package com.devcamp.task77.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.task77.model.CCountry;
import com.devcamp.task77.repository.ICountryRepository;

@CrossOrigin
@RestController
public class CountryController {
	@Autowired
	ICountryRepository countryRepository;
	
	@GetMapping("/countries/{countryName}")
	public ResponseEntity<Object> getCountryByName(@PathVariable String countryName) {
		try {
			List<CCountry> countryFound = countryRepository.findCountryByCountryNameDesc(countryName, countryName, countryName);
			return new ResponseEntity<>(countryFound, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
