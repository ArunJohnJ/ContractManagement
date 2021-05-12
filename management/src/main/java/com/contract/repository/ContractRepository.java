package com.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contract.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>{

}
