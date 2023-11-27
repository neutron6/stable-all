package com.rsn.test_controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rsn.controller.EmployeeController;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.serviceimpl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeController_Test {

	@InjectMocks
	private EmployeeController mockEmployeeController;

	@Mock
	private EmployeeServiceImpl employeeServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_deleteAccountAPI() throws RecordNotFoundException {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		doNothing().when(employeeServiceImpl).deleteEmployeeDataByUsingId(1);
		assertNotNull(mockEmployeeController.deleteAccountAPI(1));
	}

	@Test
	void test_updateAccountAPI() throws RecordNotFoundException {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null);
		when(employeeServiceImpl.updateEmployeeDataByUsingId(1, employee1)).thenReturn(employee1);
		assertNotNull(mockEmployeeController.updateAccountAPI(1, employee1));
	}

	@Test
	void test_getAllData() {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		when(employeeServiceImpl.getAllEmployeeData()).thenReturn(list);
		assertNotNull(mockEmployeeController.getAllData()); 
	}

	@Test
	void test_searchAPI() {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		when(employeeServiceImpl.searchEmployeeUsingAnyField("rushi")).thenReturn(list);
		assertNotNull(mockEmployeeController.searchAPI(any()));
	}
}
