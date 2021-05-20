package com.contract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contract.domain.AllData;
import com.contract.service.AllDataService;

@Controller
@RequestMapping
public class AllDataController {

	@Autowired
	private AllDataService allDataService;

	@GetMapping("/")
	public String getAllData(Model model) {
		List<AllData> allData = allDataService.getAllData();
		model.addAttribute("allData", allData);
		return "all-data";
	}

	@GetMapping("/moreInfo")
	public String getAllCount(Model model) {
		List<Long> allCount = allDataService.getallCount();
		model.addAttribute("allCount", allCount);
		return "alldata-more-info";
	}
}
