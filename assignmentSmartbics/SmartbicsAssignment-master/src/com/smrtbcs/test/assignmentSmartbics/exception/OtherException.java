package com.smrtbcs.test.assignmentSmartbics.exception;

public class OtherException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OtherException(String msg) {
		super(msg);//
		System.err.println(msg);
	}
}
