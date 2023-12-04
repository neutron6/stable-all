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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.exception.InvalidInputException;
import com.rsn.exception.ItemNotFoundException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Items;
import com.rsn.serviceimpl.EmployeeBankServiceImpl;
import com.rsn.serviceimpl.ItemsServiceImpl;



@RestController
@EnableTransactionManagement
@RequestMapping("/api/v3")
public class ItemController {
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private ItemsServiceImpl itemsServiceImpl;

	@Autowired
	private EmployeeBankServiceImpl employeeBankServiceImpl;

	@PostMapping("/createitem")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> createItemAPI(@RequestBody List<Items> items) {
		itemsServiceImpl.createItems(items);
		return ResponseEntity.ok("**** item created successfully ***");
	}

	@GetMapping("/buyitems/{itemName}/{quantity}/{bankId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> buyItemAPI(@PathVariable("itemName") String itemsName,
			@PathVariable("quantity") String quantity, @PathVariable("bankId") Integer bankId)
			throws ItemNotFoundException, RecordNotFoundException, InvalidInputException {
		String name = itemsServiceImpl.getItem(itemsName).toString();
		String price = itemsServiceImpl.getItemPrice(itemsName).toString();

		itemsServiceImpl.buyItems(itemsName, quantity, bankId);

		String bb = employeeBankServiceImpl.getEmployeeBalance(bankId).toString();
		logger.info("***** buyItemAPI is working *****");
		return ResponseEntity.ok("**** Transaction successfully **** YOU BUY AN ITEM " + name + " HAVING PRICE " + price
				+ " *** REMAINED ACCOUNT BALANCE *** :>   " + bb);

	}

}
