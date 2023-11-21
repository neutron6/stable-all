package com.rsn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class OutOfLimitException extends Exception {
	public OutOfLimitException(String s) {
		super(s);
	}
}
