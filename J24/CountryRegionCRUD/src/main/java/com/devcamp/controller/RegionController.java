package com.devcamp.controller;

import com.devcamp.model.CCountry;
import com.devcamp.model.CRegion;
import com.devcamp.repository.CountryRepository;
import com.devcamp.repository.RegionRepository;
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
	@PostMapping("/region/create/{coutryId}")
	public ResponseEntity<Object> createRegionByCountryId(@PathVariable("coutryId") Long coutryId, @RequestBody CRegion cRegion) {
		try {
			Optional<CCountry> countryData = countryRepository.findById(coutryId);
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
		try {
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
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@DeleteMapping("/region/delete/{id}")
	public ResponseEntity<Object> deleteRegionById(@PathVariable Long id) {
		try {
			regionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/region/details/{id}")
//	public CRegion getRegionById(@PathVariable Long id) {
//		if (regionRepository.findById(id).isPresent())
//			return regionRepository.findById(id).get();
//		else
//			return null;
//	}
	public ResponseEntity<Object> getRegionById(@PathVariable Long id) {
		try {
			if (regionRepository.findById(id).isPresent()) {
				return new ResponseEntity<>(regionRepository.findById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@CrossOrigin
	@GetMapping("/region/all")
//	public List<CRegion> getAllRegion() {
//		return regionRepository.findAll();
//	}
	public ResponseEntity<List<CRegion>> getAllRegion() {
		try {
			List<CRegion> result = regionRepository.findAll();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/country/{countryId}/regions")
//    public List<CRegion> getRegionsByCountryId(@PathVariable(value = "countryId") Long countryId) {
//        return regionRepository.findByCountryId(countryId);
//    }
	public ResponseEntity<Object> getRegionsByCountryId(@PathVariable Long countryId) {
		try {
			return new ResponseEntity<>(regionRepository.findByCountryId(countryId), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
	@GetMapping("/region/count/{countryId}")
//	public Object countRegionByCountryId(@PathVariable("countryId") Long countryId) {
//		Optional<CCountry> countryData = countryRepository.findById(countryId);
//		if(countryData.isPresent()) {
//			List<CRegion> _regions = regionRepository.findByCountryId(countryId);
//			return _regions.size();
//		} else {
//			return null;
//		}
//	}
	public ResponseEntity<Object> countRegionsByCountryId(@PathVariable Long countryId) {
		try {
			if (countryRepository.findById(countryId).isPresent()) {
				return new ResponseEntity<>(regionRepository.countByCountryId(countryId), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			System.out.println(e);;
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/region/check/{id}")
//	public boolean checkRegionById(@PathVariable Long id) {
//			return regionRepository.existsById(id);
//	}
	public ResponseEntity<Object> checkRegionById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(regionRepository.existsById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

