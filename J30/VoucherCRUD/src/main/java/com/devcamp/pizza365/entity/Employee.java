package com.devcamp.pizza365.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String extension;

	@Column(name="first_name")
	private String firstName;

	@Column(name="job_title")
	private String jobTitle;

	@Column(name="last_name")
	private String lastName;

	@Column(name="office_code")
	private int officeCode;

	@Column(name="report_to")
	private int reportTo;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getOfficeCode() {
		return this.officeCode;
	}

	public void setOfficeCode(int officeCode) {
		this.officeCode = officeCode;
	}

	public int getReportTo() {
		return this.reportTo;
	}

	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}

}