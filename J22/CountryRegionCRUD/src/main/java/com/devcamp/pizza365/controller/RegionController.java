package com.devcamp.pizza365.controller;

import com.devcamp.pizza365.model.CCountry;
import com.devcamp.pizza365.model.CRegion;
import com.devcamp.pizza365.repository.CountryRepository;
import com.devcamp.pizza365.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RegionController {
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@CrossOrigin
	@PostMapping("/region/create/{id}")
	public ResponseEntity<Object> createRegion(@PathVariable("id") Long id, @RequestBody CRegion cRegion) {
		try {
			Optional<CCountry> countryData = countryRepository.findById(id);
			if (countryData.isPresent()) {
			CRegion newRole = new CRegion();
			newRole.setRegionName(cRegion.getRegionName());
			newRole.setRegionCode(cRegion.getRegionCode());
			newRole.setCountry(cRegion.getCountry());
			
			CCountry _country = countryData.get();
			newRole.setCountry(_country);
			newRole.setCountryName(_country.getCountryName());
			
			CRegion savedRole = regionRepository.save(newRole);
			return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: "+e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Voucher: "+e.getCause().getCause().getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@CrossOrigin
	@PutMapping("/region/update/{id}")
	public ResponseEntity<Object> updateRegion(@PathVariable("id") Long id, @RequestBody CRegion cRegion) {
		Optional<CRegion> regionData = regionRepository.findById(id);
		if (regionData.isPresent()) {
			CRegion newRegion = regionData.get();
			newRegion.setRegionName(cRegion.getRegionName());
			newRegion.setRegionCode(cRegion.getRegionCode());
			CRegion savedRegion = regionRepository.save(newRegion);
			return new ResponseEntity<>(savedRegion, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@DeleteMapping("/region/delete/{id}")
	public ResponseEntity<Object> deleteRegionById(@PathVariable Long id) {
		try {
			regionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/region/details/{id}")
	public CRegion getRegionById(@PathVariable Long id) {
		if (regionRepository.findById(id).isPresent())
			return regionRepository.findById(id).get();
		else
			return null;
	}


	@CrossOrigin
	@GetMapping("/region/all")
	public List<CRegion> getAllRegion() {
		return regionRepository.findAll();
	}

	@CrossOrigin
	@GetMapping("/country/{countryId}/regions")
    public List < CRegion > getRegionsByCountry(@PathVariable(value = "countryId") Long countryId) {
        return regionRepository.findByCountryId(countryId);
    }
	
	@CrossOrigin
	@GetMapping("/country/{countryId}/regions/{id}")
    public Optional<CRegion> getRegionByRegionAndCountry(@PathVariable(value = "countryId") Long countryId,@PathVariable(value = "id") Long regionId) {
        return regionRepository.findByIdAndCountryId(regionId,countryId);
    }
	
	@CrossOrigin
	@GetMapping("/region/code/{regionCode}")
	public CRegion getRegionByRegionCode(@PathVariable String regionCode) {
		CRegion vRegion = regionRepository.findByRegionCode(regionCode);
		if(vRegion != null) {
			return vRegion;
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	@GetMapping("/region/name/{regionName}")
	public CRegion getRegionByRegionName(@PathVariable String regionName) {
		CRegion vRegion = regionRepository.findByRegionName(regionName);
		if(vRegion != null) {
			return vRegion;
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	@GetMapping("/region/count/{id}")
	public Object countRegionByCountryId(@PathVariable("id") Long id) {
		Optional<CCountry> countryData = countryRepository.findById(id);
		if(countryData.isPresent()) {
			List<CRegion> _regions = regionRepository.findByCountryId(id);
			return _regions.size();
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	@GetMapping("/region/check/{id}")
	public boolean checkRegionById(@PathVariable Long id) {
			return regionRepository.existsById(id);
	}
}

