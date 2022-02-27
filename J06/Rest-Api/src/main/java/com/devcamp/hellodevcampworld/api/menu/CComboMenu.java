package com.devcamp.hellodevcampworld.api.menu;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class CComboMenu {
	@CrossOrigin
	@GetMapping("/combomenu")
	public ArrayList<CMenu> getComboMenu(){
		
		ArrayList<CMenu> menu = new ArrayList<CMenu>();
		CMenu sizeS = new CMenu('S', 20, 2, 200, 2, 150000);
		CMenu sizeM = new CMenu('M', 25, 4, 300, 3, 200000);
		CMenu sizeL = new CMenu('L', 30, 8, 500, 4, 250000);
		//TODO Logic here.
		//sizeL.setDonGia(200000);
		
		menu.add(sizeS);
		menu.add(sizeM);
		menu.add(sizeL);
		
		return menu;	
	}
	@CrossOrigin
	@GetMapping("/combomenuitem")
	public CMenu getComboMenuItem(){
		
		ArrayList<CMenu> menu = new ArrayList<CMenu>();
		CMenu sizeS = new CMenu('S', 20, 2, 200, 2, 150000);
		CMenu sizeM = new CMenu('M', 25, 4, 300, 3, 200000);
		CMenu sizeL = new CMenu('L', 30, 8, 500, 4, 250000);
		//TODO Logic here.
		sizeL.setDonGia(200000);
		
		menu.add(sizeS);
		menu.add(sizeM);
		menu.add(sizeL);
		
		return sizeL;
	}	
}
