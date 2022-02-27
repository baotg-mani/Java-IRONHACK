package com.devcamp.hellodevcampworld.api.region;

import java.util.ArrayList;
import java.util.Iterator;

public class CCountryBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<CCountry> countries = new ArrayList<CCountry>();
		CCountry cCountry = new CCountry("VietNam", "Vn");
		cCountry.setCountryName("Việt Nam");
		cCountry.setCountryCode("VN");
		countries.add(cCountry);
		 for (Iterator iterator = countries.iterator(); iterator.hasNext();) {
			CCountry cCountry1 = (CCountry) iterator.next();
			if("VN".equals(cCountry1.getCountryCode())) {
				
			}
			
		}
		
		ArrayList<CRegion> regions = new ArrayList<CRegion>();
		CRegion cRegion = new CRegion("Ha Nội", "HN");
		CRegion cRegion1 = new CRegion("Hồ Chí Minh", "HCM");
		regions.add(cRegion);
		regions.add(cRegion1);
		cCountry.setRegions(regions);
		
		System.out.println(countries);
	}

}
