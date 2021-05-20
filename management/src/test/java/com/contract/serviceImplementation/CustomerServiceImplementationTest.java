package com.contract.serviceImplementation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImplementation customerService;

	List<Customer> customerList = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Given
		Customer cust1 = new Customer(1L, "1234", "John", "WEA-23", "9962981320", "abc@nnn.com", null);
		Customer cust2 = new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null);
		customerList.add(cust1);
		customerList.add(cust2);
	}

	@Test
	public void retrievingWhenUserDoesNotExistShouldThrowException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			customerService.getCustomer(1L);
		});
	}

	@Test
	public void getAllCustomersTest() {
		// when
		when(customerRepository.findAll()).thenReturn(customerList);
		// then
		List<Customer> actual = customerService.getAllCustomers();
		int expectedSize = 2;
		assertEquals(expectedSize, actual.size());
		verify(customerRepository, times(1)).findAll();

	}

	@Test
	public void getCustomerByIdTest() throws ResourceNotFoundException {
		// Given
		Optional<Customer> cust = Optional
				.of(new Customer(1L, "1234", "John", "WEA-23", "9962981320", "abc@nnn.com", null));
		// When
		when(customerRepository.findById(1L)).thenReturn(cust);
		// Then
		Customer customer = customerService.getCustomer(1L);
		assertEquals("1234", customer.getAccountNumber());
	}

	@Test
	public void createCustomerTest() {
		// Given
		Customer customer = new Customer(1L, "1234", "John", "WEA-23", "9962981320", "abc@nnn.com", null);
		// Then
		customerService.saveCustomer(customer);
		verify(customerRepository, times(1)).save(customer);
	}

	@Test
	public void getCustomerNamesListTest() {
		// When
		when(customerRepository.findAll()).thenReturn(customerList);
		// Then
		List<String> actualCustomerNames = customerService.getAllCustomerNames();
		List<String> expectedCustomerNames = List.of("John", "Kesav");
		assertEquals(expectedCustomerNames, actualCustomerNames);
	}

	@Test
	public void getCustomerCountTest() {
		// When
		when(customerRepository.findAll()).thenReturn(customerList);
		// Then
		String actualCustomerCount = customerService.getCustomerCount();
		String expectedCustomerCount = "Customer Count :2";
		assertEquals(expectedCustomerCount, actualCustomerCount);
	}
}
