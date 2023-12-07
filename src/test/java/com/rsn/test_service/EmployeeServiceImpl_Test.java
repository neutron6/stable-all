package com.rsn.test_service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.model.EmployeeBankData;
import com.rsn.repository.EmployeeBankDataRepo;
import com.rsn.repository.EmployeeRepo;
import com.rsn.serviceimpl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImpl_Test {

	@InjectMocks
	private EmployeeServiceImpl mockEmployeeServiceImpl;

	@Mock
	private EmployeeRepo mockEmployeeRepo;
	
	@Mock
	private EmployeeBankDataRepo mockEmployeeBankDataRepo;

	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_createBankDetailsAPI() {
		EmployeeBankData employeeBankData=new EmployeeBankData(23, "25000", "Savings", null);
		when(mockEmployeeBankDataRepo.save(employeeBankData)).thenReturn(employeeBankData);
		assertNotNull(mockEmployeeServiceImpl.save(employeeBankData));
	}
	

	@Test
	public void test_getAllEmployeeData() {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		Employee employee2 = new Employee(2, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		list.add(employee2);

		Mockito.when(mockEmployeeRepo.findAll()).thenReturn(list);
		assertNotNull(mockEmployeeServiceImpl.getAllEmployeeData());
	}

	@Test
	public void test_deleteEmployeeDataByUsingId() throws RecordNotFoundException {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);

		Mockito.when(mockEmployeeRepo.findAllById(1)).thenReturn(list);
		mockEmployeeServiceImpl.deleteEmployeeDataByUsingId(1);

	}

	@Test
	public void test_deleteEmployeeDataByUsingId_Exception() {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);

		Mockito.when(mockEmployeeRepo.findAllById(1)).thenReturn(null);
		try {
			mockEmployeeServiceImpl.deleteEmployeeDataByUsingId(1);
		} catch (RecordNotFoundException e) {
			assertEquals(e.getMessage(), "Record not found");
		}
	}

	@Test
	public void test_updateEmployeeDataByUsingId() throws RecordNotFoundException {
		Optional<Employee> employee1 = Optional.of(new Employee(1, "rushi", "nichit", "Pune", "PP", "Single", "@gmail",
				"123", LocalDate.now(), null, null,null));
		Employee updatedEmp = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);

		Mockito.when(mockEmployeeRepo.findById(1)).thenReturn(employee1);
		assertNull(mockEmployeeServiceImpl.updateEmployeeDataByUsingId(1, updatedEmp));

	}

	@Test
	public void test_updateEmployeeDataByUsingId_Exception() {
		Employee updatedEmp = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		try {
			assertNotNull(mockEmployeeServiceImpl.updateEmployeeDataByUsingId(1, updatedEmp));
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "Record not found");
		}
	}

	@Test
	public void test_searchEmployeeUsingAnyField() {
		String query = "James";
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		Employee employee2 = new Employee(1, "James", "IOP", "Belgium", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null, null,null);
		Employee employee3 = new Employee(1, "aakash", "Rao", "Pune", "PP", "Single", "@gmail", "123", LocalDate.now(),
				null, null,null);
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		list.add(employee2);
		list.add(employee3);

		Mockito.when(mockEmployeeRepo.searchEmployees(query)).thenReturn(list);
		List<Employee> result = mockEmployeeServiceImpl.searchEmployeeUsingAnyField(query);
		assertNotNull(result);
		assertEquals("James", result.get(1).getEmployeeFirst_Name());
	}
}
