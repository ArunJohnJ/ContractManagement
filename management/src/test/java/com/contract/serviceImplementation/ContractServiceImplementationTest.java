package com.contract.serviceImplementation;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
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

import com.contract.domain.Contract;
import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;
import com.contract.repository.ContractRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractServiceImplementationTest {
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	ContractRepository contractRepository;

	@InjectMocks
	ContractServiceImplementation contractService;

	@Test
	public void retrievingWhenContractDoesNotExistShouldThrowException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			contractService.getContract(1L);
		});
	}

	@Test
	public void getAllContractsTest() {
		// Given
		List<Contract> contractList = new ArrayList<>();
		Contract contract1 = new Contract(1L, new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),LocalDate.parse("2019-01-01"),LocalDate.parse("2019-10-30"),"WC","WC-9282-198393","Closed", null);
		Contract contract2 = new Contract(1L, null,LocalDate.parse("2015-01-01"),LocalDate.parse("2021-10-30"),"GL","GL-9282-198393","Open", null);
		contractList.add(contract1);
		contractList.add(contract2);
		// when
		when(contractRepository.findAll()).thenReturn(contractList);
		// then
		List<Contract> actual = contractService.getAllContracts();
		System.out.println("contract");
		System.out.println(actual.toString());
		assertEquals(2, actual.size());
		verify(contractRepository, times(1)).findAll();

	}

	@Test
	public void getContractByIdTest() throws ResourceNotFoundException {
		Optional<Contract> con = Optional.of(new Contract(1L, new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),LocalDate.parse("2019-01-01"),LocalDate.parse("2019-10-30"),"WC","WC-9282-198393","Closed", null));
		when(contractRepository.findById(1L))
				.thenReturn(con);
		Contract contract = contractService.getContract(1L);
		assertEquals("WC", contract.getLineOfBusiness());
	}

	@Test
	public void createContractTest() {
		Contract contract = new Contract(1L, new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),LocalDate.parse("2019-01-01"),LocalDate.parse("2019-10-30"),"WC","WC-9282-198393","Closed", null);
		contractService.saveContract(contract);
		verify(contractRepository, times(1)).save(contract);
	}

	@Test
	public void getContractNamesListTest() {
		contractService.getAllContractNumbers();
		verify(contractRepository, times(1)).findAll();
		verify(contractRepository.findAll(), times(1)).stream();
	}

	@Test
	public void getContractCountTest() {
		contractService.getContractCount();
		verify(contractRepository, times(1)).findAll();
		verify(contractRepository.findAll(), times(1)).stream();
	}
}
