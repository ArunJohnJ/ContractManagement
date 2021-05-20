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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "policy")
public class Policy {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;

	@ManyToOne // (cascade = CascadeType.ALL)
	@JoinColumn(name = "contractId")
	@JsonBackReference
	private Contract contract;

	@Column
	@NotBlank(message = "Policy Number is mandatory")
	private String policyNumber;

	@Column(name = "effectiveDate")
	@NotNull(message = "Effective Date is mandatory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate effectiveDate;

	@Column(name = "expirationDate")
	@NotNull(message = "Expiration Date is mandatory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDate;

	@Column
	@NotBlank(message = "Policy Issuing company is mandatory")
	private String policyInsuranceCompany;

	@Column
	@NotNull(message = "Policy Limit is mandatory")
	private Double policyLimit;

	@Column
	@NotBlank(message = "Occurence Limit type is mandatory")
	private String OccurenceLimitType;

	@Column(length = 1000)
	private String remark;

//	@Column
//	private Long contractId;
//	private Long idContract=contract.getContractId();

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

//	public void setContractId(Long id) {
//		this.contract.getContractId().get
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((OccurenceLimitType == null) ? 0 : OccurenceLimitType.hashCode());
		result = prime * result + ((contract == null) ? 0 : contract.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((policyId == null) ? 0 : policyId.hashCode());
		result = prime * result + ((policyInsuranceCompany == null) ? 0 : policyInsuranceCompany.hashCode());
		result = prime * result + ((policyLimit == null) ? 0 : policyLimit.hashCode());
		result = prime * result + ((policyNumber == null) ? 0 : policyNumber.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		Policy other = (Policy) obj;
		if (OccurenceLimitType == null) {
			if (other.OccurenceLimitType != null)
				return false;
		} else if (!OccurenceLimitType.equals(other.OccurenceLimitType))
			return false;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
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
		if (policyId == null) {
			if (other.policyId != null)
				return false;
		} else if (!policyId.equals(other.policyId))
			return false;
		if (policyInsuranceCompany == null) {
			if (other.policyInsuranceCompany != null)
				return false;
		} else if (!policyInsuranceCompany.equals(other.policyInsuranceCompany))
			return false;
		if (policyLimit == null) {
			if (other.policyLimit != null)
				return false;
		} else if (!policyLimit.equals(other.policyLimit))
			return false;
		if (policyNumber == null) {
			if (other.policyNumber != null)
				return false;
		} else if (!policyNumber.equals(other.policyNumber))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}

}
