package com.contract.controller;

import java.util.List;
import java.util.Map;

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

import com.contract.domain.Contract;
import com.contract.exception.ResourceNotFoundException;
import com.contract.service.ContractService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;

	@GetMapping("/all")
	public List<Contract> getAllContracts() {
		return contractService.getAllContracts();
	}

	@GetMapping("/allContractNumbers")
	public List<String> getAllContractNumbers() {
		return contractService.getAllContractNumbers();
	}

	@GetMapping("/expired")
	public List<String> getExpiredContracts() {
		return contractService.getExpiredContracts();
	}
	
	@GetMapping("/expiredDetailed")
	public Map<String, List<Contract>> getExpiredContractsDetailed() {
		return contractService.getExpiredContractsDetailed();
	}

	@GetMapping("/count")
	public String getContractCount() {
		return contractService.getContractCount();
	}

	@GetMapping("/{id}")
	public Contract getContractById(@PathVariable(value = "id") Long contractId) throws ResourceNotFoundException {
		return contractService.getContract(contractId);
	}

	@PostMapping("/create")
	public void createContract(@RequestBody Contract contract) {
		contractService.saveContract(contract);
	}

	@PutMapping("/{id}")
	public void updateContract(@PathVariable(value = "id") Long contractId, @RequestBody Contract contract)
			throws ResourceNotFoundException {
		contractService.getContract(contractId);
		contractService.updateContract(contract);
	}

	@DeleteMapping("/{id}")
	public void deleteContract(@PathVariable(value = "id") Long contractId) throws ResourceNotFoundException {
		contractService.deleteContract(contractId);
	}

}
