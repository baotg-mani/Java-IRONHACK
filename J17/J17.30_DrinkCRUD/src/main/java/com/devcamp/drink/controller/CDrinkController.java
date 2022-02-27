package com.devcamp.drink.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.drink.model.CDrink;
import com.devcamp.drink.repository.IDrinkRepository;

@RestController
@RequestMapping("/")
public class CDrinkController {
	@Autowired
	IDrinkRepository pDrinkRepository;
	
	@CrossOrigin
	@GetMapping("/drinks")
	public ResponseEntity<List<CDrink>> getAllDrinks() {
		try {
			List<CDrink> pDrinks = new ArrayList<CDrink>();

			pDrinkRepository.findAll().forEach(pDrinks::add);

			return new ResponseEntity<>(pDrinks, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/drinks/{id}")
	public ResponseEntity<CDrink> getCDrinkById(@PathVariable("id") long id) {
		Optional<CDrink> drinkData = pDrinkRepository.findById(id);
		if (drinkData.isPresent()) {
			return new ResponseEntity<>(drinkData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@PostMapping("/drinks")
	public ResponseEntity<Object> createCDrink(@Valid @RequestBody CDrink pDrinks) {
		try {
			pDrinks.setNgayTao(new Date());
			pDrinks.setNgayCapNhat(null);
			CDrink _drinks = pDrinkRepository.save(pDrinks);
			return new ResponseEntity<>(_drinks, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: "+e.getCause().getCause().getMessage());
			//Hiện thông báo lỗi tra back-end
			//return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Drink: "+e.getCause().getCause().getMessage());
		}
	}
	
	@CrossOrigin
	@PutMapping("/drinks/{id}")
	public ResponseEntity<Object> updateCDrinkById(@PathVariable("id") long id, @RequestBody CDrink pDrinks) {
		Optional<CDrink> drinkData = pDrinkRepository.findById(id);
		if (drinkData.isPresent()) {
			CDrink drink= drinkData.get();
			drink.setMaNuocUong(pDrinks.getMaNuocUong());
			drink.setTenNuocUong(pDrinks.getTenNuocUong());
			drink.setNgayCapNhat(new Date());
			try {
				return new ResponseEntity<>(pDrinkRepository.save(drink), HttpStatus.OK);	
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().body("Failed to Update specified Drink:"+e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified Drink: "+id + "  for update.");
		}
	}
	
	@CrossOrigin
	@DeleteMapping("/drinks/{id}")
	public ResponseEntity<CDrink> deleteCDrinkById(@PathVariable("id") long id) {
		try {
			pDrinkRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@DeleteMapping("/drinks")
	public ResponseEntity<CDrink> deleteAllCDrink() {
		try {
			pDrinkRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
