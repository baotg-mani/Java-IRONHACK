package com.devcamp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.model.CCountry;
import com.devcamp.model.CRegion;

@RestController
public class CTest {

	@CrossOrigin
	@GetMapping("/countries")
	public ArrayList<CRegion> getRegions(@RequestParam(value = "countrycode") String paramCountryCode) {
		ArrayList<CCountry> countries = new ArrayList<>();
		CRegion hanoi = new CRegion("844", "Ha Noi");
		CRegion hochiminh = new CRegion("848", "Ho Chi Minh");
		CRegion california = new CRegion("213", "California");
		CRegion newyork = new CRegion("518", "New York");
		CRegion beijing = new CRegion("8610", "Beijing");
		CRegion shanghai = new CRegion("8621", "Shanghai");

		ArrayList<CRegion> regionsOfVN = new ArrayList<>();
		ArrayList<CRegion> regionsOfUS = new ArrayList<>();
		ArrayList<CRegion> regionsOfCN = new ArrayList<>();

		regionsOfVN.add(hanoi);
		regionsOfVN.add(hochiminh);
		regionsOfUS.add(california);
		regionsOfUS.add(newyork);
		regionsOfCN.add(shanghai);
		regionsOfCN.add(beijing);

		CCountry vn = new CCountry("VN", "Viet Nam", regionsOfVN);
		CCountry us = new CCountry("US", "America", regionsOfUS);
		CCountry cn = new CCountry("CN", "China", regionsOfCN);

		countries.add(vn);
		countries.add(us);
		countries.add(cn);

		ArrayList<CRegion> regions = new ArrayList<>();
		if (paramCountryCode.equals("VN")) {
			regions = regionsOfVN;
		} else if (paramCountryCode.equals("US")) {
			regions = regionsOfUS;
		} else if (paramCountryCode.equals("CN")) {
			regions = regionsOfCN;
		} else {
			return regions;
		}
		return regions;
	}
}
