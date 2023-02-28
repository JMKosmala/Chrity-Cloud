package com.fdmgroup.CharCloud.exceptions;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(int id) {
		super(String.format("User with id %o could not be found.", id));
	}
	public UserNotFoundException(String parameter) {
		super(String.format("User with given parameter %s could not be found.", parameter));
	}
}
