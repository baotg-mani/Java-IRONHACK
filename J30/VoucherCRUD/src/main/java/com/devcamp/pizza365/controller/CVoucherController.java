package com.devcamp.pizza365.controller;

import com.devcamp.pizza365.model.CCOrder;
import com.devcamp.pizza365.repository.IVoucherRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CVoucherController {
	@Autowired
	IVoucherRepository pVoucherRepository;

	@GetMapping("/vouchers")
	public ResponseEntity<List<CCOrder>> getAllVouchers() {
		try {
			List<CCOrder> pVouchers = new ArrayList<CCOrder>();

			pVoucherRepository.findAll().forEach(pVouchers::add);

			return new ResponseEntity<>(pVouchers, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/vouchers/{id}")
	public ResponseEntity<CCOrder> getCVoucherById(@PathVariable("id") long id) {
		Optional<CCOrder> voucherData = pVoucherRepository.findById(id);
		if (voucherData.isPresent()) {
			return new ResponseEntity<>(voucherData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/vouchers")
	public ResponseEntity<Object> createCVoucher(@Valid @RequestBody CCOrder pVouchers) {//Sửa để cho phép validate
		try {
			pVouchers.setNgayTao(new Date());
			pVouchers.setNgayCapNhat(null);
			CCOrder _vouchers = pVoucherRepository.save(pVouchers);
			return new ResponseEntity<>(_vouchers, HttpStatus.CREATED);
		}catch (Exception  e) {
			System.out.println("+++++++++++++++++++++::::: "+e.getCause().getCause().getMessage());
			//Hiện thông báo lỗi tra back-end
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			//return ResponseEntity.unprocessableEntity().body("Failed to Create specified Voucher: "+e.getCause().getCause().getMessage());
		}
	}

	@PutMapping("/vouchers/{id}")
	public ResponseEntity<Object> updateCVoucherById(@PathVariable("id") long id, @RequestBody CCOrder pVouchers) {
		Optional<CCOrder> voucherData = pVoucherRepository.findById(id);
		if (voucherData.isPresent()) {
			CCOrder voucher= voucherData.get();
			voucher.setMaVoucher(pVouchers.getMaVoucher());
			voucher.setPhanTramGiamGia(pVouchers.getPhanTramGiamGia());
			voucher.setNgayCapNhat(new Date());
			try {
				return new ResponseEntity<>(pVoucherRepository.save(voucher), HttpStatus.OK);	
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().body("Failed to Update specified Voucher:"+e.getCause().getCause().getMessage());
			}
			
		} else {
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return ResponseEntity.badRequest().body("Failed to get specified Voucher: "+id + "  for update.");
		}
	}

	@DeleteMapping("/vouchers/{id}")
	public ResponseEntity<CCOrder> deleteCVoucherById(@PathVariable("id") long id) {
		try {
			pVoucherRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/vouchers")
	public ResponseEntity<CCOrder> deleteAllCVoucher() {
		try {
			pVoucherRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
