package com.reservation.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ReservationApp extends ResourceConfig{

	public ReservationApp(){
		packages("com.reservation.rest");
	}
}
