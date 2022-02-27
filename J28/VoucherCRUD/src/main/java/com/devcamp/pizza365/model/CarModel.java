package com.devcamp.pizza365.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import javax.persistence.*;
import java.io.*;
import java.util.Date;

@Entity
@Table(name = "p_carmodel")
public class CarModel {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long car_id;
	
	@Column(name = "car_code", unique = true)
	private String carCode;
	
	@Column(name = "car_name")
	private String carName;
	
	@OneToMany(mappedBy = "carmodel", cascade = CascadeType.ALL)
    @JsonManagedReference
	private Set<CarType> cartypes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
	public CarModel() {
		
	}
	
	public CarModel(String carCode, String carName) {
		this.carCode = carCode;
		this.carName = carName;
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

	public long getCar_id() {
		return car_id;
	}

	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}

	public Set<CarType> getCartypes() {
		return cartypes;
	}

	public void setCartypes(Set<CarType> cartypes) {
		this.cartypes = cartypes;
	}
	
	
}
