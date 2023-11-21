package com.rsn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rsn.model.Employee;
import com.rsn.model.EmployeeBankData;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	List<Employee> findAllById(Integer id);

	Optional<Employee> findById(Integer id);

	// Employee saveAll(EmployeeBankData employeeBankData);

	// search api
	@Query(value = "SELECT * FROM employee e WHERE " + "e.employee_city_name LIKE CONCAT('%',:query, '%')"
			+ "Or e.employee_first_name LIKE CONCAT('%',:query, '%')"
			+ "Or e.employee_last_name LIKE CONCAT('%',:query, '%')"
			+ "Or e.employee_married_status LIKE CONCAT('%',:query, '%')"
			+ "Or e.employee_resident_address LIKE CONCAT('%',:query, '%')", nativeQuery = true)
	List<Employee> searchEmployees(String query);
}
