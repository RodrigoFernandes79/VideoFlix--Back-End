package com.rodrigohf.videoFlix.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiException> methodArgumentnotValid(MethodArgumentNotValidException e , 
			HttpServletRequest req){
		
		List<String> getErro = new ArrayList<>();
		e.getBindingResult().getAllErrors().forEach(erro ->{
			String obj = erro.getDefaultMessage();
			getErro.add(obj);
		});
		
		ApiException error = new ApiException();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(getErro.toString());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiException> dataIntegrityViolation(DataIntegrityViolationException e , HttpServletRequest req){
		ApiException erro = new ApiException();
		erro.setTimestamp(LocalDateTime.now());
		erro.setMessage(e.getMessage());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
