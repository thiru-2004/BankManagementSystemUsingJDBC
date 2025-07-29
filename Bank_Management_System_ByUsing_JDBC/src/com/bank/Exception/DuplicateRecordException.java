package com.bank.Exception;

public class DuplicateRecordException extends RuntimeException
{
	private String message;

	public DuplicateRecordException(String message) 
	{
		this.message = message;
	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	@Override
	public String toString() {
		return "DuplicateRecordException [message=" + message + "]";
	}
	
	

}
