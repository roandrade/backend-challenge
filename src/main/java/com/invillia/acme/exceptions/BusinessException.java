package com.invillia.acme.exceptions;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085274242292291095L;
	
	public BusinessException(String message) {
		super(message);
	}

}
