package com.rsn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rsn.model.Items;

@Repository
public interface ItemsRepo extends JpaRepository<Items, Long> {

	Optional<Items> findByItemName(String itemName);
	
	/*
	 * @Query(value = "SELECT * FROM ") List<Items> serachItems(String query);
	 */
}
