package com.reservation.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.reservation.exception.AppException;
import com.reservation.models.Reservation;
import com.reservation.models.Seating;

import com.reservation.utils.DBConnector;

public class ReserveDAO {
	
public Reservation addReservation(Reservation res) throws AppException {
		
		Connection con = DBConnector.connect();
		PreparedStatement ps = null,ps1=null,ps2=null;
		
		ResultSet rs = null;
		ArrayList<Integer> table=new ArrayList<>();
		int seatingno;
		
		try {
			ps1=con.prepareStatement("select * from seating where status=?");
			ps1.setString(1, "available");
			rs=ps1.executeQuery();
			while(rs.next()){
				table.add(rs.getInt("tableno"));				
				
			}
			Random random = new Random();
			seatingno=table.get(random.nextInt(table.size()));
			
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
			ps2=con.prepareStatement("update seating set status=?,reservationid=? where tableno=?");
			ps2.setString(1, "occupied");
			ps2.setInt(2, res.getId());
			ps2.setInt(3, seatingno);
			ps2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Error in adding reservation to the database.", e.getCause());
		}
		finally {
			DBConnector.closeResources(ps, rs, con);
		}
		
		return res;
	}

public List<Reservation> getAll() throws AppException {
	List<Reservation> resList = new ArrayList<Reservation>();
	
	Connection con = DBConnector.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		ps = con.prepareStatement("SELECT * FROM reservation");
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			Reservation res = new Reservation();
			
			res.setId(rs.getInt("reservationid"));
			res.setReservationdate(rs.getString("reservationdate"));
			res.setReservationtime(rs.getString("reservationtime"));
			res.setFirstname(rs.getString("firstname"));
			res.setLastname(rs.getString("lastname"));
			res.setPhone(rs.getInt("phone"));
			res.setEmail(rs.getString("email"));
			res.setPartysize(rs.getInt("partysize"));
			res.setSpecialneeds(rs.getString("specialneeds"));
			
			resList.add(res);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new AppException("Error in fetching records from database.", e.getCause());
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	
	return resList;
}
public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
   
    return true;
}
public Reservation search(String search) throws AppException{
	Connection con=DBConnector.connect();
	PreparedStatement ps=null;
	Reservation res=new Reservation();
	ResultSet rs=null;
	try{
		if(search.contains("@")){
			String sql="select * from reservation where email=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, search);
		}
		else if(isInteger(search)){
			String sql="select * from reservation where phone=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, search);
		}
		else{
			String sql="select * from reservation where firstname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, search);
		}			
		    rs=ps.executeQuery();	
			if(rs.next()){
				res.setId(rs.getInt("reservationid"));
				res.setReservationdate(rs.getString("reservationdate"));
				res.setReservationtime(rs.getString("reservationtime"));
				res.setFirstname(rs.getString("firstname"));
				res.setLastname(rs.getString("lastname"));
				res.setPhone(rs.getInt("phone"));
				res.setEmail(rs.getString("email"));
				res.setPartysize(rs.getInt("partysize"));
				res.setSpecialneeds(rs.getString("specialneeds"));				
			}
			else {
				throw new AppException("Error in fetching records from database.");
			}
	}
	catch(SQLException e){
		e.printStackTrace();
		throw new AppException("Error in fetching records from database.", e.getCause());
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	return res;
	}

public Reservation searchReservation(int reservationid) throws AppException {
	
	
	Connection con = DBConnector.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Reservation res = new Reservation();
	try {
		ps = con.prepareStatement("SELECT * FROM reservation where reservationid=?");
		ps.setInt(1, reservationid);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			
			
			res.setId(rs.getInt("reservationid"));
			res.setReservationdate(rs.getString("reservationdate"));
			res.setReservationtime(rs.getString("reservationtime"));
			res.setFirstname(rs.getString("firstname"));
			res.setLastname(rs.getString("lastname"));
			res.setPhone(rs.getInt("phone"));
			res.setEmail(rs.getString("email"));
			res.setPartysize(rs.getInt("partysize"));
			res.setSpecialneeds(rs.getString("specialneeds"));	
		}
		else {
			throw new AppException("Error in fetching records from database.");
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new AppException("Error in fetching records from database.", e.getCause());
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	
	return res;
}

public Reservation updateReservation(Reservation res,int reservationid) throws AppException{
	Connection con=DBConnector.connect();
	PreparedStatement ps=null;
	
	ResultSet rs=null;
	try{
			String sql="update reservation set reservationdate=?,reservationtime=?,firstname=?,lastname=?,phone=?,email=?,partysize=?,specialneeds=?"
					+ " where reservationid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, res.getReservationdate());
			ps.setString(2, res.getReservationtime());
			ps.setString(3, res.getFirstname());
			ps.setString(4, res.getLastname());
			ps.setInt(5, res.getPhone());
			ps.setString(6, res.getEmail());
			ps.setInt(7, res.getPartysize());
			ps.setString(8, res.getSpecialneeds());
			ps.setInt(9, reservationid);
			ps.executeUpdate();
	
		
	}
	catch(SQLException e){
		e.printStackTrace();
		throw new AppException("Reservation with id =" + reservationid + " does not exist in the system."+ps);
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	return res;
	}

public List<Seating> getSeating() throws AppException{
	
	Connection con=DBConnector.connect();
	PreparedStatement ps=null;
	List<Seating> seatList = new ArrayList<Seating>();
	ResultSet rs=null;
	try{
		ps = con.prepareStatement("select tableno,status,s.reservationid,reservationtime,"
				+ "reservationdate,partysize from seating s  left outer join reservation r "
				+ " on s.reservationid=r.reservationid;");
		
		rs = ps.executeQuery();
		while(rs.next()) {
			
			Seating seat=new Seating();
			
			seat.setTableno(rs.getInt("tableno"));
			seat.setStatus(rs.getString("status"));
			seat.setReservationid(rs.getInt("reservationid"));
			
			seat.setReservationdate(rs.getString("reservationdate"));
			seat.setReservationtime(rs.getString("reservationtime"));
			seat.setPartysize(rs.getInt("partysize"));
			
		seatList.add(seat);
			
		
		}
	}
	catch(SQLException e){
		e.printStackTrace();
		throw new AppException("Error in fetching data from system");
	}
	finally {
		DBConnector.closeResources(ps, rs, con);
	}
	return seatList;
}






}
