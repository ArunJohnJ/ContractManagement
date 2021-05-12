package com.contract.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.domain.Policy;
import com.contract.exception.ResourceNotFoundException;
import com.contract.repository.PolicyRepository;
import com.contract.service.PolicyService;

@Service
public class PolicyServiceImplementation implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;

	@Override
	public List<Policy> getAllPolicys() {
		return policyRepository.findAll();
	}

	@Override
	public Policy getPolicy(Long policyId) throws ResourceNotFoundException {
		Optional<Policy> policy = policyRepository.findById(policyId);
		return policy.orElseThrow(() -> new ResourceNotFoundException("policy not found for this id-- " + policyId));
	}

	@Override
	public void savePolicy(Policy policy) {
		policyRepository.save(policy);
	}

	@Override
	public void deletePolicy(Long policyId) throws ResourceNotFoundException {
		Optional<Policy> policy = policyRepository.findById(policyId);
		if (policy.isPresent()) {
			policyRepository.deleteById(policyId);
		}
	}

	@Override
	public void updatePolicy(Policy policy) throws ResourceNotFoundException {
		policyRepository.save(policy);
	}

	@Override
	public String getPolicyCount() {
		long policyCount = policyRepository.findAll().stream().map(x -> x.getPolicyNumber()).distinct().count();
		return "Policy Count :" + policyCount;
	}

	@Override
	public List<String> getAllPolicyNumbers() {
		List<String> policyNumbers = policyRepository.findAll().stream().map(Policy::getPolicyNumber).distinct()
				.collect(Collectors.toList());
		return policyNumbers;
	}

	@Override
	public Map<String, List<Policy>> getExpiredPoliciesDetailed() {
		return policyRepository.findAll().stream().filter(x -> (x.getExpirationDate().compareTo(LocalDate.now())) < 0)
				.distinct().collect(Collectors.groupingBy(Policy::getPolicyNumber, Collectors.toList()));
	}

	@Override
	public List<String> getExpiredPolicies() {
		return policyRepository.findAll().stream().filter(x -> (x.getExpirationDate().compareTo(LocalDate.now())) < 0)
				.map(Policy::getPolicyNumber).distinct().collect(Collectors.toList());
	}
}
