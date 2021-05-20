package com.contract.service;

import java.util.List;

import com.contract.domain.Contract;
import com.contract.exception.ResourceNotFoundException;

public interface ContractService {

	public List<Contract> getAllContracts();

	public Contract getContract(Long contractId) throws ResourceNotFoundException;

	public void saveContract(Contract contract);

	public void deleteContract(Long contractId) throws ResourceNotFoundException;

	public void updateContract(Contract contract) throws ResourceNotFoundException;

	public List<String> getAllContractNumbers();

	public String getContractCount();

	public List<String> getExpiredContracts();

	public List<Contract> getExpiredContractsDetailed();

	public Contract getContractByContractNumber(String contractNumber) throws ResourceNotFoundException;

}
