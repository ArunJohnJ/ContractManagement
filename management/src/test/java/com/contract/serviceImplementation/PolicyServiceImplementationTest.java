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
import com.contract.domain.Policy;
import com.contract.exception.ResourceNotFoundException;
import com.contract.repository.PolicyRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceImplementationTest {
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	PolicyRepository policyRepository;

	@InjectMocks
	PolicyServiceImplementation policyService;

	@Test
	public void retrievingWhenPolicyDoesNotExistShouldThrowException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			policyService.getPolicy(1L);
		});
	}

	@Test
	public void getAllPolicysTest() {
		// Given
		List<Policy> policyList = new ArrayList<>();
		Policy policy1=new Policy(1L,new Contract(),"POL-1727",LocalDate.parse("2021-01-20"),LocalDate.parse("2021-10-20"),"AIG",100.3,"SIR","None");
		Policy policy2 = new Policy(2L,new Contract(1L, new Customer(2L, "1721", "Kesav", "WEL-11", "7162971399", "corp@insurance.com", null),LocalDate.parse("2019-01-01"),LocalDate.parse("2019-10-30"),"WC","WC-9282-198393","Closed", null),"POL-1827",LocalDate.parse("2019-09-11"),LocalDate.parse("2021-10-31"),"AIG",1000.0,"SIR","None");
		policyList.add(policy1);
		policyList.add(policy2);
		// when
		when(policyRepository.findAll()).thenReturn(policyList);
		// then
		List<Policy> actual = policyService.getAllPolicys();
		System.out.println("policy");
		System.out.println(actual.toString());
		assertEquals(2, actual.size());
		verify(policyRepository, times(1)).findAll();

	}

	@Test
	public void getPolicyByIdTest() throws ResourceNotFoundException {
		Optional<Policy> con = Optional.of(new Policy(1L,new Contract(),"POL-1727",LocalDate.parse("2021-01-20"),LocalDate.parse("2021-10-20"),"AIG",100.3,"SIR","None"));
		when(policyRepository.findById(1L))
				.thenReturn(con);
		Policy policy = policyService.getPolicy(1L);
		assertEquals("POL-1727", policy.getPolicyNumber());
	}

	@Test
	public void createPolicyTest() {
		Policy policy = new Policy(1L,new Contract(),"POL-1727",LocalDate.parse("2021-01-20"),LocalDate.parse("2021-10-20"),"AIG",100.3,"SIR","None");
		policyService.savePolicy(policy);
		verify(policyRepository, times(1)).save(policy);
	}

	@Test
	public void getPolicyNamesListTest() {
		policyService.getAllPolicyNumbers();
		verify(policyRepository, times(1)).findAll();
		verify(policyRepository.findAll(), times(1)).stream();
	}

	@Test
	public void getPolicyCountTest() {
		policyService.getPolicyCount();
		verify(policyRepository, times(1)).findAll();
		verify(policyRepository.findAll(), times(1)).stream();
	}
}
