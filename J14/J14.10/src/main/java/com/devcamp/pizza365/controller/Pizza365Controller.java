package com.devcamp.pizza365.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.pizza365.model.CCustomer;
import com.devcamp.pizza365.model.CDrink;
import com.devcamp.pizza365.model.CMenu;
import com.devcamp.pizza365.model.COrder;
import com.devcamp.pizza365.model.CVoucher;
import com.devcamp.pizza365.repository.ICustomerRepository;
import com.devcamp.pizza365.repository.IDrinkRepository;
import com.devcamp.pizza365.repository.IMenuRepository;
import com.devcamp.pizza365.repository.IOrderRepository;
import com.devcamp.pizza365.repository.IVoucherRepository;

@CrossOrigin
@RestController
public class Pizza365Controller {
	@Autowired
	IVoucherRepository pVoucherRepository;
	@Autowired
	IDrinkRepository pDrinkRepository;
	@Autowired
	IMenuRepository pMenuRepository;
	@Autowired
	ICustomerRepository pCustomerRepository;
	@Autowired
	IOrderRepository pOrderRepository;
	
	@GetMapping("/voucher")
	public ResponseEntity<List<CVoucher>> getAllVoucher(){
		try {
			List<CVoucher> pVouchers = new ArrayList<CVoucher>();
			pVoucherRepository.findAll().forEach(pVouchers::add);
			return new ResponseEntity<>(pVouchers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/drink")
	public ResponseEntity<List<CDrink>> getAllDrink(){
		try {
			List<CDrink> pDrinks = new ArrayList<CDrink>();
			pDrinkRepository.findAll().forEach(pDrinks::add);
			return new ResponseEntity<>(pDrinks, HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/menu")
	public ResponseEntity<List<CMenu>> getAllMenu(){
		try {
			List<CMenu> pMenus = new ArrayList<CMenu>();
			pMenuRepository.findAll().forEach(pMenus::add);
			return new ResponseEntity<>(pMenus, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<CCustomer>> getAllCustomer(){
		try {
			List<CCustomer> pCustomers = new ArrayList<CCustomer>();
			pCustomerRepository.findAll().forEach(pCustomers::add);
			return new ResponseEntity<>(pCustomers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order")
	public ResponseEntity<Set<COrder>> getOdersByCustomerId(@RequestParam(value = "customerId") String paramCusId){
		try {
			Long vId = Long.parseLong(paramCusId);
			Optional<CCustomer> oCustomer = pCustomerRepository.findById(vId);
			if(oCustomer.isPresent()) {
				CCustomer vCustomer = oCustomer.get();
				return new ResponseEntity<>(vCustomer.getOrders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
