package com.contract.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;
import com.contract.service.CustomerService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/all")
	public String getAllCustomers(Model model) {
		List<Customer> allCustomers = customerService.getAllCustomers();
		model.addAttribute("allCustomers", allCustomers);
		return "customers";
	}

	@GetMapping("/add")
	public String showAddCustomer(Customer customer) {
		return "add-customer";
	}

	@PostMapping("/create")
	public String createCustomer(@Valid Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-customer";
		}
		customerService.saveCustomer(customer);
		return "redirect:/customer/all";
	}

	@GetMapping("/moreInfo")
	public String getCustomerMoreInfo(Model model) {
		List<String> allCustomerNames = customerService.getAllCustomerNames();
		model.addAttribute("allCustomerNames", allCustomerNames);

		String customerCount = customerService.getCustomerCount();
		model.addAttribute("customerCount", customerCount);

		return "customer-more-info";
	}

	@GetMapping("/edit/{customerId}")
	public String getCustomerById(@PathVariable(value = "customerId") Long customerId, Model model)
			throws ResourceNotFoundException {
		Customer customer = customerService.getCustomer(customerId);
		model.addAttribute("customer", customer);
		return "update-customer";
	}

	@GetMapping("/name/{customerName}")
	public String getCustomerByName(@PathVariable(value = "customerName") String customerName, Model model)
			throws ResourceNotFoundException {
		Customer customer = customerService.getCustomerByName(customerName);
		model.addAttribute("customer", customer);
		return "view-customer";
	}

	@PostMapping("/update/{customerId}")
	public String updateCustomer(@PathVariable(value = "customerId") Long customerId, @Valid Customer customer,
			BindingResult result, Model model) throws ResourceNotFoundException {
		if (result.hasErrors()) {
			customer.setCustomerId(customerId);
			return "update-customer";
		}
		customerService.updateCustomer(customer);
		return "redirect:/customer/all";
	}

	@GetMapping("/delete/{customerId}")
	public String deleteCustomer(@PathVariable(value = "customerId") Long customerId, Model model)
			throws ResourceNotFoundException {
		customerService.deleteCustomer(customerId);
		return "redirect:/customer/all";

	}

}
