package com.bank.Exception;

public class CustomerDataInValidException extends RuntimeException
{
	private String message;

	public CustomerDataInValidException(String message) 
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
		return "CustomerDataInValidException [message=" + message + "]";
	}
	
	
	
	
	 

}
