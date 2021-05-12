package com.contract.serviceImplementation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;
import com.contract.repository.CustomerRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplementationTest {
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImplementation customerService;

	@Test
	public void retrievingWhenUserDoesNotExistShouldThrowException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			customerService.getCustomer(1L);
		});
	}

	@Test
	public void getAllCustomersTest() {
		// Given
		List<Customer> customerList = new ArrayList<>();
		Customer cust1 = new Customer(1L, "1234", "John", "WEA-23", "9962981320", "abc@nnn.com", null);
		Customer cust2 = new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null);
		customerList.add(cust1);
		customerList.add(cust2);
		// when
		when(customerRepository.findAll()).thenReturn(customerList);
		// then
		List<Customer> actual = customerService.getAllCustomers();
		assertEquals(2, actual.size());
		verify(customerRepository, times(1)).findAll();

	}

	@Test
	public void getCustomerByIdTest() throws ResourceNotFoundException {
		Optional<Customer> cust = Optional
				.of(new Customer(1L, "1234", "John", "WEA-23", "9962981320", "abc@nnn.com", null));
		when(customerRepository.findById(1L)).thenReturn(cust);
		Customer customer = customerService.getCustomer(1L);
		assertEquals("1234", customer.getAccountNumber());
	}

	@Test
	public void createCustomerTest() {
		Customer customer = new Customer(1L, "1234", "John", "WEA-23", "9962981320", "abc@nnn.com", null);
		customerService.saveCustomer(customer);
		verify(customerRepository, times(1)).save(customer);
	}

	@Test
	public void getCustomerNamesListTest() {
		List<String> customerNames = List.of("Arun", "Bergin");
		when(customerService.getAllCustomerNames()).thenReturn(customerNames);
		customerService.getAllCustomerNames();
		verify(customerRepository, times(2)).findAll();
		verify(customerRepository.findAll(), times(2)).stream();
	}

	@Test
	public void getCustomerCountTest() {
		customerService.getCustomerCount();
		verify(customerRepository, times(1)).findAll();
		verify(customerRepository.findAll(), times(1)).stream();
	}
}
