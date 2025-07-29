package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.bank.DTO.CustomerDetails;
import com.bank.DTO.TrancationDetails;
import com.bank.util.DatabaseConnection;


public class CustomerDAO
{
	
	private static final String insert="insert into customer_details( customer_Name, customer_EmailId, customer_Mobile_Number, customer_aadhar_Number, customer_Address, customer_Gender,  customer_Amount,customer_status) values(?,?,?,?,?,?,?,?)";

	public boolean inserCustomerDetails(CustomerDetails cd)
	{
		
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement ps=connection.prepareStatement(insert);
			ps.setString(1, cd.getName());
			ps.setString(2, cd.getEmailid());
			ps.setLong(3, cd.getMobilenumber());
			ps.setLong(4, cd.getAadharnumber());
			ps.setString(5, cd.getAddress());
			ps.setString(6, cd.getGender());
			ps.setDouble(7, cd.getAmount());
			ps.setString(8, cd.getStatus());
			int result=ps.executeUpdate();
			
			if(result!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	final private static String customerLogin="select * from customer_details "
            + "where (customer_EmailId=? or customer_Account_Number=?) and customer_PIN=?";
	public ResultSet selectCustomerDetailsByUsingEmailIdOrAccountNum(String email,int pin) {
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(customerLogin);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, email);
			preparedStatement.setInt(3, pin);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()) {
				return resultSet;
			}else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void deposit(double amount,ResultSet rs) 
	{
		try {
			double updatedAmount=rs.getDouble("customer_Amount")+amount;
			long accountNumber=rs.getLong("customer_Account_Number");
			final String update_Balance="update customer_details set customer_Amount=? where customer_Account_Number=?";
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(update_Balance);
			preparedStatement.setDouble(1,updatedAmount );
			preparedStatement.setDouble(2, accountNumber);
			int res=preparedStatement.executeUpdate();
			String status;
			if(res!=0)
			{
			status="Done";
			System.out.println(amount+" Deposited Sucessfully");
			}
			else
			{
				System.out.println("Trancation failed");
				status="Failed";
			}
			LocalDate date=LocalDate.now();
			LocalTime time=LocalTime.now();
			TrancationDetails td=new TrancationDetails();
			td.setTrancationamount(amount);
			td.setBalanceamount(updatedAmount);
			td.setCustomeraccountnumber(accountNumber);
			td.setTrancationStatus(status);
			td.setTrancationtype("Deposite");
			td.setTrancationDate(date);
			td.setTrancationtime(time);
			TrancationDetailsDAO.storeTrancation(td);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void withdrawl(double amount, ResultSet rs)
	{
		try {
			if(amount<=rs.getDouble("customer_Amount"))
			{
			double updatedAmount=rs.getDouble("customer_Amount")-amount;
			long accountNumber=rs.getLong("customer_Account_Number");
			final String update_Balance="update customer_details set customer_Amount=? where customer_Account_Number=?";
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(update_Balance);
			preparedStatement.setDouble(1,updatedAmount );
			preparedStatement.setDouble(2, accountNumber);
			int res=preparedStatement.executeUpdate();
			String status;
			if(res!=0)
			{
			status="Done";
			System.out.println(amount+" Withdrawl Sucessfully");
			}
			else
			{
				status="Failed";
			}
			LocalDate date=LocalDate.now();
			LocalTime time=LocalTime.now();
			TrancationDetails td=new TrancationDetails();
			td.setTrancationamount(amount);
			td.setBalanceamount(updatedAmount);
			td.setCustomeraccountnumber(accountNumber);
			td.setTrancationStatus(status);
			td.setTrancationtype("Withdrawl");
			td.setTrancationDate(date);
			td.setTrancationtime(time);
			TrancationDetailsDAO.storeTrancation(td);
			
			}
			else
			{
				System.out.println("Insufficent Balance!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void update_Pin(int user_pin, ResultSet rs)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Old Pin");
		int existed_pin=sc.nextInt();
		final String  update_pin="update customer_details set customer_PIN=? where customer_Account_Number=?";
		try {
			if(rs.getInt("customer_PIN")==existed_pin)
			{
				Connection connection=DatabaseConnection.formysqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(update_pin);
				preparedStatement.setInt(1,user_pin );
				preparedStatement.setInt(2, rs.getInt("customer_Account_Number"));
				int res=preparedStatement.executeUpdate();
				String status;
				if(res!=0)
				{
					status="Pin updated";
				System.out.println("Pin Updated Sucessfully");
				}
				else
				{
					status="Pin Not Updated";
					System.out.println("Pin Not Updated");
				}
				LocalDate date=LocalDate.now();
				LocalTime time=LocalTime.now();
				TrancationDetails td=new TrancationDetails();
				td.setCustomeraccountnumber(rs.getInt("customer_Account_Number"));
				td.setTrancationStatus(status);
				td.setTrancationtype("Withdrawl");
				td.setTrancationDate(date);
				td.setTrancationtime(time);
				TrancationDetailsDAO.storeTrancation(td);
			}
			else
			{
				System.out.println("Invalid Pin");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void closeAccount(ResultSet rs) 
	{
		
		final String closeAccount="Delete  from customer_details where customer_Account_Number=? ";
		Connection connection;
		try {
			connection = DatabaseConnection.formysqlConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(closeAccount);
			preparedStatement.setLong(1,rs.getLong("customer_Account_Number") );
			int result=preparedStatement.executeUpdate();
			if(result!=0)
			{
				System.out.println("Account Is Closed");
			}
			else
			{
				System.out.println("Server Error!");
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
