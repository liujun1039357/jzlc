package com.zl.exception;
/**
 * 登录自定义异常类
 * @author
 *
 */
public class LoginInfoException  extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginInfoException() {
		super();
	}

	public LoginInfoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginInfoException(String message) {
		super(message);
	}

	public LoginInfoException(Throwable cause) {
		super(cause);
	}
	
}
