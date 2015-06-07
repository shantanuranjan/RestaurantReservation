package com.reservation.models;


public class Reservation {

	private int id;
	private String reservationdate;
	private String reservationtime;
	private String firstname;
	private String lastname;
	private int phone;
	private String email;
	private int partysize;
	private String specialneeds;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReservationdate() {
		return reservationdate;
	}
	public void setReservationdate(String reservationdate) {
		this.reservationdate = reservationdate;
	}
	public String getReservationtime() {
		return reservationtime;
	}
	public void setReservationtime(String reservationtime) {
		this.reservationtime = reservationtime;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPartysize() {
		return partysize;
	}
	public void setPartysize(int partysize) {
		this.partysize = partysize;
	}
	public String getSpecialneeds() {
		return specialneeds;
	}
	public void setSpecialneeds(String specialneeds) {
		this.specialneeds = specialneeds;
	}
	
}
