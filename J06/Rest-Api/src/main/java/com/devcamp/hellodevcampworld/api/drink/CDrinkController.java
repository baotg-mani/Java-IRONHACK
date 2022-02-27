package com.devcamp.hellodevcampworld.api.drink;

import java.util.Date;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CDrinkController {
	@CrossOrigin
	@GetMapping("/pizza365-drink")
	public ArrayList<CDrink> getDrinks(){
		ArrayList<CDrink> drinks = new ArrayList<>();
		
		long ngaytao = 1615177934000l;
		long ngaycapnhat = 1615177934000l;
		Date dateTao = new Date(ngaytao);
		Date dateCapNhat = new Date(ngaycapnhat);
		
		CDrink tratac = new CDrink("TRATAC", "Trà tắc", 10000, null, dateTao, dateCapNhat);
		CDrink coca = new CDrink("COCA", "Cocacola", 15000, null, dateTao, dateCapNhat);
		CDrink pepsi = new CDrink("PEPSI", "Pepsi", 15000, null, dateTao, dateCapNhat);
		CDrink lavie = new CDrink("LAVIE", "Lavie", 5000, null, dateTao, dateCapNhat);
		CDrink trasua = new CDrink("TRASUA", "Trà sữa trân châu", 40000, null, dateTao, dateCapNhat);
		CDrink fanta = new CDrink("FANTA", "Fanta", 15000, null, dateTao, dateCapNhat);
		
		drinks.add(tratac);
		drinks.add(coca);
		drinks.add(pepsi);
		drinks.add(lavie);
		drinks.add(trasua);
		drinks.add(fanta);
		
		return drinks; 
	}
	
}
