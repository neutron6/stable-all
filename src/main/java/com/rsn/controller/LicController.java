package com.rsn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Lic;
import com.rsn.serviceimpl.LicServiceImpl;

@RestController
@RequestMapping("/api/v5")
public class LicController {

	Logger logger = LoggerFactory.getLogger(LicController.class);

	@Autowired
	private LicServiceImpl licServiceImpl;

	@PostMapping("/createlicaccount")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> createLicId(@RequestBody Lic lic) {
		licServiceImpl.createLicId(lic);
		return ResponseEntity.ok("LIC Id Created");
	}

	@GetMapping("/getlicbyid/{licId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Lic>> getByLicId(@PathVariable("licId") Integer licId) throws RecordNotFoundException {
		logger.info("********getByLICId is Woring*********");
		return ResponseEntity.ok(licServiceImpl.getLicById(licId));
	}

}
