package com.rsn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsn.model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {
	Optional<Image> findById(Integer id);
}
