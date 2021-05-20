package com.contract.service;

import java.util.List;

import com.contract.domain.Policy;
import com.contract.exception.ResourceNotFoundException;

public interface PolicyService {

	public List<Policy> getAllPolicies();

	public Policy getPolicy(Long policyId) throws ResourceNotFoundException;

	public Policy getPolicyByPolicyNumber(String policyNumber) throws ResourceNotFoundException;

	public void savePolicy(Policy policy);

	public void deletePolicy(Long policyId) throws ResourceNotFoundException;

	public void updatePolicy(Policy policy) throws ResourceNotFoundException;

	public String getPolicyCount();

	public List<String> getAllPolicyNumbers();

	public List<Policy> getExpiredPoliciesDetailed();

	public List<String> getExpiredPolicies();

}
