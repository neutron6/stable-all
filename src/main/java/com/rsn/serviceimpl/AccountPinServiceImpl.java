package com.rsn.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsn.model.AccountPin;
import com.rsn.repository.AccountPinRepo;
import com.rsn.service.AccountPinService;

@Service
public class AccountPinServiceImpl implements AccountPinService {

	@Autowired
	private AccountPinRepo accountPinRepo;

	@Override
	public AccountPin saveAccountPin(AccountPin accountPin) {
		return accountPinRepo.save(accountPin);
	}

}
