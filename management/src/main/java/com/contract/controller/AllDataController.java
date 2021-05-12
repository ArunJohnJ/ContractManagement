package com.contract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contract.domain.AllData;
import com.contract.service.AllDataService;

@RestController
@RequestMapping("/allData")
public class AllDataController {

	@Autowired
	private AllDataService allDataService;

	@GetMapping("/")
	public List<AllData> getAllData() {
		return allDataService.getAllData();
	}

	@GetMapping("/count")
	public String getAllCount() {
		return allDataService.getallCount();
	}
}
