package com.rsn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rsn.utils.ErrorCodes;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LicNotFoundException extends Exception {

	public LicNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
