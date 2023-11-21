package com.rsn.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan("com.rsn.*")
public class EmployeeBankData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_Id")
	private Integer bankId;

	@Column(name = "bank_balane")
	private String bankBalance;

	@Column(name = "account_type")
	private String accountType;

	@OneToOne(cascade = CascadeType.ALL)
	private AccountPin accountPin;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(String bankBalance) {
		this.bankBalance = bankBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public AccountPin getAccountPin() {
		return accountPin;
	}

	public void setAccountPin(AccountPin accountPin) {
		this.accountPin = accountPin;
	}

	public EmployeeBankData(Integer bankId, String bankBalance, String accountType, AccountPin accountPin) {
		super();
		this.bankId = bankId;
		this.bankBalance = bankBalance;
		this.accountType = accountType;
		this.accountPin = accountPin;
	}

	public EmployeeBankData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeBankData [bankId=" + bankId + ", bankBalance=" + bankBalance + ", accountType=" + accountType
				+ ", accountPin=" + accountPin + ", getBankId()=" + getBankId() + ", getBankBalance()="
				+ getBankBalance() + ", getAccountType()=" + getAccountType() + ", getAccountPin()=" + getAccountPin()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
