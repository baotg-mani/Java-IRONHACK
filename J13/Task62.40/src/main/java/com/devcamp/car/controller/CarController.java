package com.devcamp.car.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.car.model.CCar;
import com.devcamp.car.model.CCarType;
import com.devcamp.car.repository.ICarRepository;
import com.devcamp.car.repository.ICarTypeRepository;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CarController {
	@Autowired
	ICarRepository pCarRepository;
	@Autowired
	ICarTypeRepository pCarTypeRepository;

	@GetMapping("/devcamp-cars")
	public ResponseEntity<List<CCar>> getAllCar() {
		try {
			List<CCar> pCars = new ArrayList<CCar>();
			pCarRepository.findAll().forEach(pCars::add);
			return new ResponseEntity<>(pCars, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/devcamp-cartypes")
	public ResponseEntity<Set<CCarType>> getCarTypesByCarCode(@RequestParam(value = "carCode") String paramCarCode) {
		try {
			CCar vCar = pCarRepository.findByCarCode(paramCarCode);
			if (vCar != null) {
				return new ResponseEntity<>(vCar.getTypes(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
