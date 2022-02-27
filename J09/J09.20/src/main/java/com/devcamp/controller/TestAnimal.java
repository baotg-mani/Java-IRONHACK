package com.devcamp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.model.Animals;
import com.devcamp.model.Duck;
import com.devcamp.model.Fish;
import com.devcamp.model.Zebra;

@RestController
public class TestAnimal {
	@CrossOrigin
	@GetMapping("/listAnimal")
		public ArrayList<Animals> getAnimals() {
		
		ArrayList<Animals> listAnimal = new ArrayList<Animals>() {
			{
				add(new Duck("yellow"));
				add(new Duck(3, "female", "white"));
				add(new Fish(2, "female", true));
				add(new Fish(true));
				add(new Zebra(5, "male", true));
				add(new Zebra(4, "female", false));
			}
		};
		
		return listAnimal;
	}
}
