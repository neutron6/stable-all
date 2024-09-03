package com.rsn.service;

import java.util.List;

import com.rsn.exception.LicNotFoundException;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.model.Lic;

public interface EmployeeService {

	List<Employee> getAllEmployeeData();

	void deleteEmployeeDataByUsingId(Integer id) throws RecordNotFoundException;

	Employee updateEmployeeDataByUsingId(Integer id, Employee employee) throws RecordNotFoundException;

	public List<Employee> searchEmployeeUsingAnyField(String query);

	Lic getLicDataByUsingEmployeeId(Integer id) throws RecordNotFoundException, LicNotFoundException;

}
