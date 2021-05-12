package com.contract.service;

import java.util.List;

import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Customer getCustomer(Long customerId) throws ResourceNotFoundException;

	public void saveCustomer(Customer customer);

	public void deleteCustomer(Long customerId) throws ResourceNotFoundException;

	public void updateCustomer(Customer customer) throws ResourceNotFoundException;

	public String getCustomerCount();

	public List<String> getAllCustomerNames();

}
