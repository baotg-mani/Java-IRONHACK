package com.devcamp.hellodevcampworld.json;

import java.math.BigDecimal;
import java.util.*;

public class Staff {

    private String name;
    private int age;
    private String[] position;              //  Array
    private List<String> skills;            //  List
    private Map<String, BigDecimal> salary; //  Map
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the position
	 */
	public String[] getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String[] position) {
		this.position = position;
	}
	/**
	 * @return the skills
	 */
	public List<String> getSkills() {
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	/**
	 * @return the salary
	 */
	public Map<String, BigDecimal> getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Map<String, BigDecimal> salary) {
		this.salary = salary;
	}

    // getters , setters, some boring stuff
}
