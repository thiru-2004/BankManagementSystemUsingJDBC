package com.bank.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.bank.DTO.TrancationDetails;
import com.bank.util.DatabaseConnection;

public class TrancationDetailsDAO
{
	
	public static boolean storeTrancation(TrancationDetails td)
	{
		final String store="insert into trancation_details( trancation_type, trancation_amount, trancation_time, trancation_date, balence_amount, trancation_ststus, customer_account_number) values(?,?,?,?,?,?,?)";
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement ps=connection.prepareStatement(store);
			ps.setString(1, td.getTrancationtype());
			ps.setDouble(2, td.getTrancationamount());
			ps.setTime(3, Time.valueOf(td.getTrancationtime()));
			ps.setDate(4, Date.valueOf(td.getTrancationDate()));
			ps.setDouble(5, td.getBalanceamount());
			ps.setString(6, td.getTrancationStatus());
			ps.setLong(7, td.getCustomeraccountnumber());
			int result=ps.executeUpdate();
			if(result!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	public static void getStatement(ResultSet rs1)
	{
		String select_trancation_Details="select * from trancation_details where customer_account_number=?";
		
		try {
			Connection connection=DatabaseConnection.formysqlConnection();
			PreparedStatement ps=connection.prepareStatement(select_trancation_Details);
			ps.setLong(1, rs1.getLong("customer_Account_Number"));
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					System.out.println("Transcation Account number:"+rs.getLong("customer_account_number"));
					System.out.println("Trancation Id:"+rs.getInt("idtrancation_id"));
					System.out.println("Trancation Type:"+rs.getString("trancation_type"));
					System.out.println("Trancation Amount:"+rs.getDouble("trancation_amount"));
					System.out.println("Trancation time:"+rs.getTime("trancation_time"));
					System.out.println("Trancation date:"+rs.getDate("trancation_date"));
					System.out.println("Balance Amount:"+rs.getDouble("balence_amount"));
					System.out.println("Trancation Status:"+rs.getString("trancation_ststus"));
					System.out.println("*************************************************************************");
					
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
