package com.devcamp.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "car_type")
public class CCarType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "type_code", unique = true)
	private String typeCode;
	
	@Column(name = "type_name")
	private String typeName;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	@JsonBackReference
	// @JsonIgnore
	private CCar car;

	public CCarType(int id, String typeCode, String typeName, CCar car) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.car = car;
	}

	public CCarType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public CCar getCar() {
		return car;
	}

	public void setCar(CCar car) {
		this.car = car;
	}
}
