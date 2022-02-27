package com.devcamp;

public class CMainPet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CPet  myPet = new CCat(1, "Miu Miu");
		myPet.bark();
		myPet.play();
		myPet.print();
		myPet.animalSound();
		((CCat)myPet).run();
		((CCat)myPet).barking();
		System.out.println("-----------------");
		
//		myPet = new CCat(1, "Miu Miu");
//		myPet.bark();
//		myPet.play();
//		myPet.print();
//		System.out.println("-----------------");
		
		myPet = new CDog(2, "Lu Lu");
		myPet.bark();
		myPet.play();
		myPet.print();
		myPet.animalSound();
		((CDog)myPet).run();
		((CDog)myPet).barking();
		
		myPet = new CBird(4, "Flappy");
		myPet.play();
		myPet.print();
		myPet.animalSound();
		((CBird)myPet).fly();
		
		myPet = new CFish(1, "Nemo");
		myPet.play();
		myPet.print();
		myPet.animalSound();
		((CFish)myPet).swim();

	}
}
