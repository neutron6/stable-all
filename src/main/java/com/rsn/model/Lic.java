package com.rsn.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan("com.rsn.*")
public class Lic {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "lic_Id")
	private Integer licId;

	@Column(name = "pollicy_Name")
	private String policyName;

	@Temporal(TemporalType.DATE)
	@Column(name = "issue_Date")
	private Date issueDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_Date")
	private Date expiryDate;

	@Column(name = "policy_Premium")
	private String policyPremium; // It is typically paid regularly (e.g., monthly, quarterly, or annually) to
									// keep the insurance coverage active.

	@Column(name = "policy_Tenure")
	private String policyTenure; // Insurance policies can have various tenures, such as 5 years, 10 years, 20
									// years, or even longer, depending on the type of policy and the terms chosen
									// by the policyholder.

	@Column(name = "status")
	private boolean status;

	public Integer getLicId() {
		return licId;
	}

	public void setLicId(Integer licId) {
		this.licId = licId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(String policyPremium) {
		this.policyPremium = policyPremium;
	}

	public String getPolicyTenure() {
		return policyTenure;
	}

	public void setPolicyTenure(String policyTenure) {
		this.policyTenure = policyTenure;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Lic(Integer licId, String policyName, Date issueDate, Date expiryDate, String policyPremium,
			String policyTenure, boolean status) {
		super();
		this.licId = licId;
		this.policyName = policyName;
		this.issueDate = issueDate;
		this.expiryDate = expiryDate;
		this.policyPremium = policyPremium;
		this.policyTenure = policyTenure;
		this.status = status;
	}

	public Lic() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Lic [licId=" + licId + ", policyName=" + policyName + ", issueDate=" + issueDate + ", expiryDate="
				+ expiryDate + ", policyPremium=" + policyPremium + ", policyTenure=" + policyTenure + ", status="
				+ status + ", getLicId()=" + getLicId() + ", getPolicyName()=" + getPolicyName() + ", getIssueDate()="
				+ getIssueDate() + ", getExpiryDate()=" + getExpiryDate() + ", getPolicyPremium()=" + getPolicyPremium()
				+ ", getPolicyTenure()=" + getPolicyTenure() + ", isStatus()=" + isStatus() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
}
