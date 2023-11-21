package com.rsn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsn.model.AccountPin;

@Repository
public interface AccountPinRepo extends JpaRepository<AccountPin, String> {

}
