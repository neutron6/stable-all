package com.rsn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsn.model.Lic;

@Repository
public interface LicRepo extends JpaRepository<Lic, Integer> {

	Lic save(Lic lic);

	List<Lic> findByLicId(Integer licId);

}
