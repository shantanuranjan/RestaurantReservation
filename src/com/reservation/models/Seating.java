package com.reservation.models;

public class Seating {

	private int tableno;
	private String status;
	private int reservationid;
	private String reservationdate;
	private String reservationtime;
	private int partysize;
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
	public int getPartysize() {
		return partysize;
	}
	public void setPartysize(int partysize) {
		this.partysize = partysize;
	}
	public int getTableno() {
		return tableno;
	}
	public void setTableno(int tableno) {
		this.tableno = tableno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getReservationid() {
		return reservationid;
	}
	public void setReservationid(int reservationid) {
		this.reservationid = reservationid;
	}
}
