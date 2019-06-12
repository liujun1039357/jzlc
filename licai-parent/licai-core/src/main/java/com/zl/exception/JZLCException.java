package com.zl.exception;

public class JZLCException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public JZLCException() {}

	public JZLCException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JZLCException(String message, Throwable cause) {
		super(message, cause);
	}

	public JZLCException(String message) {
		super(message);
	}

	public JZLCException(Throwable cause) {
		super(cause);
	}

}
