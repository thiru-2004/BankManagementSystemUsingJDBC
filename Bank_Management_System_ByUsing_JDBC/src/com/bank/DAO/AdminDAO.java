package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.bank.util.DatabaseConnection;

public class AdminDAO {
	
	private static final String adminLogin ="select * from admin_details where admin_emailid=? and admin_password=?";
	
	public boolean selectAdminDetailsByUsingEmailidAndPassword(String email , String password) {
		
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(adminLogin);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static void getAllCustomerDetails(String status)
	{
		try {
			 String select="SELECT * FROM bank_management_system.customer_details where customer_status=?";
			Connection connection =DatabaseConnection.formysqlConnection();
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, status);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					System.out.println("Customer id:"+rs.getInt("customer_id"));
					System.out.println("Customer Name:"+rs.getString("customer_Name"));
					System.out.println("Customer EmailId:"+rs.getString("customer_EmailId"));
					System.out.println("Customer Mobile Number:"+rs.getLong("customer_Mobile_Number"));
					System.out.println("Customer aadher Number:"+rs.getLong("customer_aadhar_Number"));
					System.out.println("Customer Address:"+rs.getString("customer_Address"));
					System.out.println("Customer Gender:"+rs.getString("customer_Gender"));
					System.out.println("Customer Account Number:"+rs.getLong("customer_Account_Number"));
					System.out.println("Customer Account Balance:"+rs.getDouble("customer_Amount"));
					System.out.println("Customer  Account Status:"+rs.getString("customer_status"));
					System.out.println("**********************************************************************");
					
					
					
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void genrateAccountNumber(int id)
	{
		Random r=new Random();
		long accountNumber=r.nextInt(9000000)+1000000l;
		int pin=r.nextInt(9000)+1000;
		int otp=r.nextInt(9000000)+1000000;
		
		String Assign_Account_Number="update customer_details set customer_Account_Number=?,customer_PIN=?,customer_status=? where customer_id=? and customer_status=?";
		
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement ps=connection.prepareStatement(Assign_Account_Number);
			ps.setLong(1, accountNumber);
			ps.setInt(2, pin);
			ps.setString(3, "Active");
			ps.setInt(4, id);
			ps.setString(5, "Pending");
			int res=ps.executeUpdate();
			if(res!=0)
			{
				System.out.println("Account Number And Pin Is Assigined to the Customer id:"+id);
			}
			else
			{
				System.out.println("Id Not Found!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void genrateAccountNumber(String status)
	{
		Random r=new Random();
		
		
		String selectid="select customer_id from customer_details where customer_status=?";
		String Assign_Account_Number="update customer_details set customer_Account_Number=?,customer_PIN=? ,customer_status=? where customer_status=? and customer_id=?";
		
		try {
			boolean updated=false;
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement ps1=connection.prepareStatement(selectid);
			ps1.setString(1, status);
			ResultSet rs=ps1.executeQuery();
			PreparedStatement ps=connection.prepareStatement(Assign_Account_Number);
			while(rs.next())
			{
			
			long accountNumber=r.nextInt(9000000)+1000000l;
			int pin=r.nextInt(9000)+1000;
			int otp=r.nextInt(9000000)+1000000;
			ps.setLong(1, accountNumber);
			ps.setInt(2, pin);
			ps.setString(3, "Active");
			ps.setString(4, status);
			int id=rs.getInt("customer_id");
			ps.setInt(5, id);
			
			ps.addBatch();
			updated=true;
			}
			if(updated)
			{
			int[] res=ps.executeBatch();
			
			
			if(res.length!=0)
			{
				System.out.println("Account Number And Pin Is Assigined to All Requsted Customers");
			}
			else
			{
				System.err.println(" Server Error!");
			}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
