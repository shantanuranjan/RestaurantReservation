package com.reservation.rest.controller;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.reservation.appresponse.AppResponse;
import com.reservation.daos.OwnerDAO;
import com.reservation.daos.ReserveDAO;
import com.reservation.exception.AppException;
import com.reservation.models.Message;
import com.reservation.models.Owner;
import com.reservation.models.Reservation;


@Path("/owner")
public class OwnerController {

	@GET
	@Path("/verifylogin/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public  AppResponse verifyLogin(@PathParam("username") String username,@PathParam("password") String password){
		
		AppResponse resp = new AppResponse();

		try {
			OwnerDAO dao=new OwnerDAO();
			Owner own = dao.verifyLogin(username,password);
			
			resp.setPayload(own);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	
	}
	@POST
	@Path("/update/{userOwner}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse addReservation(Owner own,@PathParam("userOwner") String userOwner) {
		AppResponse resp = new AppResponse();

		try {
			OwnerDAO dao = new OwnerDAO();
			own = dao.updateOwner(own,userOwner);
			resp.setMessage("Information updated");
			resp.setPayload(own);
		} catch (AppException e) {
			
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	
	@GET
	@Path("/searchOwner/{usernameOwner}")
	@Produces(MediaType.APPLICATION_JSON)
	public  AppResponse searchOwner(@PathParam("usernameOwner") String usernameOwner){
		
		AppResponse resp = new AppResponse();

		try {
			OwnerDAO dao=new OwnerDAO();
			Owner own = dao.searchOwner(usernameOwner);
			
			resp.setPayload(own);
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	
	}
	
	@POST
	@Path("/message")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse sendMessage(Message msg) {
		AppResponse resp = new AppResponse();

		try {
			OwnerDAO dao = new OwnerDAO();
			msg = dao.sendMessage(msg);
			resp.setMessage("Thank you for Contacting us.Your message has successfully reached to our system.");
			resp.setPayload(msg);
		} catch (AppException e) {
			
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	@POST
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse removeReservation(@PathParam("id") int id) {
		AppResponse resp = new AppResponse();

		try {
			OwnerDAO dao = new OwnerDAO();
			String str = dao.removeReservation(id);
			resp.setMessage("Reservation successfully deleted");
			resp.setPayload(str);
		} catch (AppException e) {
			
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@POST
	@Path("/addOwner/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse addReservationOwner(Reservation res,@PathParam("id") int tableno) {
		AppResponse resp = new AppResponse();

		try {
			OwnerDAO dao = new OwnerDAO();
			res = dao.addReservationOwner(res,tableno);
			resp.setMessage("Reservation has been added to the system.");
			resp.setPayload(res);
		} catch (AppException e) {
			
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
}
