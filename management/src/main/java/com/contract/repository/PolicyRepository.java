package com.contract.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contract.domain.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
	Optional<Policy> findByPolicyNumber(String policyNumber);
}
