package com.devcamp.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class S10_Controller {

	public CSchool getSchool() {
		ArrayList<CClass> listClass = new ArrayList<>();
		CSchool MoCheSchool = new CSchool(listClass);
		CClass lop1 = new CClass("001", "Lớp 1", "Hoàng Ngân", "088888888",
				new ArrayList<CStudent>(
						Arrays.asList(new CStudent("1", "Bảo", "male", "27/7/2015", "Mỏ Chè", "0336699146"),
								new CStudent("2", "Huy", "male", "20/8/2015", "Mỏ Chè", "0996699146"),
								new CStudent("3", "Nga", "female", "1/8/2015", "Phố Cò", "0996699777"))));
		for(CStudent stu: lop1.getListStudent()) {
			stu.goToSchool();
			stu.study();
		}
		CClass lop2 = new CClass("002", "Lớp 2", "Hoàng Ngân", "088888888",
				new ArrayList<CStudent>(
						Arrays.asList(new CStudent("21", "Minh", "male", "27/7/2014", "Phố Cò", "0336699888"),
								new CStudent("22", "Hưng", "male", "20/8/2014", "Phố Cò", "0996699116"),
								new CStudent("23", "Linh", "female", "1/8/2014", "Thắng Lợi", "0996699434"))));
		for(CStudent stu: lop2.getListStudent()) {
			stu.goToSchool();
			stu.study();
		}
		CClass lop3 = new CClass("003", "Lớp 3", "Chu Linh", "0888777777",
				new ArrayList<CStudent>(
						Arrays.asList(new CStudent("31", "Hạnh", "female", "27/7/2013", "Mỏ Chè", "0336699115"),
								new CStudent("32", "Sơn", "male", "20/8/2013", "Cải Đan", "0996699442"),
								new CStudent("33", "Khánh", "female", "1/8/2013", "Cải Đan", "0996699444"))));
		for(CStudent stu: lop3.getListStudent()) {
			stu.goToSchool();
			stu.study();
		}
		CClass lop4 = new CClass("004", "Lớp 4", "Nguyễn Nhạn", "0888666666",
				new ArrayList<CStudent>(
						Arrays.asList(new CStudent("41", "Quỳnh", "female", "27/7/2012", "Bách Quang", "0336699132"),
								new CStudent("42", "Hưng", "male", "20/8/2012", "Mỏ Chè", "0996699123"),
								new CStudent("43", "Bằng", "male", "1/8/2012", "Bình Sơn", "0996699111"))));
		for(CStudent stu: lop4.getListStudent()) {
			stu.goToSchool();
			stu.study();
		}
		CClass lop5 = new CClass("005", "Lớp 5", "Trần Hiệu", "0888555555",
				new ArrayList<CStudent>(
						Arrays.asList(new CStudent("51", "Trang", "female", "27/7/2011", "Mỏ Chè", "0336699756"),
								new CStudent("52", "Bình", "male", "20/8/2011", "Bình Sơn", "0996699466"),
								new CStudent("53", "Nga", "female", "1/8/2011", "Thắng Lợi", "0996699234"))));
		for(CStudent stu: lop5.getListStudent()) {
			stu.goToSchool();
			stu.study();
		}
		MoCheSchool.setListClass(new ArrayList<CClass>() {
			{
				add(lop1);
				add(lop2);
				add(lop3);
				add(lop4);
				add(lop5);
			}
		});
		for(CClass lop: MoCheSchool.getListClass()) {
			lop.goToSchool();
			lop.study();
		}

		return MoCheSchool;
	}
	
	@CrossOrigin
	@GetMapping("/school")
	public CSchool runApi() {
		
		return getSchool();
	}

}
