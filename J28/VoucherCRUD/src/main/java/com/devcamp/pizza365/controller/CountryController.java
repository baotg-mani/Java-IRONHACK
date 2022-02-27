package com.devcamp.pizza365.controller;

import com.devcamp.pizza365.model.CCountry;
import com.devcamp.pizza365.model.CRegion;
import com.devcamp.pizza365.repository.CountryRepository;
import com.devcamp.pizza365.repository.RegionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CountryController {
	
	@Autowired
	private CountryRepository countryRepository;

						////// COUNTRY //////
	@PostMapping("/country/create")
	public ResponseEntity<Object> createCountry(@RequestBody CCountry cCountry) {
		try {
			CCountry newRole = new CCountry();
			newRole.setCountryName(cCountry.getCountryName());
			newRole.setCountryCode(cCountry.getCountryCode());
			newRole.setRegions(cCountry.getRegions());
			CCountry savedRole = countryRepository.save(newRole);
			return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/country/update/{id}")
	public ResponseEntity<Object> updateCountry(@PathVariable("id") Long id, @RequestBody CCountry cCountry) {
		Optional<CCountry> countryData = countryRepository.findById(id);
		if (countryData.isPresent()) {
			CCountry newCountry = countryData.get();
			/*
			 * newCountry.setCountryName(cCountry.getCountryName());
			 * newCountry.setCountryCode(cCountry.getCountryCode());
			 * newCountry.getRegions().clear();
			 * newCountry.getRegions().addAll(cCountry.getRegions());
			 * //newCountry.setRegions(cCountry.getRegions());
			 */
			cCountry.setId(newCountry.getId());
			CCountry savedCountry = countryRepository.save(cCountry);
			return new ResponseEntity<>(savedCountry, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/country/delete/{id}")
	public ResponseEntity<Object> deleteCountryById(@PathVariable Long id) {
		try {
			countryRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/country/details/{id}")
	public CCountry getCountryById(@PathVariable Long id) {
		if (countryRepository.findById(id).isPresent())
			return countryRepository.findById(id).get();
		else
			return null;
	}

	@GetMapping("/country/all")
	public List<CCountry> getAllCountry() {
		return countryRepository.findAll();
	}
	
	// get Country theo cách query Native SQL
	@GetMapping("/country/query1/{paraName1}")
	public List<CCountry> getAllCountryByName1(@PathVariable String paraName1) {
		return countryRepository.findCountryByCountryName1(paraName1);
	}
	
	@GetMapping("/country/query2/{paraName2}")
	public List<CCountry> getAllCountryByName2(@PathVariable String paraName2) {
		return countryRepository.findCountryByCountryName2(paraName2);
	}
	
	@GetMapping("/country/query3/{paraName3}")
	public List<CCountry> getAllCountryByName3(@PathVariable String paraName3) {
		return countryRepository.findCountryByCountryName3(paraName3);
	}

	 								//////// REGION ////////
	@Autowired
	private RegionRepository regionRepository;

	@GetMapping("/regions/{countryCode}")
	public ResponseEntity<List<CRegion>> getRegionsByCountryCode(@PathVariable("countryCode") String countryCode) {
		try {
			CCountry vCountry = countryRepository.findByCountryCode(countryCode);

			if (vCountry != null) {
				return new ResponseEntity<>(vCountry.getRegions(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/country/{countryId}/regions")
	public List<CRegion> getRegionsByCountry(@PathVariable(value = "countryId") Long countryId) {
		return regionRepository.findByCountryId(countryId);
	}

	@GetMapping("/country/{countryId}/regions/{id}")
	public Optional<CRegion> getRegionByRegionAndCountry(@PathVariable(value = "countryId") Long countryId,
			@PathVariable(value = "id") Long regionId) {
		return regionRepository.findByIdAndCountryId(regionId, countryId);
	}

	@GetMapping("/region/details/{id}")
	public CRegion getRegionById(@PathVariable Long id) {
		if (regionRepository.findById(id).isPresent())
			return regionRepository.findById(id).get();
		else
			return null;
	}

	@GetMapping("/region/all")
	public List<CRegion> getAllRegions() {
		return regionRepository.findAll();
	}

	@GetMapping("/country/{countryCode}/{regionCode}")
	public Optional<CRegion> getRegionByCountryCodeAndRegionCode(
			@PathVariable(value = "countryCode") String countryCode,
			@PathVariable(value = "regionCode") String regionCode) {
		Optional<CRegion> optional = regionRepository.findByRegionCodeAndCountryCountryCode(regionCode,countryCode);
		if (optional.isPresent()) {
			return optional;
		} else {
			return null;
		}
	}

	@GetMapping("/region/code/{regionCode}")
	public CRegion getRegionByCode(@PathVariable(value = "regionCode") String regionCode) {
		return regionRepository.findByRegionCode(regionCode);
	}
}
