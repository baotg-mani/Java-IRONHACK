package com.devcamp.pizza365.controller;

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

import com.devcamp.pizza365.repository.ICarModelRepository;
import com.devcamp.pizza365.repository.ICarTypeRepository;
import com.devcamp.pizza365.model.CarModel;
import com.devcamp.pizza365.model.CarType;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CarController {
	@Autowired
    ICarModelRepository pCarModelRepository;
	
	@Autowired
    ICarTypeRepository pCarTypeRepository;
	
	@GetMapping("/cars")
	public ResponseEntity<List<CarModel>> getAllCarModel() {
        try {
            List<CarModel> pCarModel = new ArrayList<CarModel>();

            pCarModelRepository.findAll().forEach(pCarModel::add);

            return new ResponseEntity<>(pCarModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/carstypes")
	public ResponseEntity<Set<CarType>> getCarTypesByCarCode(@RequestParam(value = "carCode") String carCode) {
        try {
            CarModel vCarModel = pCarModelRepository.findByCarCode(carCode);
            
            if(vCarModel != null) {
            	return new ResponseEntity<>(vCarModel.getCartypes(), HttpStatus.OK);
            } else {
            	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
