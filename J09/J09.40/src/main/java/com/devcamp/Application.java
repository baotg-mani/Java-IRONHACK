package com.devcamp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.model.Address;
import com.devcamp.model.Animals;
import com.devcamp.model.Duck;
import com.devcamp.model.Fish;
import com.devcamp.model.Person;
import com.devcamp.model.Professor;
import com.devcamp.model.Student;
import com.devcamp.model.Subject;
import com.devcamp.model.University;
import com.devcamp.model.Worker;
import com.devcamp.model.Zebra;

@SpringBootApplication
@RestController
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@CrossOrigin
	@GetMapping("/students")
	public ArrayList<Person> getStudents() {

		Professor linhPro = new Professor(30, "male", "Lĩnh", new Address("Quận 2", "HCM", "Vietnam", 230000), 5000);
		Professor hieuPro = new Professor(37, "male", "Hiệu", new Address("Cầu Giấy", "HN", "Vietnam", 250000), 5000);

		Subject javascript = new Subject("Javascript", 13, linhPro);
		Subject react = new Subject("ReactJS", 12, linhPro);
		Subject java = new Subject("Java", 14, hieuPro);
		Subject spring = new Subject("Spring", 15, hieuPro);
		ArrayList<Subject> subjects = new ArrayList<Subject>() {
			{
				add(javascript);
				add(java);
			}
		};

		subjects.add(javascript);
		subjects.add(java);

		Person my = new Student(28, "male", "My", new Address("Quan 1", "HCM", "Vietnam", 230000), 0, subjects);
		Person loc = new Student(28, "male", "Loc", new Address("Quan 2", "HCM", "Vietnam", 230000), 1, subjects);
		Student bao = new Student(25, "male", "Bao", new Address("Cau Giay", "HN", "Vietnam", 250000), 2,
				new ArrayList<Subject>(Arrays.asList(java, javascript, spring)));
		Student ngan = new Student(24, "female", "Ngan", new Address("Ha Dong", "HN", "Vietnam", 250000), 3,
				new ArrayList<Subject>(Arrays.asList(java, javascript)));
		Student thu = new Student(22, "female", "Thu", new Address("Nam Tu Liem", "HN", "Vietnam", 250000), 4,
				new ArrayList<Subject>(Arrays.asList(javascript, react)));
		ArrayList<Person> students = new ArrayList<>();
		students.add(my);
		students.add(loc);
		students.add(bao);
		students.add(ngan);
		students.add(thu);

		return students;
	}

	@CrossOrigin
	@GetMapping("/people")
	public ArrayList<Person> getPeople(@RequestParam(name = "type") int paramType) {
		// declare list people
		ArrayList<Person> people = new ArrayList<>();

		// initialize pet
		Animals duck = new Duck(2, "female", "yellow");
		Animals fish = new Fish(3, "male", true);
		Animals zebra = new Zebra(5, "female", false);
		// initialize list pet
		ArrayList<Animals> listPet = new ArrayList<Animals>(Arrays.asList(duck, fish, zebra));

		// initialize professors
		Professor linhPro = new Professor(38, "male", "Lĩnh", new Address("Quận 2", "HCM", "Vietnam", 233311), 5000,
				listPet);
		Professor hieuPro = new Professor(37, "male", "Hiệu", new Address("Cầu Giấy", "HN", "Vietnam", 258822), 5000,
				listPet);
		Professor tuanPro = new Professor(36, "male", "Tuấn", new Address("Đà Nẵng", "QN", "Vietnam", 115888), 5500,
				new ArrayList<Animals>(Arrays.asList(fish)));
		ArrayList<Person> professors = new ArrayList<Person>();
		professors.add(hieuPro);
		professors.add(tuanPro);
		professors.add(linhPro);
		// --- done list professors

		// initialize subject
		Subject javascript = new Subject("Javascript", 13, tuanPro);
		Subject react = new Subject("ReactJS", 12, linhPro);
		Subject java = new Subject("Java", 14, hieuPro);
		Subject spring = new Subject("Spring", 15, hieuPro);
		// initialize list subject
		ArrayList<Subject> subjects = new ArrayList<Subject>() {
			{
				add(javascript);
				add(java);
				add(react);
				add(spring);
			}
		};

		// declare list student & initialize student
		ArrayList<Person> students = new ArrayList<>();
		Person my = new Student(28, "male", "Mỹ", new Address("Quận 1", "HCM", "Vietnam", 231111), 0, subjects,
				new ArrayList<Animals>(Arrays.asList(fish, duck)));
		Person loc = new Student(28, "male", "Lộc", new Address("Quận 2", "HCM", "Vietnam", 233311), 1, subjects,
				new ArrayList<Animals>(Arrays.asList(zebra, duck)));
		Person bao = new Student(25, "male", "Bảo", new Address("Cầu Giấy", "HN", "Vietnam", 258822), 2,
				new ArrayList<Subject>(Arrays.asList(java, javascript, spring)),
				new ArrayList<Animals>(Arrays.asList(zebra)));
		Person ngan = new Student(24, "female", "Ngân", new Address("Tây Hồ", "HN", "Vietnam", 240022), 3,
				new ArrayList<Subject>(Arrays.asList(java, javascript)), listPet);
		Person thu = new Student(22, "female", "Thu", new Address("Hoàn Kiếm", "HN", "Vietnam", 252200), 4,
				new ArrayList<Subject>(Arrays.asList(javascript, react)), listPet);
		students.add(thu);
		students.add(my);
		students.add(loc);
		students.add(bao);
		students.add(ngan);
		// --- done list students

		// initialize worker & list worker
		Person worker1 = new Worker(23, "male", "worker1", new Address("Quận 2", "HCM", "Vietnam", 233311), 500,
				listPet);
		Person worker2 = new Worker(25, "male", "worker2", new Address("Quận 2", "HCM", "Vietnam", 233311), 600,
				listPet);
		Person worker3 = new Worker(23, "male", "worker3", new Address("Quận 2", "HCM", "Vietnam", 233311), 450,
				listPet);
		Person worker4 = new Worker(24, "male", "worker4", new Address("Quận 2", "HCM", "Vietnam", 233311), 500,
				listPet);
		Person worker5 = new Worker(22, "male", "worker5", new Address("Quận 2", "HCM", "Vietnam", 233311), 400,
				listPet);
		ArrayList<Person> workers = new ArrayList<>();
		workers.add(worker1);
		workers.add(worker2);
		workers.add(worker3);
		workers.add(worker4);
		workers.add(worker5);
		// --- done list worker

		if (paramType == 1) {
			people = students;
		} else if (paramType == 2) {
			people = workers;
		} else if (paramType == 3) {
			people = professors;
		} else {
			people.addAll(workers);
			people.addAll(students);
			people.addAll(professors);
		}
		
		return people;
	}

}
