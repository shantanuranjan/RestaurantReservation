package com.reservation.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.reservation.exception.AppException;
import com.reservation.models.Message;
import com.reservation.models.Owner;
import com.reservation.models.Reservation;
import com.reservation.utils.DBConnector;

public class OwnerDAO {
	
	public Owner searchOwner(String usernameOwner) throws AppException{
		Connection con=DBConnector.connect();
		PreparedStatement ps=null;
		Owner own=new Owner();
		ResultSet rs=null;
		try{
				String sql="select * from owner where username=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, usernameOwner);
				
			    rs=ps.executeQuery();
		
				if(rs.next()){
					
					own.setUsername(rs.getString("username"));
					own.setPassword(rs.getString("password"));
					own.setName(rs.getString("name"));
					own.setAddress(rs.getString("address"));
					own.setCity(rs.getString("city"));
					own.setDob(rs.getString("dob"));
					own.setEmail(rs.getString("email"));
					own.setPhone(rs.getInt("phone"));
				}
				else {
					throw new AppException("Owner with usernme '" + usernameOwner + "' does not exist in the system.");
				}
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new AppException("Owner with username '" + usernameOwner + "' does not exist in the system."+ps);
		}
		finally {
			DBConnector.closeResources(ps, rs, con);
		}
		return own;
		}
	
	public Owner verifyLogin(String username,String password) throws AppException{
	Connection con=DBConnector.connect();
	PreparedStatement ps=null;
	Owner own=new Owner();
	ResultSet rs=null;
	try{
			String sql="select * from owner where username=? and password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
		    rs=ps.executeQuery();
	
			if(rs.next()){
				
				own.setUsername(rs.getString("username"));
				own.setPassword(rs.getString("password"));				
			}
			else {
				throw new AppException("Owner with username'" + username + "' does not exist in the system.");
			}
	}
	catch(SQLException e){
		e.printStackTrace();
		throw new AppException("Owner with username '" + username + "' does not exist in the system."+ps);
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	return own;
	}
public Owner updateOwner(Owner own,String userOwner) throws AppException{
		Connection con=DBConnector.connect();
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		try{
				String sql="update owner set username=?,password=?,name=?,dob=?,email=?,address=?,city=?,phone=?"
						+ " where username=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, own.getUsername());
				ps.setString(2, own.getPassword());
				ps.setString(3, own.getName());
				ps.setString(4, own.getDob());
				ps.setString(5, own.getEmail());
				ps.setString(6, own.getAddress());
				ps.setString(7, own.getCity());
				ps.setInt(8, own.getPhone());
				ps.setString(9, userOwner);
				ps.executeUpdate();
		
			
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new AppException("Owner with username =" + userOwner + " does not exist in the system."+ps);
		}
		finally {
			DBConnector.closeResources(ps, rs, con);
		}
		return own;
		}

public Message sendMessage(Message msg) throws AppException{
	Connection con = DBConnector.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
		
		ps=con.prepareStatement("insert into message(subject,message,name,phone,email) values(?,?,?,?,?)");
		ps.setString(1,msg.getSubject());
		ps.setString(2, msg.getMessage());
		ps.setString(3, msg.getName());
		ps.setInt(4, msg.getPhone());
		ps.setString(5, msg.getEmail());
		
		ps.executeUpdate();
	
	}catch (SQLException e) {
		e.printStackTrace();
		throw new AppException("Error in adding message to the database.", e.getCause());
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	
	return msg;
}

public String removeReservation(int id) throws AppException{
	Connection con = DBConnector.connect();
	PreparedStatement ps = null,ps1=null;
	ResultSet rs = null;
	try{
		ps1=con.prepareStatement("delete from reservation where reservationid=?");
		ps1.setInt(1, id);
		ps1.executeUpdate();
		
		ps=con.prepareStatement("update seating set reservationid=?,status=? where reservationid=?");
		ps.setInt(1, 0);
		ps.setString(2,"available");
		ps.setInt(3, id);
		ps.executeUpdate();
		
		
			return "success";
		
	}catch (SQLException e) {
		e.printStackTrace();
		throw new AppException("Error in removing reservation from the database.", e.getCause());
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
}

public Reservation addReservationOwner(Reservation res,int tableno) throws AppException{
	Connection con = DBConnector.connect();
	PreparedStatement ps = null,ps1=null;
	ResultSet rs = null;
	
	try{
		ps = con.prepareStatement("INSERT INTO reservation (reservationdate,reservationtime,firstname,lastname,phone,email,partysize,specialneeds) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, res.getReservationdate());
		ps.setString(2, res.getReservationtime());
		ps.setString(3, res.getFirstname());
		ps.setString(4, res.getLastname());
		ps.setInt(5, res.getPhone());
		ps.setString(6, res.getEmail());
		ps.setInt(7, res.getPartysize());
		ps.setString(8, res.getSpecialneeds());		
		ps.executeUpdate();
		
		rs = ps.getGeneratedKeys();
		
		if(rs.next())
		{
			res.setId(rs.getInt(1));
		}
		ps1=con.prepareStatement("update seating set status=?,reservationid=? where tableno=?");
		ps1.setString(1, "occupied");
		ps1.setInt(2, res.getId());
		ps1.setInt(3, tableno);
		ps1.executeUpdate();
		
		
	}catch (SQLException e) {
		e.printStackTrace();
		throw new AppException("Error in removing reservation from the database.", e.getCause());
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	
	return res;
}
}
