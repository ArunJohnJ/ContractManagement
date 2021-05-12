package com.contract.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "contract")
public class Contract {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contractId;

	@ManyToOne
	@JoinColumn(name = "customerIdFk")
	@JsonBackReference
	private Customer customer;

	@Column(name = "effectiveDate")
	private LocalDate effectiveDate;

	@Column(name = "expirationDate")
	private LocalDate expirationDate;

	@Column(name = "lineOfBusiness")
	private String lineOfBusiness;

	@Column(name = "contractNumber")
	private String contractNumber;

	@Column(name = "claimType")
	private String claimType;

	@OneToMany(mappedBy = "contract")	
	@JsonManagedReference
	List<Policy> policies = new ArrayList<>();

	public Contract(Long contractId, Customer customer, LocalDate effectiveDate, LocalDate expirationDate,
			String lineOfBusiness, String contractNumber, String claimType, List<Policy> policies) {
		super();
		this.contractId = contractId;
		this.customer = customer;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
		this.lineOfBusiness = lineOfBusiness;
		this.contractNumber = contractNumber;
		this.claimType = claimType;
		this.policies = policies;
	}

	public Contract() {
		super();
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

//	public void addPolicy(Policy policy) {
//		this.policies.add(policy);
//		policy.setContract(this);
//	}
//
//	public void removePolicy(Policy policy) {
//		policy.setContract(null);
//		this.policies.remove(policy);
//	}

	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", customer=" + customer + ", effectiveDate=" + effectiveDate
				+ ", expirationDate=" + expirationDate + ", lineOfBusiness=" + lineOfBusiness + ", contractNumber="
				+ contractNumber + ", claimType=" + claimType + ", policies=" + policies + "]";
	}

}
