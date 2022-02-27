package com.devcamp.hellodevcampworld.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainCountry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Region> cRegions = MainCountry.getRegions(MainCountry.initCountries(), "VN");
		System.out.println(cRegions);
		ObjectMapper objectMapper = new ObjectMapper();
		String prettyRegions = "";
		String jsonString = "";
		try {
			prettyRegions = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cRegions);
			jsonString = objectMapper.writeValueAsString(cRegions);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prettyRegions);
		System.out.println(jsonString);
	}

	public static List<Country> initCountries() {
		ObjectMapper mapper = new ObjectMapper();

		List<Country> countries = new ArrayList<Country>();

		try {
			File file = ResourceUtils.getFile("classpath:countrydata.json");

			// Java objects to JSON file
			//countries = mapper.readValue(new File("d:\\test\\data.json"), new TypeReference<ArrayList<Country>>() {});
			countries = mapper.readValue(file, new TypeReference<ArrayList<Country>>() {});
//			// Java objects to JSON string - compact-print
//			String jsonString = mapper.writeValueAsString(countries);

//			System.out.println(jsonString);
//
//			// Java objects to JSON string - pretty-print
//			String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countries);
//
//			System.out.println(jsonInString2);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return countries;
	}

	public static List<Region> getRegions(List<Country> cCountries, String countryShortCode) {
		List<Region> cRegions = null;
		for (Iterator<Country> iterator = cCountries.iterator(); iterator.hasNext();) {
			Country cCountry = (Country) iterator.next();
			if (countryShortCode.equals(cCountry.getCountryShortCode())) {
				cRegions = cCountry.getRegions();
			}
		}
		return cRegions;
	}
}
