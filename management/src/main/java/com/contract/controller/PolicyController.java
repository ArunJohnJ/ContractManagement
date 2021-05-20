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
import com.contract.domain.Policy;
import com.contract.exception.ResourceNotFoundException;
import com.contract.service.ContractService;
import com.contract.service.PolicyService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/policy")
public class PolicyController {
	@Autowired
	private PolicyService policyService;

	@Autowired
	private ContractService contractService;

	@GetMapping("/moreInfo")
	public String getPolicyMoreInfo(Model model) {
		List<String> allPolicyNumbers = policyService.getAllPolicyNumbers();
		model.addAttribute("allPolicyNumbers", allPolicyNumbers);

		String policyCount = policyService.getPolicyCount();
		model.addAttribute("policyCount", policyCount);

		List<Policy> expiredPoliciesDetailed = policyService.getExpiredPoliciesDetailed();
		model.addAttribute("expiredPoliciesDetailed", expiredPoliciesDetailed);

		List<String> expiredPolicies = policyService.getExpiredPolicies();
		model.addAttribute("expiredPolicies", expiredPolicies);

		return "policy-more-info";
	}

	@GetMapping("/all")
	public String getAllPolicys(Model model) {
		List<Policy> allPolicies = policyService.getAllPolicies();
		model.addAttribute("allPolicies", allPolicies);
		return "policies";
	}

	@GetMapping("/add")
	public String showAddPolicy(Policy policy, Model model) {
		List<Contract> allContracts = contractService.getAllContracts();
		model.addAttribute("allContracts", allContracts);
		return "add-policy";
	}

	@PostMapping("/create")
	public String createPolicy(@Valid Policy policy, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Contract> allContracts = contractService.getAllContracts();
			model.addAttribute("allContracts", allContracts);
			return "add-policy";
		}
		if (policy.getContract().getContractId() == null) {
			policy.setContract(null);
		}
		policyService.savePolicy(policy);
		return "redirect:/policy/all";
	}

	@GetMapping("/edit/{policyId}")
	public String getPolicyById(@PathVariable(value = "policyId") Long policyId, Model model)
			throws ResourceNotFoundException {
		Policy policy = policyService.getPolicy(policyId);
		model.addAttribute("policy", policy);
		List<Contract> allContracts = contractService.getAllContracts();
		model.addAttribute("allContracts", allContracts);
		return "update-policy";
	}

	@GetMapping("/number/{policyNumber}")
	public String getPolicyByPolicyNumber(@PathVariable(value = "policyNumber") String policyNumber, Model model)
			throws ResourceNotFoundException {
		Policy policy = policyService.getPolicyByPolicyNumber(policyNumber);
		model.addAttribute("policy", policy);
		return "view-policy";
	}

	@PostMapping("/update/{policyId}")
	public String updatePolicy(@PathVariable(value = "policyId") Long policyId, @Valid Policy policy,
			BindingResult result, Model model) throws ResourceNotFoundException {
		if (result.hasErrors()) {
			policy.setPolicyId(policyId);
			return "update-policy";
		}
		if (policy.getContract().getContractId() == null) {
			policy.setContract(null);
		}
		policyService.updatePolicy(policy);
		return "redirect:/policy/all";
	}

	@GetMapping("/delete/{policyId}")
	public String deletePolicy(@PathVariable(value = "policyId") Long policyId) throws ResourceNotFoundException {
		policyService.deletePolicy(policyId);
		return "redirect:/policy/all";
	}

}
