package com.rsn.test_service;

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

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Lic;
import com.rsn.repository.LicRepo;
import com.rsn.serviceimpl.LicServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LicServiceImpl_Test {

	@InjectMocks
	private LicServiceImpl mockLicServiceImpl;
	
	@Mock
	private LicRepo mockLicRepo;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_createLicId() {
		Lic lic=new Lic(2, "Bima",LocalDate.now(), LocalDate.now(), "Monthly", "24 Months", false);
		when(mockLicRepo.save(lic)).thenReturn(lic);
		assertNotNull(mockLicServiceImpl.createLicId(lic));
	}
	
	@Test
	public void test_getLicById() throws RecordNotFoundException {
		Lic lic=new Lic(2, "Bima",LocalDate.now(), LocalDate.now(), "Monthly", "24 Months", false);
		List<Lic> list=new ArrayList<>();
		list.add(lic);
		
		when(mockLicRepo.findByLicId(anyInt())).thenReturn(list);
		assertNotNull(mockLicServiceImpl.getLicById(anyInt()));
	}
}
