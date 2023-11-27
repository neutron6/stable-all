package com.rsn.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@EntityScan("com.rsn.*")
@Transactional
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Employee_First_Name")
	private String employeeFirst_Name;

	@Column(name = "Employee_Last_Name")
	private String employeeLast_Name;

	@Column(name = "Employee_City_Name")
	private String employeeCity_Name;

	@Column(name = "Employee_Resident_Address")
	private String employeeResident_Address;

	@Column(name = "Employee_Married_status")
	private String employeeMarried_status;

	@Column(name = "Employee_Email")
	private String employeeEmail;

	/*
	 * The @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) prevents the
	 * password field from being included in the JSON response.
	 */
	@Column(name = "Employee_Password")
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String employeePassword;

	@Column(name = "Employee_Joining_Date")
	private LocalDate employeeJoining_Date;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_empbank_id")
	private EmployeeBankData employeeBankData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeFirst_Name() {
		return employeeFirst_Name;
	}

	public void setEmployeeFirst_Name(String employeeFirst_Name) {
		this.employeeFirst_Name = employeeFirst_Name;
	}

	public String getEmployeeLast_Name() {
		return employeeLast_Name;
	}

	public void setEmployeeLast_Name(String employeeLast_Name) {
		this.employeeLast_Name = employeeLast_Name;
	}

	public String getEmployeeCity_Name() {
		return employeeCity_Name;
	}

	public void setEmployeeCity_Name(String employeeCity_Name) {
		this.employeeCity_Name = employeeCity_Name;
	}

	public String getEmployeeResident_Address() {
		return employeeResident_Address;
	}

	public void setEmployeeResident_Address(String employeeResident_Address) {
		this.employeeResident_Address = employeeResident_Address;
	}

	public String getEmployeeMarried_status() {
		return employeeMarried_status;
	}

	public void setEmployeeMarried_status(String employeeMarried_status) {
		this.employeeMarried_status = employeeMarried_status;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public LocalDate getEmployeeJoining_Date() {
		return employeeJoining_Date;
	}

	public void setEmployeeJoining_Date(LocalDate employeeJoining_Date) {
		this.employeeJoining_Date = employeeJoining_Date;
	}

	public EmployeeBankData getEmployeeBankData() {
		return employeeBankData;
	}

	public void setEmployeeBankData(EmployeeBankData employeeBankData) {
		this.employeeBankData = employeeBankData;
	}

	public Employee(Integer id, String employeeFirst_Name, String employeeLast_Name, String employeeCity_Name,
			String employeeResident_Address, String employeeMarried_status, String employeeEmail,
			String employeePassword, LocalDate employeeJoining_Date, EmployeeBankData employeeBankData) {
		super();
		this.id = id;
		this.employeeFirst_Name = employeeFirst_Name;
		this.employeeLast_Name = employeeLast_Name;
		this.employeeCity_Name = employeeCity_Name;
		this.employeeResident_Address = employeeResident_Address;
		this.employeeMarried_status = employeeMarried_status;
		this.employeeEmail = employeeEmail;
		this.employeePassword = employeePassword;
		this.employeeJoining_Date = employeeJoining_Date;
		this.employeeBankData = employeeBankData;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeFirst_Name=" + employeeFirst_Name + ", employeeLast_Name="
				+ employeeLast_Name + ", employeeCity_Name=" + employeeCity_Name + ", employeeResident_Address="
				+ employeeResident_Address + ", employeeMarried_status=" + employeeMarried_status + ", employeeEmail="
				+ employeeEmail + ", employeePassword=" + employeePassword + ", employeeJoining_Date="
				+ employeeJoining_Date + ", employeeBankData=" + employeeBankData + ", getId()=" + getId()
				+ ", getEmployeeFirst_Name()=" + getEmployeeFirst_Name() + ", getEmployeeLast_Name()="
				+ getEmployeeLast_Name() + ", getEmployeeCity_Name()=" + getEmployeeCity_Name()
				+ ", getEmployeeResident_Address()=" + getEmployeeResident_Address() + ", getEmployeeMarried_status()="
				+ getEmployeeMarried_status() + ", getEmployeeEmail()=" + getEmployeeEmail()
				+ ", getEmployeePassword()=" + getEmployeePassword() + ", getEmployeeJoining_Date()="
				+ getEmployeeJoining_Date() + ", getEmployeeBankData()=" + getEmployeeBankData() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
