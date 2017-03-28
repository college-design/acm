package com.lxg.acm.exception;

/**
 * 自定义异常
 */
public class UserException extends RuntimeException {

	public UserException() {

	}

	public UserException(String arg0) {
		super(arg0);

	}

	public UserException(Throwable arg0) {
		super(arg0);

	}

	public UserException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public UserException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
