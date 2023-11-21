package com.rsn.service;

import java.util.List;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.model.EmployeeBankData;

public interface EmployeeService {
	List<Employee> createEmployeeAccount(List<Employee> employee);

	List<Employee> getAllEmployeeData();

	void deleteEmployeeDataByUsingId(Integer id) throws RecordNotFoundException;

	Employee updateEmployeeDataByUsingId(Integer id, Employee employee) throws RecordNotFoundException;

	public List<Employee> searchEmployeeUsingAnyField(String query);
}
