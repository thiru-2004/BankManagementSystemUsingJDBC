package com.bank.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrancationDetails 
{
	
	private int trancationid;
	private String trancationtype;
	private double trancationamount;
	private LocalDate trancationDate;
	private LocalTime trancationtime;
	private double balanceamount;
	private String trancationStatus;
	private long customeraccountnumber;
	
	public TrancationDetails() {}
	
	public TrancationDetails(int trancationid, String trancationtype, double trancationamount, LocalDate trancationDate,
			LocalTime trancationtime, double balanceamount, String trancationStatus, long customeraccountnumber)
	{
		
		this.trancationid = trancationid;
		this.trancationtype = trancationtype;
		this.trancationamount = trancationamount;
		this.trancationDate = trancationDate;
		this.trancationtime = trancationtime;
		this.balanceamount = balanceamount;
		this.trancationStatus = trancationStatus;
		this.customeraccountnumber = customeraccountnumber;
	}

	public int getTrancationid() {
		return trancationid;
	}

	public void setTrancationid(int trancationid) {
		this.trancationid = trancationid;
	}

	public String getTrancationtype() {
		return trancationtype;
	}

	public void setTrancationtype(String trancationtype) {
		this.trancationtype = trancationtype;
	}

	public double getTrancationamount() {
		return trancationamount;
	}

	public void setTrancationamount(double trancationamount) {
		this.trancationamount = trancationamount;
	}

	public LocalDate getTrancationDate() {
		return trancationDate;
	}

	public void setTrancationDate(LocalDate trancationDate) {
		this.trancationDate = trancationDate;
	}

	public LocalTime getTrancationtime() {
		return trancationtime;
	}

	public void setTrancationtime(LocalTime trancationtime) {
		this.trancationtime = trancationtime;
	}

	public double getBalanceamount() {
		return balanceamount;
	}

	public void setBalanceamount(double balanceamount) {
		this.balanceamount = balanceamount;
	}

	public String getTrancationStatus() {
		return trancationStatus;
	}

	public void setTrancationStatus(String trancationStatus) {
		this.trancationStatus = trancationStatus;
	}

	public long getCustomeraccountnumber() {
		return customeraccountnumber;
	}

	public void setCustomeraccountnumber(long customeraccountnumber) {
		this.customeraccountnumber = customeraccountnumber;
	}

	@Override
	public String toString() {
		return "TrancationDetails [trancationid=" + trancationid + ", trancationtype=" + trancationtype
				+ ", trancationamount=" + trancationamount + ", trancationDate=" + trancationDate + ", trancationtime="
				+ trancationtime + ", balanceamount=" + balanceamount + ", trancationStatus=" + trancationStatus
				+ ", customeraccountnumber=" + customeraccountnumber + "]";
	}
	
	
	
	

}
