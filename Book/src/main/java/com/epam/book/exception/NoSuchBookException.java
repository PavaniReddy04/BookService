
package com.epam.book.exception;

public class NoSuchBookException extends Exception{
	private static final long serialVersionUID = 1L;

	public NoSuchBookException(String message) {
		super(message);
	}
}
