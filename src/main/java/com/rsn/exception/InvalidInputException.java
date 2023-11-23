package com.rsn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidInputException extends Exception {
	public InvalidInputException() {
		super("# Either you entered wrong input or input is void #");
	}

}
