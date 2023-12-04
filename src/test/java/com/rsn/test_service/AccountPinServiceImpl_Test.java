package com.rsn.test_service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rsn.model.AccountPin;
import com.rsn.repository.AccountPinRepo;
import com.rsn.serviceimpl.AccountPinServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountPinServiceImpl_Test {
	
	@InjectMocks
	private AccountPinServiceImpl mockAccountPinServiceImpl;
	
	@Mock
	private AccountPinRepo mockAccountPinRepo;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void test_saveAccountPin() {
		AccountPin accountPin=new AccountPin();
		accountPin.setPin("123456");
		Mockito.when(mockAccountPinRepo.save(any())).thenReturn(accountPin);
		assertNotNull(mockAccountPinServiceImpl.saveAccountPin(accountPin));
	}
	
	
	

}
