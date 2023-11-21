package com.rsn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan("com.rsn.*")
public class AccountPin {

	@Id
	@Column(name = "account_pin")
	private String pin;

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "AccountPin [pin=" + pin + ", getPin()=" + getPin() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public AccountPin() {
		super();
		// TODO Auto-generated constructor stub
	}

}
