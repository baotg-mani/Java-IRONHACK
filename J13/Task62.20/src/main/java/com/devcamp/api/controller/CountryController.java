package com.devcamp.api.controller;

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

import com.devcamp.api.model.CCountry;
import com.devcamp.api.model.CRegion;
import com.devcamp.api.repository.ICountryRepository;
import com.devcamp.api.repository.IRegionRepository;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CountryController {
	@Autowired
    ICountryRepository pCountryRepository;
	
	@Autowired
    IRegionRepository pRegionRepository;
	
	@GetMapping("/devcamp-countries")
	public ResponseEntity<List<CCountry>> getAllCountries() {
        try {
            List<CCountry> pCountries = new ArrayList<CCountry>();

            pCountryRepository.findAll().forEach(pCountries::add);

            return new ResponseEntity<>(pCountries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@GetMapping("/devcamp-regions")
	public ResponseEntity<Set<CRegion>> getRegionsByCountryCode(@RequestParam(value = "countryCode") String countryCode) {
        try {
            CCountry vCountry = pCountryRepository.findByCountryCode(countryCode);
            
            if(vCountry != null) {
            	return new ResponseEntity<>(vCountry.getRegions(), HttpStatus.OK);
            } else {
            	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
