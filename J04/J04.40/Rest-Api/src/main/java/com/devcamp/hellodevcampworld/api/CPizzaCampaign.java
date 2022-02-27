package com.devcamp.hellodevcampworld.api;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.hellodevcampworld.api.person.CPerson;

@RestController
public class CPizzaCampaign {
	
	@CrossOrigin
	@GetMapping("/devcamp-pizza/hello-pizza-lover")
	public String helloPizza() {
		String name = "lover";
		DateTimeFormatter dtfVietnam = DateTimeFormatter.ofPattern("EEEE").localizedBy(Locale.forLanguageTag("vi"));
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		return String.format("Hello %s! Hôm nay %s, mua 1 tặng 1.", name, dtfVietnam.format(today));
	}
	
	@CrossOrigin
	@GetMapping("/devcamp-pizza/hello-user")
	public String helloUser() {
		return String.format("Xin chào: %s, Số may mắn hôm nay của bạn là: %d", CPerson.getUsername(), numberOfUser());
	}
	
	@CrossOrigin
	@GetMapping("/devcamp-pizza/hello-app")
	public String helloApp() {
		DateTimeFormatter dtfVietnam = DateTimeFormatter.ofPattern("EEEE").localizedBy(Locale.forLanguageTag("vi"));
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		return String.format("Hello Pizza Light ! application", dtfVietnam.format(today));
	}
	
	// generate user
	CPerson user = new CPerson ("BaoTG", "Bao", "Tran");
	
	// function get random number from 1 to 6
	public int numberOfUser() {
		int randomInt = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		return randomInt;
	}
}

