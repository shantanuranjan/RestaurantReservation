package com.reservation.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.reservation.appresponse.AppResponse;
import com.reservation.daos.ReserveDAO;
import com.reservation.exception.AppException;
import com.reservation.models.Reservation;
import com.reservation.models.Seating;

@Path("/reserve")
public class ReserveController {
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse addReservation(Reservation res) {
		AppResponse resp = new AppResponse();

		try {
			ReserveDAO dao = new ReserveDAO();
			res = dao.addReservation(res);
			resp.setMessage("Reservation has been added to the system.");
			resp.setPayload(res);
		} catch (AppException e) {
			
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll() {

		AppResponse resp = new AppResponse();

		try {
			ReserveDAO dao = new ReserveDAO();
			List<Reservation> resList = dao.getAll();
			resp.setPayload(resList);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/customer/{search}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getCustomer(@PathParam("search") String search) {

		AppResponse resp = new AppResponse();

		try {
			ReserveDAO dao = new ReserveDAO();
			Reservation res = dao.search(search);
			resp.setPayload(res);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/reservation/{reservationid}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse searchReservation(@PathParam("reservationid") int reservationid) {

		AppResponse resp = new AppResponse();

		try {
			ReserveDAO dao = new ReserveDAO();
			Reservation res = dao.searchReservation(reservationid);
			resp.setPayload(res);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@POST
	@Path("/update/{reservationid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse updateReservation(Reservation res,@PathParam("reservationid") int reservationid) {

		AppResponse resp = new AppResponse();

		try {
			
			ReserveDAO dao = new ReserveDAO();
			 res = dao.updateReservation(res,reservationid);
			resp.setPayload(res);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/seating")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getSeating() {

		AppResponse resp = new AppResponse();

		try {
			ReserveDAO dao = new ReserveDAO();
			List<Seating> seatList = dao.getSeating();
			resp.setPayload(seatList);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
}
