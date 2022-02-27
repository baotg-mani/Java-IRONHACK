package com.devcamp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.api.model.CDrink;
import com.devcamp.api.repository.IDrinkRepository;

@CrossOrigin
@RestController
public class CDrinkController {
	@Autowired
	IDrinkRepository pDrinkRepository;

	@GetMapping("/drinks")
	public ResponseEntity<List<CDrink>> getAllDrinks() {
		try {
			List<CDrink> listDrink = new ArrayList<CDrink>();

			pDrinkRepository.findAll().forEach(listDrink::add);

			return new ResponseEntity<>(listDrink, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/drinks1")
	public List<CDrink> getAllVouchers1() {
		try {
			List<CDrink> listDrink = new ArrayList<CDrink>();
			pDrinkRepository.findAll().forEach(listDrink::add);
			return listDrink;
		} catch (Exception e) {
			return null;
		}
	}
}
