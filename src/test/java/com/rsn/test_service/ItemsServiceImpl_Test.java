package com.rsn.test_service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rsn.model.EmployeeBankData;
import com.rsn.model.Items;
import com.rsn.repository.EmployeeBankDataRepo;
import com.rsn.repository.ItemsRepo;
import com.rsn.serviceimpl.ItemsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItemsServiceImpl_Test {

	@InjectMocks
	private ItemsServiceImpl itemsServiceImpl;

	@Mock
	private EmployeeBankDataRepo employeeBankDataRepo;

	@Mock
	private ItemsRepo itemsRepo;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_buyItems() {
		Optional<Items> items = Optional.ofNullable(new Items(1L, "cv", "45"));
		Optional<EmployeeBankData> employeeBankData = Optional.of(new EmployeeBankData(1, "4000", "savings", null));
		

		Mockito.when(employeeBankDataRepo.findById(1)).thenReturn(employeeBankData);
		Mockito.when(itemsRepo.findByItemName("cv")).thenReturn(items);
		assertNull(itemsServiceImpl.buyItems("cv", "4", 1));

	}

}
