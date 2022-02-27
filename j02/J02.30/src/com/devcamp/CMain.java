package com.devcamp;

public class CMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CPet myPet = new CPet("Lulu", 2, 4, "Yellow");
		myPet.print();
		
		CCat myCat = new CCat("Kiki", 1, 4, "Black");
		myCat.print();
		System.out.println(myCat.bark());
		myCat.play();
		
		CDog myDog = new CDog("Toto", 7, 4, "Grey");
		myDog.print();
		System.out.println(myDog.bark());
		myDog.play();
		
		CPet petPoly1 = new CCat();
		System.out.println("New pet: " + petPoly1.name);
		System.out.println("New pet: " + petPoly1.bark());
		
		CPet petPoly2 = new CDog(null, 0, 0, null);
		System.out.println("New pet: " + petPoly2.name);
		System.out.println("New pet: " + petPoly2.bark());
	}

}
