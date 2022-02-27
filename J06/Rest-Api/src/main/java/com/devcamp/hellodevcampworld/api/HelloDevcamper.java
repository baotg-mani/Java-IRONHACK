package com.devcamp.hellodevcampworld.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloDevcamper {
	@CrossOrigin
	@GetMapping("/devcamp-welcome")
	public String welcome() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Date now = new Date();
		return String.format("Hello devcamper today is Tuesday, now it is %s.", dateFormat.format(now));
	}

	@CrossOrigin
	@GetMapping("/dice1")
	public String dice1(@RequestParam(value = "username", defaultValue = "World") String name,
			@RequestParam String lastname, @RequestParam(value = "firstname") String firstName,
			@RequestParam(required = false) String go) {
		return String.format("Have a nice day. It is  %s. My fullname is %s %s! ", name, firstName, lastname);
	}
	@CrossOrigin
	@GetMapping("/dice")
	public String dice(@RequestParam(value = "username", defaultValue = "World") String name,
			@RequestParam String lastname, @RequestParam(value = "firstname") String firstName) {
		return String.format("Have a nice day. It is  %s. My fullname is %s %s! ", name, firstName, lastname);
	}}