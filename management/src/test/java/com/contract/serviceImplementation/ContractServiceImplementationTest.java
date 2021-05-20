package com.contract.serviceImplementation;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
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

import com.contract.domain.Contract;
import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;
import com.contract.repository.ContractRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractServiceImplementationTest {
	@Mock
	ContractRepository contractRepository;

	@InjectMocks
	ContractServiceImplementation contractService;

	List<Contract> contractList = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Given
		Contract contract1 = new Contract(1L,
				new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),
				LocalDate.parse("2019-01-01"), LocalDate.parse("2019-10-30"), "WC", "WC-9282-198393", "Closed", null);
		Contract contract2 = new Contract(1L, null, LocalDate.parse("2015-01-01"), LocalDate.parse("2021-10-30"), "GL",
				"GL-9282-198393", "Open", null);
		contractList.add(contract1);
		contractList.add(contract2);
	}

	@Test
	public void retrievingWhenContractDoesNotExistShouldThrowException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			contractService.getContract(1L);
		});
	}

	@Test
	public void getAllContractsTest() {
		// When
		when(contractRepository.findAll()).thenReturn(contractList);
		// Then
		List<Contract> actual = contractService.getAllContracts();
		int expectedSize = 2;
		assertEquals(expectedSize, actual.size());
		verify(contractRepository, times(1)).findAll();

	}

	@Test
	public void getContractByIdTest() throws ResourceNotFoundException {
		// Given
		Optional<Contract> con = Optional.of(new Contract(1L,
				new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),
				LocalDate.parse("2019-01-01"), LocalDate.parse("2019-10-30"), "WC", "WC-9282-198393", "Closed", null));
		// When
		when(contractRepository.findById(1L)).thenReturn(con);
		// Then
		Contract contract = contractService.getContract(1L);
		assertEquals("WC", contract.getLineOfBusiness());
	}

	@Test
	public void createContractTest() {
		// Given
		Contract contract = new Contract(1L,
				new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),
				LocalDate.parse("2019-01-01"), LocalDate.parse("2019-10-30"), "WC", "WC-9282-198393", "Closed", null);
		// Then
		contractService.saveContract(contract);
		verify(contractRepository, times(1)).save(contract);
	}

	@Test
	public void getContractNamesListTest() {
		// When
		when(contractRepository.findAll()).thenReturn(contractList);
		// Then
		List<String> actual = contractService.getAllContractNumbers();
		List<String> expected = List.of("WC-9282-198393", "GL-9282-198393");
		assertEquals(2, actual.size());
		assertEquals(expected, actual);
	}

	@Test
	public void getContractCountTest() {
		// When
		when(contractRepository.findAll()).thenReturn(contractList);
		// Then
		String actualCount = contractService.getContractCount();
		String expectedCount = "Contracts Count :2";
		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void getExpiredContractsDetailedTest() {
		// When
		when(contractRepository.findAll()).thenReturn(contractList);
		// Then
		List<Contract> actualExpiredContractsDetailed = contractService.getExpiredContractsDetailed();
		String actualContractNumber = actualExpiredContractsDetailed.get(0).getContractNumber();
		List<Contract> expectedExpiredContractsDetailed = List.of(new Contract(1L,
				new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),
				LocalDate.parse("2019-01-01"), LocalDate.parse("2019-10-30"), "WC", "WC-9282-198393", "Closed", null));
		String exceptedContractNumber = expectedExpiredContractsDetailed.get(0).getContractNumber();
		assertEquals(exceptedContractNumber, actualContractNumber);
	}

	@Test
	public void getExpiredContractsTest() {
		// When
		when(contractRepository.findAll()).thenReturn(contractList);
		// Then
		List<String> actualExpiredContracts = contractService.getExpiredContracts();
		List<String> expectedExpiredContracts = List.of("WC-9282-198393");
		assertEquals(expectedExpiredContracts, actualExpiredContracts);

	}

}
