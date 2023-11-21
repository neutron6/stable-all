package com.rsn.service;

import java.util.List;
import java.util.Optional;

import com.rsn.exception.InvalidAccountPinException;
import com.rsn.exception.OutOfLimitException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.AccountPin;
import com.rsn.model.Employee;
import com.rsn.model.EmployeeBankData;

public interface EmployeeBankService {

	List<EmployeeBankData> getEmployeeBankDataByBankId(Integer bankId) throws RecordNotFoundException;

	EmployeeBankData createBankDetails(EmployeeBankData employeeBankData);

	EmployeeBankData withDrawMoneyFromBankAccount(Integer bankId, String amount, String pin)
			throws RecordNotFoundException, InvalidAccountPinException, OutOfLimitException;

	public List<EmployeeBankData> searchBankDatas(String query);

}
