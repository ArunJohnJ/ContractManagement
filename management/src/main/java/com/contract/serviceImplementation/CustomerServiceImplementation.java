package com.contract.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;
import com.contract.repository.CustomerRepository;
import com.contract.service.CustomerService;

@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(Long customerId) throws ResourceNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		return customer
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id-- " + customerId));
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) throws ResourceNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			customerRepository.deleteById(customer.get().getCustomerId());
		}

	}

	@Override
	public void updateCustomer(Customer customer) throws ResourceNotFoundException {
		customerRepository.save(customer);
	}

	@Override
	public String getCustomerCount() {
		long customerCount = customerRepository.findAll().stream().map(Customer::getCustomerCode).distinct().count();
		return "Customer Count :" + customerCount;
	}

	@Override
	public List<String> getAllCustomerNames() {
		List<Customer> allCustomers = customerRepository.findAll();
		return getOnlyNames(allCustomers);
	}

	public List<String> getOnlyNames(List<Customer> allCustomers) {
		return allCustomers.stream().map(Customer::getCustomerName).distinct().collect(Collectors.toList());
	}

}
