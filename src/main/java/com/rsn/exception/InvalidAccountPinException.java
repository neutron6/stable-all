package com.rsn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.bytebuddy.implementation.bind.annotation.Super;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidAccountPinException extends Exception {
	public InvalidAccountPinException() {
		super(" wrong account pin");
	}
}
