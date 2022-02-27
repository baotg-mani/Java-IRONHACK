package com.devcamp;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	public ArrayList<Person> getPersons() {
		Address address1 = new Address("Thái Hà", "Hà Nội", "Việt Nam", 100000);
		Address address2 = new Address("Ba Đình", "Hà Nội", "Việt Nam", 100000);

		Professor professor1 = new Professor(30, "male", "Trần Văn Thắng", address1);
		Professor professor2 = new Professor(35, "male", "Nguyễn Văn Ba", address2, 35000000);
		Professor professor3 = new Professor(40, "female", "Nguyễn Linh",
				new Address("Cầu Giấy", "Hà Nội", "Viêt Nam", 100001),
				new ArrayList<Animals>(Arrays.asList(new Fish())),
				new ArrayList<IPlayer>(Arrays.asList(new DVDPlayer())));
		Professor professor4 = new Professor(38, "female", "Nguyễn Thi",
				new Address("Cầu Giấy", "Hà Nội", "Viêt Nam", 100001),
				new ArrayList<Animals>(Arrays.asList(new Fish())),
				new ArrayList<IPlayer>(Arrays.asList(new DVDPlayer())),
				new ArrayList<Ball>(Arrays.asList(new Baseball())));

		Subject subject1 = new Subject();
		Subject subject2 = new Subject("Physical", 2, professor1);
		Subject subject3 = new Subject("HTML", 3, professor2);

		ArrayList<Person> people = new ArrayList<Person>();

		Student student1 = new Student(1, new ArrayList<Subject>() {
			{
				add(new Subject());
				add(new Subject("Physical", 2, new Professor(30, "male", "Trần Văn Thắng",
						new Address("Thái Hà", "Hà Nội", "Việt Nam", 100000), 10000)));
			}
		});
		Student student2 = new Student(2, "Lê Văn Thắng", new ArrayList<Subject>(Arrays.asList(subject1, subject2)),
				new ArrayList<Ball>(Arrays.asList(new Football())));
		Student student3 = new Student(3, "Trần Thị Thủy", new ArrayList<Subject>(),
				new ArrayList<Ball>(Arrays.asList(new Baseball())));
		student3.setListSubject(new ArrayList<Subject>(Arrays.asList(subject1, subject2, subject3)));
		Student student4 = new Student(4, "Trần Gia Kiều", address1,
				new ArrayList<Subject>(Arrays.asList(subject1, subject2)),
				new ArrayList<IPlayer>(Arrays.asList(new CDPlayer())));

		ArrayList<Animals> listAnimals = new ArrayList<Animals>();
		listAnimals.add(new Duck());
		listAnimals.add(new Fish());
		listAnimals.add(new Zebra());
		student2.setListPet(listAnimals);
		student1.setListPet(listAnimals);

		Worker worker1 = new Worker(28, "male", "Tống Giang", address1,
				new ArrayList<Animals>(Arrays.asList(new Fish())),
				new ArrayList<IPlayer>(Arrays.asList(new TapePlayer())));
		Worker worker2 = new Worker(28, "male", "Tấn Tài", address2,
				new ArrayList<Animals>(Arrays.asList(new Fish())),
				new ArrayList<IPlayer>(Arrays.asList(new TapePlayer())),
				new ArrayList<Ball>(Arrays.asList(new Football())));

		people.add(student1);
		people.add(student2);
		people.add(student3);
		people.add(student4);
		people.add(new Worker(25, "male", "Lê Nam", address2));
		people.add(new Professor(45, "male", "Nguyễn Mạnh Tường", address1));
		people.add(professor1);
		people.add(professor2);
		people.add(professor3);
		people.add(professor4);
		people.add(worker1);
		people.add(worker2);

		return people;
	}

	@CrossOrigin
	@GetMapping("/listPerson") // 1-worker, 2-student, 3-professor
	public ArrayList<Person> getListPerson(@RequestParam(value = "type", defaultValue = "0") String type) {
		ArrayList<Person> listPerson = getPersons();
		ArrayList<Person> listPersonFilter = new ArrayList<Person>();

		if (type.equals("1") || type.equals("2") || type.equals("3")) {// lọc dữ liệu
			for (Person person : listPerson) {
				System.out.println("Student: " + person.getName() + "; age=" + person.getAge() + "; address = "
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

	@CrossOrigin
	@GetMapping("/player") // 1-CDPlayer, 2-DVDPlayer, 3-TapePlayer
	public ArrayList<Person> getPlayer(@RequestParam(value = "play", defaultValue = "0") String paramPlay) {
		ArrayList<Person> players = new ArrayList<>();

		if (paramPlay.equals("1") || paramPlay.equals("2") || paramPlay.equals("3")) { // lọc dữ liệu
			for (Person person : getPersons()) {
				if (person.getListPlay() != null) {
					for (IPlayer play : person.getListPlay()) {
						if (paramPlay.equals("1") && play instanceof DVDPlayer
								|| paramPlay.equals("2") && play instanceof CDPlayer
								|| paramPlay.equals("3") && play instanceof TapePlayer) {
							players.add(person);
							System.out.println("ok");
						}
					}
				}
			}
		} else {
			players = getPersons();
		}

		return players;
	}
	
	@CrossOrigin
	@GetMapping("/ball") // 1-Baseball, 2-Football
	public ArrayList<Person> getBallPlayer(@RequestParam(value = "ballType", defaultValue = "0") String paramType) {
		ArrayList<Person> players = new ArrayList<>();

		if (paramType.equals("1") || paramType.equals("2")) { // lọc dữ liệu
			for (Person person : getPersons()) {
				if (person.getListBall() != null) {
					for (Ball ball : person.getListBall()) {
						if (paramType.equals("1") && ball instanceof Baseball
								|| paramType.equals("2") && ball instanceof Football
								) {
							players.add(person);
							System.out.println("ok");
						}
					}
				}
			}
		} else {
			players = getPersons();
		}

		return players;
	}
	
}
