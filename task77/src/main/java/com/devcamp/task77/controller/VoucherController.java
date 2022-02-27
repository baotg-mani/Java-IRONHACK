package com.devcamp.task77.controller;


import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.task77.repository.IVoucherRepository;

@CrossOrigin
@RestController
public class VoucherController {
	@Autowired
	IVoucherRepository voucherRepository;
	
	@PutMapping("/vouchers/{voucherPercent}/{voucherCode}")
	public ResponseEntity<Object> updateVoucherByVoucherCode(@PathVariable String voucherPercent, 
			@PathVariable String voucherCode) {
		try {
			return new ResponseEntity<>(voucherRepository.updatePhanTram(voucherCode, voucherPercent) , HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
