package com.rodrigohf.videoFlix.services.exceptions;

public class UsernameNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UsernameNotFoundException(String message) {
		super(message);
		
	}

}
