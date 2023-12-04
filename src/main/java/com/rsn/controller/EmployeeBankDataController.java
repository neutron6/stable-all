package com.rsn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.exception.InvalidAccountPinException;
import com.rsn.exception.OutOfLimitException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.EmployeeBankData;
import com.rsn.serviceimpl.EmployeeBankServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@EnableTransactionManagement
@RequestMapping("/api/v1")
public class EmployeeBankDataController {

	Logger logger = LoggerFactory.getLogger(EmployeeBankDataController.class);

	@Autowired
	private EmployeeBankServiceImpl employeeBankServiceImpl;

	@PostMapping("/createbankdata")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<String> createBankAccountAPI(@RequestBody EmployeeBankData employeeBankData) {
		employeeBankServiceImpl.createBankDetails(employeeBankData);
		return ResponseEntity.ok("Bank account created");
	}

	@GetMapping("/getbankdetails/{bankId}")
	public ResponseEntity<List<EmployeeBankData>> getEmployeeBankDetailsAPI(@PathVariable("bankId") Integer bankId)
			throws RecordNotFoundException {
		logger.info("****** getEmployeeBankDetailsAPI is working ********");
		return ResponseEntity.ok(employeeBankServiceImpl.getEmployeeBankDataByBankId(bankId));
	}

	@GetMapping("/amountwithdraw/{bankId}/{amount}/{pin}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> withdrawYourAmountAPI(@PathVariable("bankId") Integer bankId,
			@PathVariable("amount") String amount, @PathVariable("pin") String pin)
			throws RecordNotFoundException, InvalidAccountPinException, OutOfLimitException {
		employeeBankServiceImpl.withDrawMoneyFromBankAccount(bankId, amount, pin);
		String p = employeeBankServiceImpl.getEmployeeBalance(bankId).toString();
		logger.info("****** withdrawYourAmountAPI is working ********");
		return ResponseEntity.ok("** Transaction Completed **" + " Bank Balance->> " + p);
	}

	@GetMapping("/search")
	@ResponseStatus(value = HttpStatus.FOUND)
	public ResponseEntity<List<EmployeeBankData>> searchAPI(@RequestParam("query") String query) {
		logger.info("****** searchAPI is working ********");
		return ResponseEntity.ok(employeeBankServiceImpl.searchBankDatas(query));
	}
}
