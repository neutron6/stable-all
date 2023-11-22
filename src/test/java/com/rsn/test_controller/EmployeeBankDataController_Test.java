package com.rsn.test_controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.rsn.controller.EmployeeBankDataController;
import com.rsn.exception.InvalidAccountPinException;
import com.rsn.exception.OutOfLimitException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.AccountPin;
import com.rsn.model.EmployeeBankData;
import com.rsn.serviceimpl.EmployeeBankServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeBankDataController_Test {

	@InjectMocks
	private EmployeeBankDataController employeeBankDataController;

	@Mock
	private EmployeeBankServiceImpl employeeBankServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_createBankAccountAPI() {
		EmployeeBankData employeeBankData = new EmployeeBankData(1, "5000", "savings", null);
		when(employeeBankServiceImpl.createBankDetails(employeeBankData)).thenReturn(employeeBankData);
		assertNotNull(employeeBankDataController.createBankAccountAPI(employeeBankData));
	}

	@Test
	void test_getEmployeeBankDetailsAPI() throws RecordNotFoundException {
		EmployeeBankData employeeBankData = new EmployeeBankData(1, "5000", "savings", null);
		List<EmployeeBankData> list = new ArrayList<>();
		list.add(employeeBankData); 

		when(employeeBankServiceImpl.getEmployeeBankDataByBankId(1)).thenReturn(list);
		assertNotNull(employeeBankDataController.getEmployeeBankDetailsAPI(1));
	}
 
	@Test
	void test_withdrawYourAmountAPI() throws RecordNotFoundException, InvalidAccountPinException, OutOfLimitException {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		EmployeeBankData employeeBankData = new EmployeeBankData(1, "5000", "savings", accountPin);
		when(employeeBankServiceImpl.getEmployeeBalance(1)).thenReturn("5000");
		when(employeeBankServiceImpl.withDrawMoneyFromBankAccount(1, "50", accountPin.getPin()))
				.thenReturn(employeeBankData);
		assertNotNull(employeeBankDataController.withdrawYourAmountAPI(1, "50", accountPin.getPin()));
	}

	@Test
	void test_searchAPI() {
		EmployeeBankData employeeBankData = new EmployeeBankData(1, "5000", "savings", null);
		List<EmployeeBankData> list = new ArrayList<>();
		list.add(employeeBankData);

		when(employeeBankServiceImpl.searchBankDatas("savings")).thenReturn(list);
		assertNotNull(employeeBankDataController.searchAPI("savings"));

	}
}
