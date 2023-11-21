package com.rsn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rsn.model.EmployeeBankData;

@Repository
public interface EmployeeBankDataRepo extends JpaRepository<EmployeeBankData, Integer> {

	List<EmployeeBankData> findByBankId(Integer bankId);

	// Optional<EmployeeBankData> findByBankId1(Integer bankId);

	EmployeeBankData save(EmployeeBankData employeeBankData);

	// search api
	@Query(value = "SELECT * FROM employee_bank_data eb WHERE" + "e.bank_id LIKE CONCAT('%',:query, '%')"
			+ "e.account_type LIKE CONCAT('%',:query, '%')"
			+ "e.bank_balance LIKE CONCAT('%',:query, '%')", nativeQuery = true)
	List<EmployeeBankData> searchBankDatas(String query);
}
