package com.devcamp.hellodevcampworld.api;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RandomMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		double randomNum = RandomMain.randomNumber();
//		System.out.println("Random double: " + randomNum);
//		
//		System.out.println("Random double Min-Max: " + RandomMain.randomNumber(10, 1));
//		System.out.println("Random int  Min-Max: " + RandomMain.randomNumberInt(10, 1));
		
		DateTimeFormatter dtfVietnam = DateTimeFormatter.ofPattern("EEEE").localizedBy(Locale.forLanguageTag("vi"));
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		DayOfWeek dayOfWeek = DayOfWeek.from(today);
		System.out.println(String.format("Hello %s ! Hôm nay %s, mua 1 tặng 1.", dayOfWeek.getValue() , dtfVietnam.format(today)));

		
	}
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
