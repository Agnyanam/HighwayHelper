package com.startup.HighwayHelper.exception;


public class HHDomainException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HHDomainException(String code, String message) {
		super();
		this.message = message;
		this.code = code;
	}

	public HHDomainException() {
		super();
	}
	
	
	
}
