package com.cma.hackathon.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.JSONObject;


@Path("ImportService")
public class ImportService {

	public static final Logger LOGGER = Logger.getLogger(ImportService.class
			.getName());

	@POST
	@Path("/generatetripdata")
	@Consumes({ "application/json" })
	public String generateTripData(String jsonString){
		String tripDetails = null;
		try{} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			JSONObject object = new JSONObject();
			try {
				
			} catch (Exception e1) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			return null;
		}
		return tripDetails;
	}

	
}
