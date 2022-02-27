package com.devcamp;

import java.util.ArrayList;

public class CMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CPerson person1 = new CPerson();
//		CPerson person2 = new CPerson(1, 20, "Le Van", "Thang");
//		
//		System.out.println("Fullname = " + person1.getFullname());
//		System.out.println("Fullname = " + person2.getFullname());
//		System.out.println("age = " + person2.getAge());
//		
	    ArrayList<String> cars = new ArrayList<String>();
	    cars.add("Volvo");
	    cars.add("BMW");
	    cars.add("Ford");
	    cars.add("Mazda");
	    System.out.println(cars);
	    
	    ArrayList<CPet> pets = new ArrayList<CPet>(); 
	    CPet dog = new CDog(1, "Lulu");
	    pets.add(dog);
	    
	    CPet cat = new CCat("Milo", 2);
	    pets.add(cat);
	    
	    CPet bird = new CBird("Flappy", 4);
	    pets.add(bird);
	    
	    CPet fish = new CFish("Nemo", 1);
	    pets.add(fish);
	    
	    CPerson person3 = new CPerson(2, 25, "Nguyen Manh", "Tuong", "Vietnam", cars, pets);
	    person3.printPerson();
	    
	    /*
	    person3.country = "China";
	    //person3.age = 30;
	    person3.setAge(30);
	    person3.addCar("Toyota");
	    
	    ArrayList<String> cars2 = new ArrayList<String>();
	    cars2.add("Vin");
	    cars2.add("Audi");
	    person3.addCars(cars2);
	    person3.printPerson();
	    
	    person2.printPerson();
	    */
	    
	}

}

