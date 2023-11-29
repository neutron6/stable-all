package com.rsn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsn.model.Profiles;

@Repository
public interface ImageRepo extends JpaRepository<Profiles, Integer> {

	Optional<Profiles> findById(Integer id);

}
