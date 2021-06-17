package com.epam.book.exceptionhandler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseTemplet {

	private Date timestamp;
	private String message;
	private String details;

}
