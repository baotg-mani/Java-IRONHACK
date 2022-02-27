package com.devcamp.hellodevcampworld.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CCountries {

	public static void main(String[] args) {

		getRegions(initCountries(), "VN");

	}
	public <T> Object deserialize(String xml, Class objClass ,TypeReference<T> typeReference ) throws IOException {
		ObjectMapper xmlMapper = new ObjectMapper();
	    Object obj = xmlMapper.readValue(xml,objClass);
	    return  xmlMapper.convertValue(obj,typeReference );   
	}
	
	public <T> List<T> jsonArrayToObjectList(String json, Class<T> tClass) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
	    List<T> ts = mapper.readValue(json, listType);
	    return ts;
	}
	public static List<CCountry> initCountries() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<CCountry> cCountries = new ArrayList<CCountry>();			
		try {
			
			cCountries = objectMapper.readValue(new File("d:\\test\\data.json"), new TypeReference<ArrayList<CCountry>>() {
			});

			// compact print
			System.out.println(cCountries);

			// pretty print
			String prettyStaff1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cCountries);

			System.out.println(prettyStaff1);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cCountries;
	}

	public static List<CRegion> getRegions(List<CCountry> cCountries, String countryShortCode) {
		List<CRegion> cRegions = null;
		for (Iterator<CCountry> iterator = cCountries.iterator(); iterator.hasNext();) {
			CCountry cCountry = (CCountry) iterator.next();
			if (countryShortCode.equals(cCountry.getCountryShortCode())) {
				cRegions = cCountry.getRegions();
			}
		}
		return cRegions;
	}

}