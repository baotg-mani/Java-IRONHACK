package com.devcamp.hellodevcampworld.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.hellodevcampworld.api.menu.CMenu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@CrossOrigin
@RestController
public class CPizzaCampaign {
	@CrossOrigin
	@GetMapping("/devcamp-date")
	public String getDateViet(@RequestParam(value = "username", defaultValue = "Pizza Lover") String name) {
		DateTimeFormatter dtfVietnam = DateTimeFormatter.ofPattern("EEEE").localizedBy(Locale.forLanguageTag("vi"));
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		return String.format("Hello %s ! Hôm nay %s, mua 1 tặng 1.", name, dtfVietnam.format(today));
	}

	@CrossOrigin
	@GetMapping("/devcamp-infor")
	public String getInfor(@RequestParam(value = "name", defaultValue = "Pizza Lover") String fullName) {
		DateTimeFormatter dtfVietnam = DateTimeFormatter.ofPattern("EEEE").localizedBy(Locale.forLanguageTag("vi"));
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		return String.format("Hello %s ! Hôm nay %s, mua 1 tặng 1.", fullName, dtfVietnam.format(today));
	}

	@CrossOrigin
	@GetMapping("/devcamp-simple")
	public String simple() {
		return "test campaign";
	}

	@CrossOrigin
	@GetMapping("/devcamp-welcome1")
	public String nice() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Date now = new Date();
		return String.format("Hello devcamper, now it is %s.", dateFormat.format(now));
	}

	// Bài giải cho bài 63.30 TDD Làm rest api dice
	@CrossOrigin
	@GetMapping("/devcamp-lucky")
	public String getRandom(@RequestParam(value = "name") String fullName) {
		return String.format("Xin chào: %s, Số may mắn hôm nay của bạn là: %s", fullName,RandomMain.randomNumberInt(6, 1));
	}
	@CrossOrigin
	@GetMapping("/devcamp-menu")
	public ArrayList<CMenu> getMenu() {
		CMenu cMenu = new CMenu('S', 12, 13, 12, 12, 1111);
		CMenu cMenu1 = new CMenu('S', 12, 13, 12, 12, 1111);
		ArrayList<CMenu> cMenus = new ArrayList<CMenu>();
		cMenus.add(cMenu);
		cMenus.add(cMenu1);
		return cMenus;
	}

}

/*
//Bài giải cho bài 63.30 TDD Làm rest api dice
//Tách class này ra file riêng
class RandomMain1 {
	public static double randomNumber() {
	     double x = Math.random();
	     return x;
	}
	public static double randomNumber(int max, int min) {
		
	     double x = (Math.random()) * ((max - min) + 1) + min;
	     return x;
	}
	public static int randomNumberInt(int max, int min) {
	     int  x = (int)((Math.random()) * ((max - min) + 1) + min);
	     return x;
	}
}
*/