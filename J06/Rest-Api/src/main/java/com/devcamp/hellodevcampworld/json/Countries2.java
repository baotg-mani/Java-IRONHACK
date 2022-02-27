package com.devcamp.hellodevcampworld.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Countries2 {

	public static void main(String[] args) {

		List<CRegion> cRegions = CCountries.getRegions(CCountries.initCountries(), "VN");
		ObjectMapper objectMapper = new ObjectMapper();
		String prettyRegions = "";
		try {
			prettyRegions = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cRegions);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prettyRegions);
	}

}