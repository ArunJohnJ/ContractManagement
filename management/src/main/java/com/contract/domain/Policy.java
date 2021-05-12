package com.contract.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "policy")
public class Policy {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;

	@ManyToOne
	@JoinColumn(name = "contractIdFk")
	@JsonBackReference
	private Contract contract;

	@Column
	private String policyNumber;

	@Column(name = "effectiveDate")
	private LocalDate effectiveDate;

	@Column(name = "expirationDate")
	private LocalDate expirationDate;

	@Column
	private String policyInsuranceCompany;

	@Column
	private Double policyLimit;

	@Column
	private String OccurenceLimitType;

	@Column(length = 1000)
	private String remark;

	public Policy() {
		super();
	}

	public Policy(Long policyId, Contract contract, String policyNumber, LocalDate effectiveDate,
			LocalDate expirationDate, String policyInsuranceCompany, Double policyLimit, String occurenceLimitType,
			String remark) {
		super();
		this.policyId = policyId;
		this.contract = contract;
		this.policyNumber = policyNumber;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
		this.policyInsuranceCompany = policyInsuranceCompany;
		this.policyLimit = policyLimit;
		this.OccurenceLimitType = occurenceLimitType;
		this.remark = remark;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
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

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", contract=" + contract + ", policyNumber=" + policyNumber
				+ ", effectiveDate=" + effectiveDate + ", expirationDate=" + expirationDate
				+ ", policyInsuranceCompany=" + policyInsuranceCompany + ", policyLimit=" + policyLimit
				+ ", OccurenceLimitType=" + OccurenceLimitType + ", remark=" + remark + "]";
	}

}
