package com.devcamp.pizza365.controller;

import com.devcamp.pizza365.model.CDrink;
import com.devcamp.pizza365.repository.IDrinkRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CDrinkController {
	@Autowired 
	IDrinkRepository pDrinkRepository;
	
	@GetMapping("/drinks")
	public ResponseEntity<List<CDrink>> getDrinkList(){
		try {
			List<CDrink> pDrinkLists = new ArrayList<CDrink>();
			
			pDrinkRepository.findAll().forEach(pDrinkLists::add);
			
			return new ResponseEntity<>(pDrinkLists, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
