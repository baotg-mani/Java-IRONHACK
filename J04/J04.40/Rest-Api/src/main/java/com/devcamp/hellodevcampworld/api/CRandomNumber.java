package com.devcamp.hellodevcampworld.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRandomNumber {
	@CrossOrigin
	@GetMapping("/devcamp-random")
	public String nice() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		Date now = new Date();
		return String.format("Hello devcamper, now it is %s.", dateFormat.format(now));
	}
	@CrossOrigin
	@GetMapping("/devcamp-random/double-number")
	public String nextDoubleBetween() {
		double randomDouble = (ThreadLocalRandom.current().nextDouble() * (100 - 1)) + 1;
		String string = "random double 1 to 100: " + randomDouble;
		return string;
	}
	@CrossOrigin
	@GetMapping("/devcamp-random/int-number")
	public String nextIntBetween() {
		int randomInt = ThreadLocalRandom.current().nextInt(1, 10 + 1);
		String string = "random int 1 to 10: " + randomInt;
		return string;
	}
}
