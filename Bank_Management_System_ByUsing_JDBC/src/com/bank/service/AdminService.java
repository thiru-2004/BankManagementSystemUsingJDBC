package com.bank.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.bank.DAO.AdminDAO;
import com.bank.util.DatabaseConnection;

public class AdminService {
	
	Scanner sc=new Scanner(System.in);
	
	AdminDAO adminDAO=new AdminDAO();
	public void adminLogin() {
		
		System.out.println("enter the email");
		String email=sc.next();
		System.out.println("enter  password ");
		String password=sc.next();
		
		if(adminDAO.selectAdminDetailsByUsingEmailidAndPassword(email, password)) 
		{
			System.out.println("Login successfull.....");
			boolean start=true;
			while(start)
			{
				System.out.println("1.Get All CustomerDetails\n2.All Account Request Datails\n3.All Closing Account Details\n4.Logout");
				System.out.println("Enter the one option:");
				int choice=sc.nextInt();
				switch(choice)
				{
				case 1:
				{
					
					String status="Active";
					adminDAO.getAllCustomerDetails(status);
					
					
				}
				break;
				case 2:
				{
					String status="Pending";
					adminDAO.getAllCustomerDetails(status);
					System.out.println("Enter");
					System.out.println("  1.Genrate Account number for one Preson\n  2.to Select All to genrate Account Number");
					int ch=sc.nextInt();
					switch(ch)
					{
					case 1:
					{
						System.out.println("Enter the Customer Id to genrate the Account Number:");
						int id=sc.nextInt();
						adminDAO.genrateAccountNumber(id);
					}
					break;
					case 2:
					{
						
						adminDAO.genrateAccountNumber(status);
					}
					break;
					}
					
					
				}
				break;
				case 3:
				{
					String status="Closing";
					adminDAO.getAllCustomerDetails(status);
				}
				break;
				case 4:
				{
					start=false;
				}
				default:System.out.println("Enter valid Option");
				}
			}
		}
		else {
			System.out.println("invalid email and password");
		}
	}
}
