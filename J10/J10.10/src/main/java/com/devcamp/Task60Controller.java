package com.devcamp;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Task60Controller {

	public ArrayList<Person> getPersons() {
		Address address1 = new Address("Thái Hà", "Hà Nội", "Việt Nam", 100000);
		Address address2 = new Address("Ba Đình", "Hà Nội", "Việt Nam", 100000);

		Professor professor1 = new Professor(30, "male", "Trần Văn Thắng", address1);
		Professor professor2 = new Professor(35, "male", "Nguyễn Văn Ba", address2, 35000000);

		Subject subject1 = new Subject();
		Subject subject2 = new Subject("Physical", 2, professor1);
		Subject subject3 = new Subject("HTML", 3, professor2);

		ArrayList<Person> lstStudents = new ArrayList<Person>();

		Student student1 = new Student(1, new ArrayList<Subject>() {
			{
				add(new Subject());
				add(new Subject("Physical", 2, new Professor(30, "male", "Trần Văn Thắng",
						new Address("Thái Hà", "Hà Nội", "Việt Nam", 100000), 10000)));
			}
		});

		Student student2 = new Student(2, "Lê Văn Thắng", new ArrayList<Subject>(Arrays.asList(subject1, subject2)));

		Student student3 = new Student(3, "Trần Thị Thủy", new ArrayList<Subject>());
		student3.setListSubject(new ArrayList<Subject>(Arrays.asList(subject1, subject2, subject3)));
		Student student4 = new Student(4, new ArrayList<Subject>(Arrays.asList(subject1, subject2)),
				new ArrayList<Animals>() {
					{
						add(new Duck(1, "male", "yellow"));
						add(new Fish());
						add(new Zebra());
					}
				});

		student1.sleep();
		student1.animalSound();
		student1.gotoShool();
		student1.gotoShop();
		student1.play();

		professor1.sleep();
		professor1.animalSound();
		professor1.gotoShool();
		professor1.gotoShop();
		professor1.play();

		ArrayList<Animals> listAnimals = new ArrayList<Animals>();
		listAnimals.add(new Duck());
		listAnimals.add(new Fish());
		listAnimals.add(new Zebra());
		student2.setListPet(listAnimals);
		student1.setListPet(listAnimals);

		lstStudents.add(student1);
		lstStudents.add(student2);
		lstStudents.add(student3);
		lstStudents.add(student4);
		lstStudents.add(new Worker(25, "male", "Lê Nam", address2));
		lstStudents.add(new Professor(45, "male", "Nguyễn Mạnh Tường", address1));
		lstStudents.add(professor1);

		return lstStudents;
	}

	@CrossOrigin
	@GetMapping("/listPerson") // 1-worker, 2-student, 3-professor
	public ArrayList<Person> getListPerson(@RequestParam(value = "type", defaultValue = "0") String type) {
		ArrayList<Person> listPerson = getPersons();
		ArrayList<Person> listPersonFilter = new ArrayList<Person>();

		if (type.equals("1") || type.equals("2") || type.equals("3")) {// lọc dữ liệu
			for (Person person : listPerson) {
				System.out.println("Student: " + person.getName() + "; age=" + person.getAge() + "; adress = "
						+ person.getAddress());
				for (Animals animal : person.getListPet()) {
					System.out.println("Animal: " + animal);
				}
				if ((type.equals("1") && person.getPersonType() == PersonType.WORKER)
						|| (type.equals("2") && person.getPersonType() == PersonType.STUDENT)
						|| (type.equals("3") && person.getPersonType() == PersonType.PROFESSOR)) {
					listPersonFilter.add(person);
				}
				/*
				 * if ((type.equals("1") && person instanceof Worker) || (type.equals("2") &&
				 * person instanceof Student) || (type.equals("3") && person instanceof
				 * Professor)) { listPersonFilter.add(person); }
				 */
			}
		} else {// trả về hết
			listPersonFilter = listPerson;
		}
		return listPersonFilter;
	}

}
