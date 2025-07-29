package com.bank.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.bank.DAO.CustomerDAO;
import com.bank.DAO.IsPresentInDataBase;
import com.bank.DAO.TrancationDetailsDAO;
import com.bank.DTO.CustomerDetails;
import com.bank.Exception.CustomerDataInValidException;
import com.bank.Exception.DuplicateRecordException;

public class CustomerServices
{
	CustomerDAO customerDAO=new CustomerDAO();
	Scanner sc=new Scanner(System.in);
	
	public void customerRegsitration()
	{
	
		CustomerDetails cd=new CustomerDetails();
		System.out.println("Enetr the name:");
		String name=sc.next();
		while(true)
		{
			try
			{
			if(name.matches("^[A-Za-z ]{2,50}$"))
			{
				
				cd.setName(name);
				break;
				
			}
			else
			{
				throw new CustomerDataInValidException("InValid Name ");
			}
			}
			catch(CustomerDataInValidException e)
			{
				System.out.println("Invalid Name");
				System.out.println("Re Enter the Name:");
				name=sc.next();
			}
		}
		System.out.println("Enter the EmailId:");
		String email=sc.next();
		
		while(true)
		{
			try
			{
				if(email.endsWith("@gmail.com"))
				{
					if(!IsPresentInDataBase.isMailPresentInDatabase(email))
					{
					cd.setEmailid(email);
					break;
					}
					else
					{
						throw new DuplicateRecordException("Duplicate Email In DataBase ");
					}
				}
				else
				{
					throw new CustomerDataInValidException("InValid EmailId ");
				}
			}
			catch(CustomerDataInValidException  e)
			{
				System.out.println("Invalid EmailId");
				System.out.println("Re Enter the New emailId");
				email=sc.next();
			}
			catch(DuplicateRecordException  e)
			{
				System.out.println(" Email is Already present in database");
				System.out.println("Re Enter the New emailId");
				email=sc.next();
			}
			
		}
		System.out.println("Enter the AadherNumber:");
		long aadherNumber=sc.nextLong();
		while(true)
		{
			try
			{
				if(aadherNumber>=100000000000l && aadherNumber<=999999999999l)
				{
					if(!IsPresentInDataBase.isAadherPresentInDatabase(aadherNumber))
					{
					cd.setAadharnumber(aadherNumber);
					break;
					}
					else
					{
						throw new DuplicateRecordException("Duplicate  Aadher Number");
					}
				}
				else
				{
					throw new CustomerDataInValidException("InValid Aadher Number");
				}
			}
			catch( CustomerDataInValidException e)
			{
				System.out.println("Invalid aadherNumber");
				System.out.println("Re Enter the New AadherNumber");
				aadherNumber=sc.nextLong();
			}
			catch( DuplicateRecordException e)
			{
				System.out.println(" aadher Number is present in database");
				System.out.println("Re Enter the New AadherNumber");
				aadherNumber=sc.nextLong();
			}
		}
		System.out.println("Enter the Mobile Number:");
		long mobileNumber=sc.nextLong();
		while(true)
		{
			try
			{
				if(mobileNumber>=6000000000l && mobileNumber<=9999999999l)
				{
					if(!IsPresentInDataBase.isMobilePresentInDatabase(mobileNumber))
					{
					cd.setMobilenumber(mobileNumber);
					break;
					}
					else
					{
						throw new DuplicateRecordException(" Mobile Number is Already present In database.");
					}
				}
				else
				{
					throw new CustomerDataInValidException("InValid Mobile Number");
				}
			}
			catch(CustomerDataInValidException e)
			{
				System.out.println("Invalid mobile number ");
				System.out.println("Re Enter the New Mobile Number");
				mobileNumber=sc.nextLong();
			}
			catch(DuplicateRecordException e)
			{
				System.out.println(" Mobile Number Is Already is present in database");
				System.out.println("Re Enter the New Mobile Number");
				mobileNumber=sc.nextLong();
			}
		}
		System.out.println("Enter the Gender:");
		String gender=sc.next();
		while(true)
		{
			try {
			if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Others"))
			{
				cd.setGender(gender);
				break;
			}
			else
			{
				throw new CustomerDataInValidException("InValid Gender");
			}
			}
			catch(CustomerDataInValidException e)
			{
				System.out.println("Invalid Gender");
				System.out.println("Re Enter the gender");
				gender=sc.next();
			}
		}
		System.out.println("Enter the address:");
		String address=sc.next();
		cd.setAddress(address);
		System.out.println("Enter the Amount:");
		String status="Pending";
		cd.setStatus(status);
		double amount=sc.nextDouble();
		while(true)
		{
			try
			{
				if(amount>= 0)
				{
					cd.setAmount(amount);
					break;
				}
				else
				{
					throw new CustomerDataInValidException("InValid Amount");
				}
			}
			catch(CustomerDataInValidException e)
			{
				System.out.println("Invalid Amount");
				System.out.println("Re enter the Amount");
				amount=sc.nextDouble();
			}
		}
		if(customerDAO.inserCustomerDetails(cd))
		{
			System.out.println("Data Is Inserted");
		}
		else
		{
			System.out.println("Server Error!");
		}
	}
	
	public static String genrateCaptha()
	{
		String chars="QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm1234567890";
		Scanner sc=new Scanner(System.in);
		Random r=new Random();
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++)
		{
			sb.append(chars.charAt(r.nextInt(chars.length())));
		}
		String CAPTCHA=sb.toString();
		return CAPTCHA;
	}
	
	public void custumerLogin() {
		System.out.println("Enter  EmailId or Account Number");
		String emailidORaccNum=sc.next();
		System.out.println("Enter  Pin");
		int pin=sc.nextInt();
		try {
		ResultSet rs=customerDAO.selectCustomerDetailsByUsingEmailIdOrAccountNum(emailidORaccNum, pin);
		
		if(rs!=null && rs.next())
		{
			String genrated_Cpatcha=genrateCaptha();
			System.out.println("CAPTCHA:"+genrated_Cpatcha);
			System.out.println("Ënter the Captha:");
			String captcha=sc.next();
			
			String gender=rs.getString("customer_Gender");
			String name=rs.getString("customer_Name");
			 
			if(genrated_Cpatcha.equals(captcha))
			{
				if(gender.equalsIgnoreCase("Male"))
				{
					System.out.println("Welecome Mr:"+name);
				}
				else
				{
					System.out.println("Welcome Ms:"+name);
				}
				boolean start=true;
				while(start)
				{
				System.out.println("Enter\n 1.Deposit\n 2.Withdrawl\n 3.ShowBalance\n 4.Update Pin\n 5.check Statement\n 6.Close Account\n 7.Back");
				System.out.println("Enter the option:");
				int ch=sc.nextInt();
				
				
					switch(ch)
					{
					case 1:
					{
						System.out.println("Enter the Amount to Deposite:");
						double amount=sc.nextDouble();
						CustomerDAO.deposit(amount,rs);
						
					}
					break;
					case 2:
					{
						//WithDrawl
						System.out.println("Enter the Amount to WithDrawl");
						double amount=sc.nextDouble();
						CustomerDAO.withdrawl(amount,rs);
					}
					break;
					case 3:
					{
						//ShowBalance
						double balance=rs.getDouble("customer_Amount");
						System.out.println("Current Balence :"+balance);
						
					}
					break;
					case 4:
					{
						System.out.println("Enter the Updated pin");
						int user_pin=sc.nextInt();
						CustomerDAO.update_Pin(user_pin,rs);
						
					}
					break;
					case 5:
					{
						System.out.println("Check Statement");
						TrancationDetailsDAO.getStatement(rs);
					}
					break;
					case 6:
					{
						System.out.println("Close Account");
						CustomerDAO.closeAccount(rs);
					}
					break;
					case 7:
					{
						start=false;
					}
					default:System.out.println("Enter the Valid Option");
					}
				}
			}
			else
			{
				System.out.println("Invalid captcha");
			}
		}
			
		
		else {
			System.out.println("invalid Credendials");
		}
		
		
		
			
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
