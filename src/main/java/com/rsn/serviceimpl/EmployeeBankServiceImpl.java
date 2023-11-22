package com.rsn.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsn.exception.InvalidAccountPinException;
import com.rsn.exception.OutOfLimitException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.AccountPin;
import com.rsn.model.EmployeeBankData;
import com.rsn.repository.EmployeeBankDataRepo;
import com.rsn.service.EmployeeBankService;

@Service
public class EmployeeBankServiceImpl implements EmployeeBankService {

	@Autowired
	private EmployeeBankDataRepo employeeBankDataRepo;

	@Override
	public EmployeeBankData createBankDetails(EmployeeBankData employeeBankData) {
		return employeeBankDataRepo.save(employeeBankData);
	}

	@Override
	public List<EmployeeBankData> getEmployeeBankDataByBankId(Integer bankId) throws RecordNotFoundException {
		List<EmployeeBankData> employeeBankDatas = employeeBankDataRepo.findByBankId(bankId);
		if (employeeBankDatas != null) {
			return employeeBankDataRepo.findByBankId(bankId);
		} else {
			throw new RecordNotFoundException();
		}
	}

	@Override
	public EmployeeBankData withDrawMoneyFromBankAccount(Integer bankId, String amount, String pin)
			throws RecordNotFoundException, InvalidAccountPinException, OutOfLimitException {
		Optional<EmployeeBankData> employee = employeeBankDataRepo.findById(bankId);
		EmployeeBankData employeeBankData = employee.get();

		String accountPin2 = employeeBankData.getAccountPin().getPin().toString();
		Integer bd = Integer.parseInt(employeeBankData.getBankBalance());

		if (employee.isPresent()) {
			if (employeeBankData != null && accountPin2.equals(pin)) {
				Integer amountX = Integer.parseInt(amount);
				if (bd < amountX) {
					throw new OutOfLimitException(
							"** Your account has low balance **" + "Your account balance is ->" + bd);
				} else {
					Integer new_bank_balance = bd - amountX;
					String bd_new = new_bank_balance.toString();
					employeeBankData.setBankBalance(bd_new);
					return employeeBankDataRepo.save(employeeBankData);
				}
			} else {
				throw new InvalidAccountPinException();
			}
		} else {
			throw new RecordNotFoundException(); 

		}

	}

	@Override
	public List<EmployeeBankData> searchBankDatas(String query) {
		List<EmployeeBankData> employeeBankDatas = employeeBankDataRepo.searchBankDatas(query);
		return employeeBankDatas;
	}

	// *** Methods for common data fetching ** TAKE INPUT AS ARG BANKID***
	public String getEmployeeBalance(Integer bankId) throws RecordNotFoundException {
		Optional<EmployeeBankData> employee = employeeBankDataRepo.findById(bankId);

		if (employee.isPresent()) {
			EmployeeBankData employeeBankData = employee.get();
			String n = employeeBankData.getBankBalance().toString();
			return n;
		}
		throw new RecordNotFoundException();
	}

}