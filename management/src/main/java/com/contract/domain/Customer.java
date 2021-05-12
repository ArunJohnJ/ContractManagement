package com.contract.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "customerId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<Contract> contracts = new ArrayList<>();

	public Customer() {
	}

	public Customer(Long customerId, String accountNumber, String customerName, String customerCode,
			String billingContactPhoneNumber, String billingContactEmail, List<Contract> contracts) {
		super();
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.customerCode = customerCode;
		this.billingContactPhoneNumber = billingContactPhoneNumber;
		this.billingContactEmail = billingContactEmail;
		this.contracts = contracts;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", accountNumber=" + accountNumber + ", customerName="
				+ customerName + ", customerCode=" + customerCode + ", billingContactPhoneNumber="
				+ billingContactPhoneNumber + ", billingContactEmail=" + billingContactEmail + "]";
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

//	public void addContract(Contract contract) {
//		System.out.println("inside add contract");
//		this.contracts.add(contract);
//		contract.setCustomer(this);
//	}
//	public void removeContract(Contract contract) {
//		contract.setCustomer(null);
//		this.contracts.remove(contract);
//	}
}
