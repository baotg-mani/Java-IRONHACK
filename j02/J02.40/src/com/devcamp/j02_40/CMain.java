package com.devcamp.j02_40;

import java.util.ArrayList;

public class CMain {
	public static void main(String[] args) {

		CPerson per2 = new CPerson(1, 25, "Bao", "Tran Quang", "vietnam", new ArrayList<CVehicle>(), new ArrayList<CPet>());
		
		System.out.println("---------");
		per2.setAge(26);
		per2.setLastName("Tran");
		per2.setCountry("Việt Nam");
		
		CVehicle vehi1 = new CVehicle("Motor");
		CVehicle vehi2 = new CVehicle("Car");
		CVehicle vehi3 = new CVehicle("Bycicle");
		per2.addVehicle(vehi1);
		per2.addVehicle(vehi2);
		per2.addVehicle(vehi3);
		per2.printPerson();
		
		CVehicle car = new CCar();
		CVehicle motor = new CMotorbike();
		CVehicle vehi4 = new CVehicle();
		System.out.println("---------");
		car.honk();
		motor.honk();
		vehi4.honk();
		car.print();
		motor.print();
		vehi4.print();
		
		CPet cat = new CCat("Kiki");
		CPet dog = new CDog("Toto");
		System.out.println("---------");
		cat.bark();
		dog.bark();
		per2.addPet(cat);
		per2.addPet(dog);
		per2.printAllPets();
	}
}
