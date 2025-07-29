package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.util.DatabaseConnection;


public class IsPresentInDataBase 
{
	
	

	

	public static boolean isMobilePresentInDatabase(long mobile)
	{
		String select="select customer_Mobile_Number from customer_details";
		boolean found = false;
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			Statement s=connection.createStatement();
			ResultSet rs=s.executeQuery(select);
			
			while(rs.next())
			{
				if(mobile==rs.getLong("customer_Mobile_Number"))
				{
					found=true;
					break;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return found;
	}
	
	public static boolean isMailPresentInDatabase(String email)
	{
	  String select="select customer_EmailId from customer_details";
		boolean found = false;
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			Statement s=connection.createStatement();
			ResultSet rs=s.executeQuery(select);
			
			while(rs.next())
			{
				if(email.equals(rs.getString("customer_EmailId")))
				{
					found=true;
					break;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return found;
	}
	
	
	public static boolean isAadherPresentInDatabase(long aadhernumber)
	{
	  String select="select customer_aadhar_Number from customer_details";
		boolean found = false;
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			Statement s=connection.createStatement();
			ResultSet rs=s.executeQuery(select);
			
			while(rs.next())
			{
				if(aadhernumber==rs.getLong("customer_aadhar_Number"))
				{
					found=true;
					break;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return found;
	}

	
	

}
