package com.devcamp.hellodevcampworld.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloDevcamper {
	@CrossOrigin
	@GetMapping("/devcamp-simple")
	public String nice() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Date now= new Date();
		return String.format("Hello devcamper, now it is %s.", dateFormat.format(now));
	}
}