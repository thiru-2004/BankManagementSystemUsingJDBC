package com.bank.DTO;

public class CustomerDetails 
{
	private String name;
	private String emailid;
	private long mobilenumber;
	private long aadharnumber;
	private String address;
	private String gender;
	private long accountnumber;
	private double amount;
	private int pin;
	private String status;
	
	public CustomerDetails() {}

	public CustomerDetails(String name, String emailid, long mobilenumber, long aadharnumber, String address,
			String gender, long accountnumber, double amount, int pin,String status) 
	{
		this.name = name;
		this.emailid = emailid;
		this.mobilenumber = mobilenumber;
		this.aadharnumber = aadharnumber;
		this.address = address;
		this.gender = gender;
		this.accountnumber = accountnumber;
		this.amount = amount;
		this.pin = pin;
		this.status=status;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public long getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(long aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDetails [name=" + name + ", emailid=" + emailid + ", mobilenumber=" + mobilenumber
				+ ", aadharnumber=" + aadharnumber + ", address=" + address + ", gender=" + gender + ", accountnumber="
				+ accountnumber + ", amount=" + amount + ", pin=" + pin + ", status=" + status + "]";
	}
	
	

}
