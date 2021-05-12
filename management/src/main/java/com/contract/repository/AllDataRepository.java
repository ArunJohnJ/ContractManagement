package com.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contract.domain.AllData;

@Repository
public interface AllDataRepository extends JpaRepository<AllData,Long>{

}
