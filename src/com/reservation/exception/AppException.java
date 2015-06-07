package com.reservation.exception;

public class AppException extends Exception{
	
	private static final long serialVersionUID = 7834763185066636052L;
	
	public AppException(String errorMsg){
		super(errorMsg);
	}
	public AppException(String errorMsg,Throwable cause){
		super(errorMsg,cause);
	}

}
