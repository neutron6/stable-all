package com.rsn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.repository.EmployeeRepo;
import com.rsn.serviceimpl.EmployeeServiceImpl;

@RestController
@EnableTransactionManagement
@RequestMapping("/api/v2")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@Autowired
	private EmployeeRepo employeeRepo;

	@PutMapping("/updateaccount/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<String> updateAccountAPI(@PathVariable("id") Integer id, @RequestBody Employee employee)
			throws RecordNotFoundException {
		employeeServiceImpl.updateEmployeeDataByUsingId(id, employee);
		logger.info("****** updateAccountAPI is working ********");
		return ResponseEntity.ok("** Account updated successfully **");
	}

	@DeleteMapping("/deleteaccount/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> deleteAccountAPI(@PathVariable("id") Integer id) throws RecordNotFoundException {
		employeeServiceImpl.deleteEmployeeDataByUsingId(id);
		logger.info("****** deleteAccountAPI is working ********");
		return ResponseEntity.ok("** Account deleted successfully **");
	}

	@GetMapping("/getaccount")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Employee>> getAllData() {
		logger.info("****** getAllData is working ********");
		return ResponseEntity.ok(employeeServiceImpl.getAllEmployeeData());
	}

	@GetMapping("/search")
	@ResponseStatus(value = HttpStatus.FOUND)
	public ResponseEntity<List<Employee>> searchAPI(@RequestParam("query") String query) {
		logger.info("****** searchAPI is working ********");
		return ResponseEntity.ok(employeeServiceImpl.searchEmployeeUsingAnyField(query));
	}

	@GetMapping("/info")
	public Employee getUserDetails() {
		String employeeEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return employeeRepo.findByEmployeeEmail(employeeEmail).get();
	}

}
