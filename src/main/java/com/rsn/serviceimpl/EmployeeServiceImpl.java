package com.rsn.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.model.EmployeeBankData;
import com.rsn.repository.EmployeeRepo;
import com.rsn.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public List<Employee> createEmployeeAccount(List<Employee> employee) {
		// TODO Auto-generated method stub
		logger.config("create account api");
		return employeeRepo.saveAll(employee);
	}

	@Override
	public List<Employee> getAllEmployeeData() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}

	@Override
	public void deleteEmployeeDataByUsingId(Integer id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List<Employee> employee = employeeRepo.findAllById(id);
		if (employee != null) {
			employeeRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException();
		}
	}

	@Override
	public Employee updateEmployeeDataByUsingId(Integer id, Employee employee) throws RecordNotFoundException {
		// TODO Auto-generated method stub

		Optional<Employee> employeesData = employeeRepo.findById(id);
		EmployeeBankData employeeBankData = employee.getEmployeeBankData();
		if (employeesData.isPresent()) {
			Employee employeeX = employeesData.get();
			employeeX.setEmployeeEmail(employee.getEmployeeEmail());
			employeeX.setEmployeeCity_Name(employee.getEmployeeCity_Name());
			employeeX.setEmployeeFirst_Name(employee.getEmployeeFirst_Name());
			employeeX.setEmployeeLast_Name(employee.getEmployeeLast_Name());
			employeeX.setEmployeePassword(employee.getEmployeePassword());
			employeeX.setEmployeeMarried_status(employee.getEmployeeMarried_status());
			employeeX.setEmployeeResident_Address(employee.getEmployeeResident_Address());
			return employeeRepo.save(employeeX);
		} else {
			throw new RecordNotFoundException();
		}

	}

	public List<Employee> searchEmployeeUsingAnyField(String query) {
		List<Employee> employees = employeeRepo.searchEmployees(query);
		logger.config(query + "**** searchEmployeeUsingAnyField **** working");
		return employees;
	}

}
