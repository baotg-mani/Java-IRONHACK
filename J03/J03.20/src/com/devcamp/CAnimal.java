package com.devcamp;

public abstract class CAnimal {
	private AnimalClass animalClass;

	public AnimalClass getAnimalClass() {
		return animalClass;
	}

	public void setAnimalClass(AnimalClass animalClass) {
		this.animalClass = animalClass;
	}

	public abstract void animalSound();
	public void eat() {
		System.out.println("Animal eating ...");
	}

}
