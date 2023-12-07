package com.rsn.test_controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rsn.controller.LicController;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Lic;
import com.rsn.serviceimpl.LicServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LicController_Test {
	
	@InjectMocks 
	private LicController mockLicController;
	
	@Mock
	private LicServiceImpl mockLicServiceImpl;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void test_createLicId() {
		Lic lic=new Lic(2, "Bima",LocalDate.now(), LocalDate.now(), "Monthly", "24 Months", false);
		when(mockLicServiceImpl.createLicId(lic)).thenReturn(lic);
		assertNotNull(mockLicController.createLicId(lic));
	}
	
	@Test
	void test_getByLicId() throws RecordNotFoundException {
		Lic lic=new Lic(2, "Bima",LocalDate.now(), LocalDate.now(), "Monthly", "24 Months", false);
		List<Lic> list=new ArrayList<>();
		list.add(lic);
		
		when(mockLicServiceImpl.getLicById(anyInt())).thenReturn(list);
		assertNotNull(mockLicController.getByLicId(anyInt()));
	}

}
