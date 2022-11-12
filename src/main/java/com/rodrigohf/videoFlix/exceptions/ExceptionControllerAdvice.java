package com.rodrigohf.videoFlix.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rodrigohf.videoFlix.services.exceptions.DataViolationException;
import com.rodrigohf.videoFlix.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiException> entityNotFound(EntityNotFoundException e, HttpServletRequest req){
		ApiException error = new ApiException();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
		
	}
	
	@ExceptionHandler(DataViolationException.class)
	public ResponseEntity<ApiException> dataViolation(DataViolationException e , HttpServletRequest req){
		ApiException erro = new ApiException();
		erro.setTimestamp(LocalDateTime.now());
		erro.setMessage(e.getMessage());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
