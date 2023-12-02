package com.rsn.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.serviceimpl.ImageServiceImpl;

@RestController
@RequestMapping("/api/v4")
public class ImagesController {

	@Autowired
	private ImageServiceImpl imageServiceImpl;

	@PostMapping("/uploadimage/{employeeid}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<String> uploadImageAPI(@RequestParam("image") MultipartFile file,
			@PathVariable("employeeid") Integer id) throws RecordNotFoundException, IOException {
		imageServiceImpl.uploadProfileFeature(file, id);
		return ResponseEntity.ok("***** Image uploaded Successfully *****");
	}

	@GetMapping("/{employeeid}")
	public ResponseEntity<?> downloadImageAPI(@PathVariable("employeeid") Integer id) throws RecordNotFoundException {
		byte[] imageData = imageServiceImpl.downloadImageFeature(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}

}
