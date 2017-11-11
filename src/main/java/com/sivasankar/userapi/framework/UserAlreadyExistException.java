package com.sivasankar.userapi.framework;

public class UserAlreadyExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException() {
		super("An Active already exist for this ID");
	}

	public UserAlreadyExistException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
