package com.devcamp.shop_pizza365.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Thiếu lastname!")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotEmpty(message = "Thiếu firstname!")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	private String extension;
	
	@Email(message = "Email không hợp lệ!")
	private String email;
	
	@Column(name = "office_code")
	private int officeCode;
	
	@Column(name = "report_to")
	private int reportTo;
	
	@Column(name = "job_title")
	private String jobTitle;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String lastName, String firstName, String extension, String email, int officeCode,
			int reportTo, String jobTitle) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.officeCode = officeCode;
		this.reportTo = reportTo;
		this.jobTitle = jobTitle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(int officeCode) {
		this.officeCode = officeCode;
	}

	public int getReportTo() {
		return reportTo;
	}

	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
