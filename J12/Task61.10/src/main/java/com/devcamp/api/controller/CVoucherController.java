package com.devcamp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.api.model.CVoucher;
//http://localhost:8080/api/vouchers
//http://localhost:8080/api/updateVouchers
import com.devcamp.api.repository.IVoucherRepository;
@CrossOrigin
@RestController
public class CVoucherController {	
	@Autowired
	IVoucherRepository pVoucherRepository;

	@GetMapping("/vouchers")
	public ResponseEntity<List<CVoucher>> getAllVouchers() {
		try {
			List<CVoucher> listVoucher = new ArrayList<CVoucher>();
			
			pVoucherRepository.findAll()
			.forEach(listVoucher::add);
			
			return new ResponseEntity<>(listVoucher, HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	@GetMapping("/vouchers1")
	public List<CVoucher> getAllVouchers1() {
		try {
		List<CVoucher> listVoucher = new ArrayList<CVoucher>();
		pVoucherRepository.findAll().forEach(listVoucher::add);
		return listVoucher;
		} catch (Exception e) {
			return null;
		}
	}
}
