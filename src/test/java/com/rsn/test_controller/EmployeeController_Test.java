package com.rsn.test_controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rsn.controller.EmployeeController;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.repository.EmployeeRepo;
import com.rsn.serviceimpl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeController_Test {

	@InjectMocks
	private EmployeeController mockEmployeeController;

	@Mock
	private EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeRepo employeeRepo;

	@Mock
	private SecurityContextHolder securityContextHolder;

	@Mock
	private SecurityContext securityContext;

	@Mock
	private Authentication authentication;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_deleteAccountAPI() throws RecordNotFoundException {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		doNothing().when(employeeServiceImpl).deleteEmployeeDataByUsingId(1);
		assertNotNull(mockEmployeeController.deleteAccountAPI(1));
	}

	@Test
	void test_updateAccountAPI() throws RecordNotFoundException {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		when(employeeServiceImpl.updateEmployeeDataByUsingId(1, employee1)).thenReturn(employee1);
		assertNotNull(mockEmployeeController.updateAccountAPI(1, employee1));
	}

	@Test
	void test_getAllData() {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		when(employeeServiceImpl.getAllEmployeeData()).thenReturn(list);
		assertNotNull(mockEmployeeController.getAllData());
	}

	@Test
	void test_searchAPI() {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		when(employeeServiceImpl.searchEmployeeUsingAnyField(anyString())).thenReturn(list);
		assertNotNull(mockEmployeeController.searchAPI(any()));
	}

	@Test
	void test_getUserDetails() {
		String employeeEmail = "Rushi@gmail.com";
		Employee employee1 = new Employee();
		employee1.setEmployeeEmail(employeeEmail);

		securityContextHolder.setContext(securityContext);
		when(authentication.getPrincipal()).thenReturn(employeeEmail);
		when(securityContext.getAuthentication()).thenReturn(authentication);

		when(employeeRepo.findByEmployeeEmail(anyString())).thenReturn(Optional.of(employee1));
		assertNotNull(mockEmployeeController.getUserDetails());

	}
}
