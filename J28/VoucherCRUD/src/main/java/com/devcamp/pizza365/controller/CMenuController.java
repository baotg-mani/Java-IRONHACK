package com.devcamp.pizza365.controller;

import com.devcamp.pizza365.model.CMenu;
import com.devcamp.pizza365.repository.IMenuRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CMenuController {
	@Autowired
	IMenuRepository pMenuRepository;
	
	@GetMapping("/menu")
	public ResponseEntity<List<CMenu>> getAllMenu(){
		try {
			List<CMenu> pMenuList = new ArrayList<CMenu>();
			
			pMenuRepository.findAll().forEach(pMenuList::add);
			
			return new ResponseEntity<>(pMenuList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
