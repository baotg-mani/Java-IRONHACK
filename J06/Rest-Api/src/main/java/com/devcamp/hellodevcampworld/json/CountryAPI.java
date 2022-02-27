package com.devcamp.hellodevcampworld.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CountryAPI {
	@CrossOrigin
	@GetMapping("/devcamp-welcome")
	public String welcome() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Date now = new Date();
		return String.format("Hello devcamper today is Tuesday, now it is %s.", dateFormat.format(now));
	}

	@CrossOrigin
	@GetMapping("/countries")
	public String countries(@RequestParam(value = "countryCode", defaultValue = "VN") String countryShortCode) {
		List<CRegion> cRegions =  CCountries.getRegions(CCountries.initCountries(),countryShortCode);
		ObjectMapper objectMapper = new ObjectMapper();
		String prettyRegions = "";
		try {
			prettyRegions = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cRegions);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prettyRegions;
	}
}