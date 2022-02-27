package com.devcamp.pizza365.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "p_cartype")
public class CarType {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartype_id;
	
	@Column(name = "type_code", unique = true)
	private String typeCode;
	
	@Column(name = "type_name")
	private String typeName;
	
	@ManyToOne
    @JoinColumn(name="car_id")
	@JsonIgnore
    private CarModel carmodel;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true, updatable = false )
    @CreatedDate
    @CreationTimestamp
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

	
	public CarType(){
		
	}
	public CarType(String typeCode, String typeName) {
		this.typeCode = typeCode;
		this.typeName = typeName;
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

	public long getCartype_id() {
		return cartype_id;
	}

	public void setCartype_id(long cartype_id) {
		this.cartype_id = cartype_id;
	}
	@JsonIgnore
	public CarModel getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(CarModel carmodel) {
		this.carmodel = carmodel;
	}
	
	
}
