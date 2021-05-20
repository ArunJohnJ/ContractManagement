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

import com.contract.domain.Contract;
import com.contract.domain.Customer;
import com.contract.exception.ResourceNotFoundException;
import com.contract.service.ContractService;
import com.contract.service.CustomerService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/all")
	public String getAllContracts(Model model) {
		List<Contract> allContracts = contractService.getAllContracts();
		model.addAttribute("allContracts", allContracts);

		return "contracts";
	}

	@GetMapping("/add")
	public String showAddContract(Contract contract, Model model) {
		List<Customer> allCustomers = customerService.getAllCustomers();
		model.addAttribute("allCustomers", allCustomers);
		return "add-contract";
	}

	@PostMapping("/create")
	public String createContract(@Valid Contract contract, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Customer> allCustomers = customerService.getAllCustomers();
			model.addAttribute("allCustomers", allCustomers);
			return "add-contract";
		}
		if (contract.getCustomer().getCustomerId() == null) {
			contract.setCustomer(null);
		}
		contractService.saveContract(contract);
		return "redirect:/contract/all";
	}

	@GetMapping("/edit/{contractId}")
	public String getContractById(@PathVariable(value = "contractId") Long contractId, Model model)
			throws ResourceNotFoundException {
		Contract contract = contractService.getContract(contractId);
		model.addAttribute("contract", contract);
		List<Customer> allCustomers = customerService.getAllCustomers();
		model.addAttribute("allCustomers", allCustomers);
		return "update-contract";
	}

	@GetMapping("/number/{contractNumber}")
	public String getContractView(@PathVariable(value = "contractNumber") String contractNumber, Model model)
			throws ResourceNotFoundException {
		Contract contract = contractService.getContractByContractNumber(contractNumber);
		model.addAttribute("contract", contract);
		return "view-contract";
	}

	@PostMapping("/update/{contractId}")
	public String updateContract(@PathVariable(value = "contractId") Long contractId, Model model,
			@Valid Contract contract, BindingResult result) throws ResourceNotFoundException {
		if (result.hasErrors()) {
			contract.setContractId(contractId);
			return "update-contract";
		}
		if (contract.getCustomer().getCustomerId() == null) {
			contract.setCustomer(null);
		}
		contractService.updateContract(contract);
		return "redirect:/contract/all";
	}

	@GetMapping("/delete/{contractId}")
	public String deleteContract(@PathVariable(value = "contractId") Long contractId) throws ResourceNotFoundException {
		contractService.deleteContract(contractId);
		return "redirect:/contract/all";
	}

	@GetMapping("/moreInfo")
	public String getContractMoreInfo(Model model) {
		List<String> allContractNumbers = contractService.getAllContractNumbers();
		model.addAttribute("allContractNumbers", allContractNumbers);

		String contractCount = contractService.getContractCount();
		model.addAttribute("contractCount", contractCount);

		List<Contract> expiredContractsDetailed = contractService.getExpiredContractsDetailed();
		model.addAttribute("expiredContractsDetailed", expiredContractsDetailed);

		List<String> expiredContracts = contractService.getExpiredContracts();
		model.addAttribute("expiredContracts", expiredContracts);

		return "contract-more-info";
	}

}
