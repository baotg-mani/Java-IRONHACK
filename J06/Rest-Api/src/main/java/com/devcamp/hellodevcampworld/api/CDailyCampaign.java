package com.devcamp.hellodevcampworld.api;

import java.util.Calendar;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CDailyCampaign {
	@CrossOrigin
	@GetMapping("/devcamp-khuyenmai")
	public String getDateViet(@RequestParam(value = "username", defaultValue = "Pizza Lover") String name) {
		String khuyenmai = null;
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == 2) {
			khuyenmai = "thứ Hai khuyến mãi đặc biệt mua 1 tặng 1.";
		} else if (day == 3) {
			khuyenmai = "thứ Ba khuyến mãi một phần bánh ngọt mỗi đơn.";
		} else if (day == 4) {
			khuyenmai = "thứ Tư khuyến mãi một phần nước Coca mỗi đơn.";
		} else {
			khuyenmai = "chương trình Khuyến mãi đã kết thúc.";
		}
		return String.format("Hello %s, %s", name, khuyenmai);
	}
}
