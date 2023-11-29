package com.rsn.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.serviceimpl.ProfilesServiceImpl;

@RestController
@RequestMapping("/api/v4")
public class ProfilesController {

	@Autowired
	private ProfilesServiceImpl profilesServiceImpl;

	@PostMapping("/uploadimage/{id}")
	public ResponseEntity<String> uploadImageAPI(@RequestParam("image") MultipartFile file,
			@PathVariable("id") Integer id) throws RecordNotFoundException, IOException {
		profilesServiceImpl.uploadProfile(file, id);
		return ResponseEntity.ok("***** Image uploaded Successfully *****");
	}

}
