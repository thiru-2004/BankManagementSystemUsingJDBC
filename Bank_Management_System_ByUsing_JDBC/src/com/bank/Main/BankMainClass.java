package com.bank.Main;

import java.util.Scanner;

import com.bank.service.AdminService;
import com.bank.service.CustomerServices;

public class BankMainClass 
{
	private static boolean start;

	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		boolean start=true;
		while(start)
		{
			System.out.println("Enter the option");
			System.out.println("  1.Customer Regestration\n  2.Customer Login\n  3.Admin Login\n  4.LogOut");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
			{
				CustomerServices cs=new CustomerServices();
				
				cs.customerRegsitration();
			}
			break;
			case 2:
			{
				CustomerServices cs=new CustomerServices();
				cs.custumerLogin();
				
			}
			break;
			case 3:
			{
				AdminService as=new  AdminService();
				
				as.adminLogin();
			}
			break;
			case 4:
			{
				 start = false;
			}
			
			default:System.out.println("Hope You  Enjoy the Session\nVisit Again..........");
			}
				
			
		}
		}

}
