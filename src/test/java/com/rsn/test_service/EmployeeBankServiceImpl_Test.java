package com.rsn.test_service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

import com.rsn.exception.InvalidAccountPinException;
import com.rsn.exception.OutOfLimitException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.AccountPin;
import com.rsn.model.EmployeeBankData;
import com.rsn.repository.EmployeeBankDataRepo;
import com.rsn.serviceimpl.EmployeeBankServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeBankServiceImpl_Test {

	@InjectMocks
	private EmployeeBankServiceImpl mockBankServiceImpl;

	@Mock
	private EmployeeBankDataRepo mockEmployeeBankDataRepo;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_createBankDetails() {
		EmployeeBankData employeeBankData = new EmployeeBankData(1, "500", "savings", null);
		Mockito.when(mockEmployeeBankDataRepo.save(employeeBankData)).thenReturn(employeeBankData);
		assertNotNull(mockBankServiceImpl.createBankDetails(employeeBankData));
	}

	@Test
	public void test_withDrawMoneyFromBankAccount()
			throws RecordNotFoundException, InvalidAccountPinException, OutOfLimitException {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		Optional<EmployeeBankData> employee = Optional
				.ofNullable(new EmployeeBankData(1, "500", "savings", accountPin));

		Mockito.when(mockEmployeeBankDataRepo.findById(1)).thenReturn(employee);

		assertNull(mockBankServiceImpl.withDrawMoneyFromBankAccount(1, "50", "123"));

	}

	@Test
	public void test_withDrawMoneyFromBankAccountOutOfLimitException() {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		Optional<EmployeeBankData> employee = Optional
				.ofNullable(new EmployeeBankData(1, "500", "savings", accountPin));

		Mockito.when(mockEmployeeBankDataRepo.findById(1)).thenReturn(employee);

		try {
			assertNull(mockBankServiceImpl.withDrawMoneyFromBankAccount(1, "5000", "123"));
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidAccountPinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutOfLimitException e) {
			assertEquals(e.getMessage(), "** Your account has low balance **" + "Your account balance is ->" + "500");
		}
	}

	@Test
	public void test_withDrawMoneyFromBankAccountInvalidAccountPinException() {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		Optional<EmployeeBankData> employee = Optional
				.ofNullable(new EmployeeBankData(1, "500", "savings", accountPin));

		Mockito.when(mockEmployeeBankDataRepo.findById(1)).thenReturn(employee);
		try {
			mockBankServiceImpl.withDrawMoneyFromBankAccount(1, "50", "1245");
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAccountPinException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), " wrong account pin");
		} catch (OutOfLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_getEmployeeBankDataByBankId() throws RecordNotFoundException {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		EmployeeBankData employeeBankDatas = new EmployeeBankData(1, "500", "savings", accountPin);
		List<EmployeeBankData> emplist = new ArrayList<>();
		emplist.add(employeeBankDatas);

		when(mockEmployeeBankDataRepo.findByBankId(1)).thenReturn(emplist);
		assertNotNull(mockBankServiceImpl.getEmployeeBankDataByBankId(1));
	}

	@Test
	public void test_searchBankDatas() {
		AccountPin accountPin = new AccountPin();
		accountPin.setPin("123");
		EmployeeBankData employeeBankDatas = new EmployeeBankData(1, "500", "savings", accountPin);
		List<EmployeeBankData> emplist = new ArrayList<>();
		emplist.add(employeeBankDatas);

		when(mockEmployeeBankDataRepo.searchBankDatas("savings")).thenReturn(emplist);
		assertNotNull(mockBankServiceImpl.searchBankDatas("query"));
	}

}
