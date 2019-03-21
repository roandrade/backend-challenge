package com.invillia.acme.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.invillia.acme.exceptions.BusinessException;
import com.invillia.models.Message;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String bodyOfResponse = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getField() + " " + fe.getDefaultMessage()).findFirst().orElse("");
		Message message = new Message().message(bodyOfResponse);
		HttpHeaders header = new HttpHeaders();
		return handleExceptionInternal(ex, message, header, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleExcpetion(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Message message = new Message().message(ex.getMessage());
		return ResponseEntity.status(status).body(message);
	}
	
	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<Object> handleBusinessExceptions(BusinessException ex, WebRequest request){
		Message message = new Message().message(ex.getMessage());
		return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
		
	}
}
