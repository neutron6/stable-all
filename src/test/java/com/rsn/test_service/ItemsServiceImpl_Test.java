package com.rsn.test_service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rsn.exception.InvalidInputException;
import com.rsn.exception.ItemNotFoundException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.AccountPin;
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
	void test_createItems() {
		Items items = new Items(1L, "ss", "50");
		List<Items> list = new ArrayList<>();
		list.add(items);
		when(itemsRepo.save(any())).thenReturn(list);
		assertNotNull(itemsServiceImpl.createItems(list));

	}

	@Test
	void test_buyItems() throws RecordNotFoundException, ItemNotFoundException, InvalidInputException {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		Optional<Items> items = Optional.ofNullable(new Items(1L, "ss", "50"));
		Optional<EmployeeBankData> employee = Optional
				.ofNullable(new EmployeeBankData(1, "5000", "savings", accountPin));
		when((employeeBankDataRepo.findById(anyInt()))).thenReturn(employee);
		when(itemsRepo.findByItemName(anyString())).thenReturn(items);
		when(employeeBankDataRepo.save(any(EmployeeBankData.class))).thenReturn(mock(EmployeeBankData.class));
		when(itemsRepo.save(any(Items.class))).thenReturn(mock(Items.class));

		assertNotNull(itemsServiceImpl.buyItems("ss", "1", 1));

		assertThrows(InvalidInputException.class, () -> itemsServiceImpl.buyItems(null, null, null));

		when(employeeBankDataRepo.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(RecordNotFoundException.class, () -> itemsServiceImpl.buyItems("ss", "1", 0));

	}

	@Test
	public void testBuyItemsRecordNotFound()
			throws ItemNotFoundException, RecordNotFoundException, InvalidInputException {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		Optional<Items> items = Optional.ofNullable(new Items(1L, "ss", "50"));
		Optional<EmployeeBankData> employee = Optional
				.ofNullable(new EmployeeBankData(1, "5000", "savings", accountPin));
		when((employeeBankDataRepo.findById(anyInt()))).thenReturn(employee);
		when(itemsRepo.findByItemName(anyString())).thenReturn(items);
		when(employeeBankDataRepo.save(any(EmployeeBankData.class))).thenReturn(mock(EmployeeBankData.class));
		when(itemsRepo.save(any(Items.class))).thenReturn(mock(Items.class));
		when(employeeBankDataRepo.findById(anyInt())).thenReturn(employee);

		when(itemsRepo.findByItemName("nonExistentItem")).thenReturn(Optional.empty());
		assertThrows(ItemNotFoundException.class, () -> itemsServiceImpl.buyItems("nonExistentItem", "1", 1));
	}

}
