/**
 * 
 */
package com.cma.hackathon.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.cma.hackathon.common.ApplicationLogger;
import com.cma.hackathon.common.TafeException;
import com.cma.hackathon.dao.UpdateTaskStatusDAO;

/**
 * @author 251699
 *
 */
@Path("/updatestatus")
public class UpdateTaskStatusService {
	
	/**
	 *  The @Context annotation allows us to have certain contextual objects
	 *  injected into this class.
     *	UriInfo object allows us to get URI information (no kidding).
	 */
    @Context
    UriInfo uriInfo;
    
    /**
     *  Another "injected" object. This allows us to use the information that's
     *  part of any incoming request.
     *  We could, for example, get header information, or the requestor's address.
     */
    @Context
    Request request;
	
	private static final ApplicationLogger log = new ApplicationLogger(UpdateTaskStatusService.class.getName());
	/**
	 * UpdateTaskstatus webservice "is the service running" 
	 * @return String
	 */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {
         return "UpdateTaskstatus webservice is ready";
    }
    
    /**
     * Update status as In Progress
     * @param FSR ID
     * @return 
     * @throws TafeException
     */
	@POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateTaskStatus(String efsrID) throws TafeException{
		String taskStatus = null;
		UpdateTaskStatusDAO updateStatusDAO = new UpdateTaskStatusDAO();
			
		try{
			log.info("Update Task Status Started................. ");
			
			taskStatus = updateStatusDAO.updateTaskStatus(efsrID);
			
			log.info("Update Task Status Completed............... ");
		}catch (Exception e) {
			
			log.info("Update Task Status EFSR_ID : "+efsrID+" * Exception Caught :: "+e.fillInStackTrace());
			log.info("Update Task Status EFSR_ID : "+efsrID+" * NO CONNECTION while updating IMEI no and Reg ID in table");
			
		}
		
		return taskStatus;
    }
		
}
