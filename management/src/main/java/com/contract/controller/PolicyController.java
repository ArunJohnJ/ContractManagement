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

import com.contract.domain.Policy;
import com.contract.exception.ResourceNotFoundException;
import com.contract.service.PolicyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/policy")
public class PolicyController {
	@Autowired
	private PolicyService policyService;

	@GetMapping("/all")
	public List<Policy> getAllPolicys() {
		return policyService.getAllPolicys();
	}

	@GetMapping("/count")
	public String getPolicyCount() {
		return policyService.getPolicyCount();
	}

	@GetMapping("/{id}")
	public Policy getPolicyById(@PathVariable(value = "id") Long policyId) throws ResourceNotFoundException {
		return policyService.getPolicy(policyId);
	}

	@GetMapping("/allPolicyNumbers")
	public List<String> getAllPolicyNumbers() {
		return policyService.getAllPolicyNumbers();
	}

	@GetMapping("/expiredDetailed")
	public Map<String, List<Policy>> getExpiredPoliciesDetailed() {
		return policyService.getExpiredPoliciesDetailed();
	}

	@GetMapping("/expired")
	public List<String> getExpiredPolicies() {
		return policyService.getExpiredPolicies();
	}

	@PostMapping("/create")
	public void createPolicy(@RequestBody Policy policy) {
		policyService.savePolicy(policy);
	}

	@PutMapping("/{id}")
	public void updatePolicy(@PathVariable(value = "id") Long policyId, @RequestBody Policy policy)
			throws ResourceNotFoundException {
		policyService.getPolicy(policyId);
		policyService.updatePolicy(policy);
	}

	@DeleteMapping("/{id}")
	public void deletePolicy(@PathVariable(value = "id") Long policyId) throws ResourceNotFoundException {
		policyService.deletePolicy(policyId);
	}

}
