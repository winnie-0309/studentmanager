package com.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ApplicationException extends RuntimeException {

	public ApplicationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public String printErrorMessage() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		super.printStackTrace(pw);
		return sw.toString();
	}

}
