package com.reservation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {

	private static final String URL="jdbc:mysql://localhost:3306/test";
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			System.err.println("error loading driver"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Connection connect(){
		Connection con=null;
		try {
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("Connection Successful");
		} catch (SQLException e) {
			System.err.println("Connection error:"+e.getMessage());
			e.printStackTrace();
		}
		
		return con;
	}
public static void closeResources(PreparedStatement ps, ResultSet rs, Connection con){
	try {
		if(ps != null)
		{
			ps.close();
		}
		if(rs != null)
		{
			rs.close();
		}
		if(con != null)
		{
			con.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

}
