package com.epam.book.exception;

public class ZeroBooksException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ZeroBooksException(String message) {
		super(message);
	}

}
