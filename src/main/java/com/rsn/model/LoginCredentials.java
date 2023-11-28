package com.rsn.model;

public class LoginCredentials {

	private String employeeEmail;
	private String employeePassword;

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

	public LoginCredentials(String employeeEmail, String employeePassword) {
		super();
		this.employeeEmail = employeeEmail;
		this.employeePassword = employeePassword;
	}

	public LoginCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LoginCredentials [employeeEmail=" + employeeEmail + ", employeePassword=" + employeePassword
				+ ", getEmployeeEmail()=" + getEmployeeEmail() + ", getEmployeePassword()=" + getEmployeePassword()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
