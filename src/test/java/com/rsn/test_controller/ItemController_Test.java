package com.rsn.test_controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rsn.controller.ItemController;
import com.rsn.exception.InvalidInputException;
import com.rsn.exception.ItemNotFoundException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.EmployeeBankData;
import com.rsn.model.Items;
import com.rsn.serviceimpl.EmployeeBankServiceImpl;
import com.rsn.serviceimpl.ItemsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItemController_Test {

	@InjectMocks
	private ItemController mockItemController;

	@Mock
	private ItemsServiceImpl mockItemsServiceImpl;
	
	@Mock
	private EmployeeBankServiceImpl mockEmployeeBankServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void Test_createItemAPI() {
		Items items = new Items(1L, "Shampoo", "270");
		List<Items> list = new ArrayList<>();
		list.add(items);

		when(mockItemsServiceImpl.createItems(any())).thenReturn(list);
		assertNotNull(mockItemController.createItemAPI(list));

	}

	@Test
	void Test_buyItemAPI() throws ItemNotFoundException, RecordNotFoundException, InvalidInputException {

		Items items = new Items(2L, "Smartphone", "9000");
		EmployeeBankData employeeBankData = new EmployeeBankData();
		employeeBankData.setBankId(21);
		String quantity = "1";
		when(mockItemsServiceImpl.getItem(anyString())).thenReturn(items.getItemName());
		when(mockItemsServiceImpl.getItemPrice(anyString())).thenReturn(items.getItemPrice());
		when(mockEmployeeBankServiceImpl.getEmployeeBalance(anyInt())).thenReturn(items.getItemPrice());
		when(mockItemsServiceImpl.buyItems(anyString(), anyString(), anyInt())).thenReturn(items);
		assertNotNull(mockItemController.buyItemAPI("Smartphone", quantity, 21));

	}

}
