package com.example.nivelamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMathOperationException extends RuntimeException {

	private static final long serialVersionUID = 8057195611211339194L;

	public UnsuportedMathOperationException(String ex) {
		super(ex);
	}
	
}
