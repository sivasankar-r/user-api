package com.sivasankar.userapi.framework;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("An Active already exist for this ID");
	}

	public UserNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
