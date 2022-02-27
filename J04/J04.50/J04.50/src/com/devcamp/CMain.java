package com.devcamp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import javax.tools.DocumentationTool.Location;

public class CMain {

	public static void main(String[] args) {
		// A collection to store country object
		ArrayList<CCountry> countries = new ArrayList<>();
		ArrayList<CRegion> regions = new ArrayList<>();
		regions.add(new CRegion("VN-44", "An Giang"));
		regions.add(new CRegion("VN-43", "Bà Rịa - Vũng Tàu"));	
		regions.add(new CRegion("VN-54", "Bắc Giang"));	
		regions.add(new CRegion("VN-53", "Bắc Kạn"));	
		regions.add(new CRegion("VN-55", "Bạc Liêu"));	
		regions.add(new CRegion("VN-56", "Bắc Ninh"));
		regions.add(new CRegion("VN-50", "Bến Tre"));	
		regions.add(new CRegion("VN-31", "Bình Định"));	
		regions.add(new CRegion("VN-57", "Bình Dương"));	
		regions.add(new CRegion("VN-58", "Bình Phước"));	
		regions.add(new CRegion("VN-40", "Bình Thuận"));	
		regions.add(new CRegion("VN-59", "Cà Mau"));	
		regions.add(new CRegion("VN-04", "Cao Bằng"));	
		regions.add(new CRegion("VN-33", "Đắk Lắk"));	
		regions.add(new CRegion("VN-72", "Đắk Nông"));	
		regions.add(new CRegion("VN-71", "Điện Biên"));	
		regions.add(new CRegion("VN-39", "Đồng Nai"));	
		regions.add(new CRegion("VN-45", "Đồng Tháp"));	
		regions.add(new CRegion("VN-30", "Gia Lai"));	
		regions.add(new CRegion("VN-03", "Hà Giang"));	
		regions.add(new CRegion("VN-63", "Hà Nam"));	
		regions.add(new CRegion("VN-23", "Hà Tĩnh"));	
		regions.add(new CRegion("VN-61", "Hải Dương"));	
		regions.add(new CRegion("VN-73", "Hậu Giang"));	
		regions.add(new CRegion("VN-14", "Hòa Bình"));	
		regions.add(new CRegion("VN-66", "Hưng Yên"));	
		regions.add(new CRegion("VN-34", "Khánh Hòa"));	
		regions.add(new CRegion("VN-47", "Kiến Giang"));	
		regions.add(new CRegion("VN-28", "Kon Tum"));	
		regions.add(new CRegion("VN-01", "Lai Châu"));	
		regions.add(new CRegion("VN-35", "Lâm Đồng"));	
		regions.add(new CRegion("VN-09", "Lạng Sơn"));	
		regions.add(new CRegion("VN-02", "Lào Cai"));	
		regions.add(new CRegion("VN-41", "Long An"));	
		regions.add(new CRegion("VN-67", "Nam Định"));	
		regions.add(new CRegion("VN-22", "Nghệ An"));	
		regions.add(new CRegion("VN-18", "Ninh Bình"));	
		regions.add(new CRegion("VN-36", "Ninh Thuận"));	
		regions.add(new CRegion("VN-68", "Phú Thọ"));	
		regions.add(new CRegion("VN-32", "Phú Yên"));	
		regions.add(new CRegion("VN-24", "Quảng Bình"));	
		regions.add(new CRegion("VN-27", "Quảng Nam"));	
		regions.add(new CRegion("VN-29", "Quảng Ngãi"));	
		regions.add(new CRegion("VN-13", "Quảng Ninh"));	
		regions.add(new CRegion("VN-25", "Quảng Trị"));	
		regions.add(new CRegion("VN-52", "Sóc Trăng"));	
		regions.add(new CRegion("VN-05", "Sơn La"));	
		regions.add(new CRegion("VN-37", "Tây Ninh"));	
		regions.add(new CRegion("VN-20", "Thái Bình"));	
		regions.add(new CRegion("VN-69", "Thái Nguyên"));	
		regions.add(new CRegion("VN-21", "Thanh Hóa"));	
		regions.add(new CRegion("VN-26", "Thừa Thiên-Huế"));	
		regions.add(new CRegion("VN-46", "Tiền Giang"));	
		regions.add(new CRegion("VN-51", "Trà Vinh"));	
		regions.add(new CRegion("VN-07", "Tuyên Quang"));	
		regions.add(new CRegion("VN-49", "Vĩnh Long"));	
		regions.add(new CRegion("VN-70", "Vĩnh Phúc"));	
		regions.add(new CRegion("VN-06", "Yên Bái"));	
		regions.add(new CRegion("VN-CT", "Cần Thơ"));	
		regions.add(new CRegion("VN-DN", "Đà Nẵng"));	
		regions.add(new CRegion("VN-HN", "Hà Nội"));	
		regions.add(new CRegion("VN-HP", "Hải Phòng"));	
		regions.add(new CRegion("VN-SG", "Hồ Chí Minh"));	
			
		// Get ISO countries, create Country object and
		// add into the collection
		String[] isoCountries = Locale.getISOCountries();
		
		for (String iso : isoCountries) {
			Locale locale = new Locale("en", iso);
			String code = locale.getCountry();
			String name = locale.getDisplayCountry();
			
			if (!"".equals(iso) && !"".equals(code) && !"".equals(name)){
				countries.add(new CCountry(code, name));
			}
		}	
		
		// Display the content of countries collection object
		for (CCountry country : countries) {
			System.out.println(country.toString());
			if (country.getCountryName() == "Vietnam") {
				country.setRegions(regions);
				for (CRegion region : country.getRegions()) {
					System.out.println("  " + region.getRegionCode() + " " + region.getRegionName());
				}
			}
		}	
	}
            

}
