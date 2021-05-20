package com.contract.serviceImplementation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.contract.domain.AllData;
import com.contract.repository.AllDataRepository;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AllDataServiceImplementationTest {
	@Mock
	AllDataRepository allDataRepository;

	@InjectMocks
	AllDataServiceImplementation allDataService;

	List<AllData> allDataList = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Given
		AllData allData1 = new AllData();
		AllData allData2 = new AllData();
		allDataList.add(allData1);
		allDataList.add(allData2);
	}

	@Test
	public void getAllDataTest() {
		// When
		when(allDataRepository.findAll()).thenReturn(allDataList);
		// Then
		List<AllData> actual = allDataService.getAllData();
		int expected = 2;
		assertEquals(expected, actual.size());
	}
}
