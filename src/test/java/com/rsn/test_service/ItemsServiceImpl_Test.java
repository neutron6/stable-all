package com.rsn.test_service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rsn.exception.ItemNotFoundException;
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
	void test_buyItems() throws ItemNotFoundException {
		EmployeeBankData employeeBankData = new EmployeeBankData();
		employeeBankData.setBankBalance("100"); // initial bank balance

		Items items = new Items();
		items.setItemName("TestItem");
		items.setItemPrice("10"); // item price

		// Set up repository mocks
		when(employeeBankDataRepo.findById(anyInt())).thenReturn(Optional.of(employeeBankData));
		when(itemsRepo.findByItemName("TestItem")).thenReturn(Optional.of(items));
		when(employeeBankDataRepo.save(any(EmployeeBankData.class))).thenReturn(employeeBankData);
		when(itemsRepo.save(any(Items.class))).thenReturn(items);

		// Call the method
		Items result = itemsServiceImpl.buyItems("TestItem", "2", 1);

		// Verify interactions and assert the result
		verify(employeeBankDataRepo).findById(1);
		verify(itemsRepo).findByItemName("TestItem");
		verify(employeeBankDataRepo).save(argThat(emp -> {
			assertEquals("80", employeeBankData.getBankBalance()); // expected bank balance after buying 2 items
			return true;
		}));
		verify(itemsRepo).save(items);

		assertEquals(items, result);

	}

}
