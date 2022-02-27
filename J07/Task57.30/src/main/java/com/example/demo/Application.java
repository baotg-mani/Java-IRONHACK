package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	
	ArrayList<Person> people = new ArrayList<Person>();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@GetMapping("/students")
	public ArrayList<Person> getPeople() {
		ArrayList<Person> students = new ArrayList<>();
		Person my = new Person(28, "male", "My", new Address("Quan 1", "HCM", "Vietnam", 230000));
		Person loc = new Person(28, "male", "Loc", new Address("Quan 2", "HCM", "Vietnam", 230000));
		Student bao = new Student(25, "male", "Bao", new Address("Cau Giay", "HN", "Vietnam", 250000));
		Student ngan = new Student(24, "female", "Ngan", new Address("Ha Dong", "HN", "Vietnam", 250000));
		Student thu = new Student(22, "female", "Thu", new Address("Nam Tu Liem", "HN", "Vietnam", 250000));
		Professor tuanPro = new Professor(35, "male", "Tuấn", new Address("Ba Dinh", "HN", "Vietnam", 250000), 20000000);
		Professor linhPro = new Professor(30, "male", "Lĩnh", new Address("Quận 2", "HCM", "Vietnam", 230000), 20000000);
		Professor hieuPro = new Professor(37, "male", "Hiệu", new Address("Cầu Giấy", "HN", "Vietnam", 250000), 22000000);
		people.add(my);
		people.add(loc);
		people.add(bao);
		people.add(ngan);
		people.add(thu);
		people.add(tuanPro);

		ArrayList<Subject> subjects = new ArrayList<>();
		Subject html = new Subject("HTML", 11, linhPro);
		Subject css = new Subject("CSS", 12, linhPro);
		Subject javascript = new Subject("Javascript", 13, linhPro);
		Subject java = new Subject("Java", 14, hieuPro);
		Subject mysql = new Subject("mySQL", 15, tuanPro);
		subjects.add(html);
		subjects.add(css);
		subjects.add(javascript);
		subjects.add(java);
		
		Person stu1 = new Student(20, "female", "Hoài", new Address("Quan 1", "HCM", "Vietnam", 230000), 1, subjects);
		bao.setStudentId(2);
		bao.setListSubject(subjects);
		thu.setStudentId(3);
		thu.setListSubject(subjects);
		
		students.add(stu1);
		students.add(bao);
		students.add(thu);
		
		return students;
	}
	
	@GetMapping("/university")
	public University getUniversity() {
		University myUni = new University(777, "Laos Coder University", people);
		return myUni;
	}

}
