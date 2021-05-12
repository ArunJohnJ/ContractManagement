package com.contract.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.domain.Contract;
import com.contract.exception.ResourceNotFoundException;
import com.contract.repository.ContractRepository;
import com.contract.service.ContractService;

@Service
public class ContractServiceImplementation implements ContractService {

	@Autowired
	private ContractRepository contractRepository;

	@Override
	public List<Contract> getAllContracts() {
		return contractRepository.findAll();
	}

	@Override
	public Contract getContract(Long contractId) throws ResourceNotFoundException {
		Optional<Contract> contract = contractRepository.findById(contractId);
		return contract
				.orElseThrow(() -> new ResourceNotFoundException("Contract not found for this id-- " + contractId));
	}

	@Override
	public void saveContract(Contract contract) {
		contractRepository.save(contract);
	}

	@Override
	public void deleteContract(Long contractId) throws ResourceNotFoundException {
		Optional<Contract> contract = contractRepository.findById(contractId);
		if (contract.isPresent()) {
			contractRepository.deleteById(contractId);
		}
	}

	@Override
	public void updateContract(Contract contract) throws ResourceNotFoundException {
		contractRepository.save(contract);
	}

	@Override
	public List<String> getAllContractNumbers() {
		return contractRepository.findAll().stream().map(Contract::getContractNumber).distinct()
				.collect(Collectors.toList());
	}

	@Override
	public String getContractCount() {
		long contractsCount = contractRepository.findAll().stream().map(x -> x.getContractNumber()).distinct().count();
		return "Contracts Count :" + contractsCount;
	}

	@Override
	public Map<String, List<Contract>> getExpiredContractsDetailed() {
		return contractRepository.findAll().stream().filter(x -> (x.getExpirationDate().compareTo(LocalDate.now())) < 0)
				.distinct().collect(Collectors.groupingBy(Contract::getContractNumber, Collectors.toList()));
	}

	@Override
	public List<String> getExpiredContracts() {
		return contractRepository.findAll().stream().filter(x -> (x.getExpirationDate().compareTo(LocalDate.now())) < 0)
				.map(Contract::getContractNumber).distinct().collect(Collectors.toList());
	}

}
