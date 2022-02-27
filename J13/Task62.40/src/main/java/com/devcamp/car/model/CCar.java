package com.devcamp.car.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "car")
public class CCar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "car_code", unique = true)
	private String carCode;

	@Column(name = "car_name")
	private String carName;

	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<CCarType> types;

	public CCar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CCar(int id, String carCode, String carName) {
		super();
		this.id = id;
		this.carCode = carCode;
		this.carName = carName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Set<CCarType> getTypes() {
		return types;
	}

	public void setTypes(Set<CCarType> types) {
		this.types = types;
	}

}
