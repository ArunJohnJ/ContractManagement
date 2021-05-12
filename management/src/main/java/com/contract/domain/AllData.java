package com.contract.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="allData")
public class AllData {
	
	//********contract fields**********
	@Id
	@Column
	private Long contractId;

	@Column(name = "contractEffectiveDate")
	@DateTimeFormat(pattern= "yyyy-M-d")
	private LocalDate ContractEffectiveDate;

	@Column(name = "contractExpirationDate")
	@DateTimeFormat(pattern= "yyyy-M-d")
	private LocalDate contractExpirationDate;

	@Column(name = "lineOfBusiness")
	private String lineOfBusiness;

	@Column(name = "contractNumber")
	private String contractNumber;

	@Column(name = "claimType")
	private String claimType;
	
	//********customer fields**********
	@Column(name="accountNumber")
	private String accountNumber;

	@Column(name="customerName")
	private String customerName;

	@Column(name="customerCode")
	private String customerCode;

	@Column(name="billingContactPhoneNumber")
	private String billingContactPhoneNumber;

	@Column(name="billingContactEmail")
	private String billingContactEmail;
	
	//********policy fields**********
	@Column
	private String policyNumber;

	@Column(name = "policyEffectiveDate")
	@DateTimeFormat(pattern= "yyyy-M-d")
	private LocalDate policyEffectiveDate;

	@Column(name = "policyExpirationDate")
	@DateTimeFormat(pattern= "yyyy-M-d")
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

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public LocalDate getContractEffectiveDate() {
		return ContractEffectiveDate;
	}

	public void setContractEffectiveDate(LocalDate contractEffectiveDate) {
		ContractEffectiveDate = contractEffectiveDate;
	}

	public LocalDate getContractExpirationDate() {
		return contractExpirationDate;
	}

	public void setContractExpirationDate(LocalDate contractExpirationDate) {
		this.contractExpirationDate = contractExpirationDate;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getBillingContactPhoneNumber() {
		return billingContactPhoneNumber;
	}

	public void setBillingContactPhoneNumber(String billingContactPhoneNumber) {
		this.billingContactPhoneNumber = billingContactPhoneNumber;
	}

	public String getBillingContactEmail() {
		return billingContactEmail;
	}

	public void setBillingContactEmail(String billingContactEmail) {
		this.billingContactEmail = billingContactEmail;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public LocalDate getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}

	public void setPolicyEffectiveDate(LocalDate policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}

	public LocalDate getPolicyExpirationDate() {
		return policyExpirationDate;
	}

	public void setPolicyExpirationDate(LocalDate policyExpirationDate) {
		this.policyExpirationDate = policyExpirationDate;
	}

	public String getPolicyInsuranceCompany() {
		return policyInsuranceCompany;
	}

	public void setPolicyInsuranceCompany(String policyInsuranceCompany) {
		this.policyInsuranceCompany = policyInsuranceCompany;
	}

	public Double getPolicyLimit() {
		return policyLimit;
	}

	public void setPolicyLimit(Double policyLimit) {
		this.policyLimit = policyLimit;
	}

	public String getOccurenceLimitType() {
		return OccurenceLimitType;
	}

	public void setOccurenceLimitType(String occurenceLimitType) {
		OccurenceLimitType = occurenceLimitType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
