package com.contract.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.domain.AllData;
import com.contract.repository.AllDataRepository;
import com.contract.repository.ContractRepository;
import com.contract.repository.CustomerRepository;
import com.contract.repository.PolicyRepository;
import com.contract.service.AllDataService;

@Service
public class AllDataServiceImplementation implements AllDataService {

	@Autowired
	private AllDataRepository allDataRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PolicyRepository policyRepository;

	@Override
	public List<AllData> getAllData() {
		return allDataRepository.findAll();
	}

	@Override
	public List<Long> getallCount() {
		long contractsCount = contractRepository.findAll().stream().map(x -> x.getContractNumber()).distinct().count();
		long customerCount = customerRepository.findAll().stream().map(x -> x.getCustomerCode()).distinct().count();
		long policyCount = policyRepository.findAll().stream().map(x -> x.getPolicyNumber()).distinct().count();
		return List.of(contractsCount, customerCount, policyCount);
	}
}
