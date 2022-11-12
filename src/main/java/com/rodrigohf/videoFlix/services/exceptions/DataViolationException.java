package com.rodrigohf.videoFlix.services.exceptions;

public class DataViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataViolationException(String mensagem) {
		super(mensagem);
	}
}
