package com.contract.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "contract")
public class Contract {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contractId;

	@ManyToOne // (cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	@JsonBackReference
	private Customer customer;

	@Column(name = "effectiveDate")
	@NotNull(message = "Effective Date is mandatory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate effectiveDate;

	@Column(name = "expirationDate")
	@NotNull(message = "Expiration Date is mandatory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDate;

	@Column(name = "lineOfBusiness")
	@NotBlank(message = "Line of Business is mandatory")
	private String lineOfBusiness;

	@Column(name = "contractNumber")
	@NotBlank(message = "Contract Number is mandatory")
	private String contractNumber;

	@Column(name = "claimType")
	@NotBlank(message = "Claim Type is mandatory")
	private String claimType;

	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY) // ,cascade = CascadeType.ALL, orphanRemoval = true)
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

	public void addPolicy(Policy policy) {
		this.policies.add(policy);
		policy.setContract(this);
	}

	public void removePolicy(Policy policy) {
		policy.setContract(null);
		this.policies.remove(policy);
	}

	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", customer=" + customer + ", effectiveDate=" + effectiveDate
				+ ", expirationDate=" + expirationDate + ", lineOfBusiness=" + lineOfBusiness + ", contractNumber="
				+ contractNumber + ", claimType=" + claimType + ", policies=" + policies + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claimType == null) ? 0 : claimType.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((contractNumber == null) ? 0 : contractNumber.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((lineOfBusiness == null) ? 0 : lineOfBusiness.hashCode());
		result = prime * result + ((policies == null) ? 0 : policies.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (claimType == null) {
			if (other.claimType != null)
				return false;
		} else if (!claimType.equals(other.claimType))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (contractNumber == null) {
			if (other.contractNumber != null)
				return false;
		} else if (!contractNumber.equals(other.contractNumber))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (lineOfBusiness == null) {
			if (other.lineOfBusiness != null)
				return false;
		} else if (!lineOfBusiness.equals(other.lineOfBusiness))
			return false;
		if (policies == null) {
			if (other.policies != null)
				return false;
		} else if (!policies.equals(other.policies))
			return false;
		return true;
	}

}
