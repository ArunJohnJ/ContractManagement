package com.contract.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "allData")
public class AllData {

	// ********contract fields**********
	@Id
	@Column
	private Long contractId;

	@Column(name = "contractEffectiveDate")
	@DateTimeFormat(pattern = "yyyy-M-d")
	private LocalDate ContractEffectiveDate;

	@Column(name = "contractExpirationDate")
	@DateTimeFormat(pattern = "yyyy-M-d")
	private LocalDate contractExpirationDate;

	@Column(name = "lineOfBusiness")
	private String lineOfBusiness;

	@Column(name = "contractNumber")
	private String contractNumber;

	@Column(name = "claimType")
	private String claimType;

	// ********customer fields**********
	@Column(name = "accountNumber")
	private String accountNumber;

	@Column(name = "customerName")
	private String customerName;

	@Column(name = "customerCode")
	private String customerCode;

	@Column(name = "billingContactPhoneNumber")
	private String billingContactPhoneNumber;

	@Column(name = "billingContactEmail")
	private String billingContactEmail;

	// ********policy fields**********
	@Column
	private String policyNumber;

	@Column(name = "policyEffectiveDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate policyEffectiveDate;

	@Column(name = "policyExpirationDate")
	@DateTimeFormat(pattern = "yyyy-M-d")
	private LocalDate policyExpirationDate;

	@Column
	private String policyInsuranceCompany;

	@Column
	private Double policyLimit;

	@Column
	private String OccurenceLimitType;

	@Column(length = 1000)
	private String remark;

	public Long getContractId() {
		return contractId;
	}

	public LocalDate getContractEffectiveDate() {
		return ContractEffectiveDate;
	}

	public LocalDate getContractExpirationDate() {
		return contractExpirationDate;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public String getClaimType() {
		return claimType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String getBillingContactPhoneNumber() {
		return billingContactPhoneNumber;
	}

	public String getBillingContactEmail() {
		return billingContactEmail;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public LocalDate getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}

	public LocalDate getPolicyExpirationDate() {
		return policyExpirationDate;
	}

	public String getPolicyInsuranceCompany() {
		return policyInsuranceCompany;
	}

	public Double getPolicyLimit() {
		return policyLimit;
	}

	public String getOccurenceLimitType() {
		return OccurenceLimitType;
	}

	public String getRemark() {
		return remark;
	}
}
