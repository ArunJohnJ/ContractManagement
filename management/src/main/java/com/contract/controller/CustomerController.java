package com.contract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;
import com.contract.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/count")
	public String getCustomerCount() {
		return customerService.getCustomerCount();
	}

	@GetMapping("/allCustomerNames")
	public List<String> getAllCustomerNames() {
		return customerService.getAllCustomerNames();
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
		return customerService.getCustomer(customerId);
	}

	@PostMapping("/create")
	public void createCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
	}

	@PutMapping("/{id}")
	public void updateCustomer(@PathVariable(value = "id") Long customerId, @RequestBody Customer customer) 
			throws ResourceNotFoundException {
		customerService.getCustomer(customerId);
		customerService.updateCustomer(customer);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException{
		customerService.deleteCustomer(customerId);
	}

}
