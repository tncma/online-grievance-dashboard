package com.cma.hackathon.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.cma.hackathon.common.ApplicationLogger;
import com.cma.hackathon.common.ImageManipulation;
import com.cma.hackathon.common.TafeException;
import com.cma.hackathon.dao.UploadDAO;
import com.cma.hackathon.entity.ComplaintDetails;

@Path("/upload")
public class UploadService {
	String compDate = null;
	String sendCompDate = null;
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
    
	private static final ApplicationLogger log = new ApplicationLogger(UploadService.class.getName());
	
	/**
	 * UploadService is the service running" 
	 * @return String
	 */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {
         return "Upload webservice is ready!";
    }
    
    /**
     * Get status of the task
     * @param FSR ID
     * @return 
     * @throws TafeException
     */
    
	@POST
	@Path("/getstatus")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getTaskStatus(String efsrID) throws TafeException{
		String taskCompDate = null;
		UploadDAO uploadDAO = new UploadDAO();
			
		try{
			log.info("GET Task Status Started................. ");
			
			taskCompDate = uploadDAO.getTaskStatus(efsrID);
			
			log.info("GET Task Status Completed............... ");
		}catch (Exception e) {
			
			log.info("GET Task Status EFSR_ID : "+efsrID+" * Exception Caught :: "+e.fillInStackTrace());
			log.info("GET Task Status EFSR_ID : "+efsrID+" * NO CONNECTION while updating IMEI no and Reg ID in table");
			
		}
		
		return taskCompDate;
    }
    
    /**
     * Save the eFSR Details.
     * @param eFSR Details
     * @return Status of the Upload
     * @throws TafeException
     */
/*    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String getLoginDetails(MultivaluedMap<String, String> loginParams) throws TafeException{
    	String uploaded = "FAILED";
		UploadDAO uploadDAO = new UploadDAO();
		
		try{
			final Encryption encryption = new Encryption();
			String efsrId= encryption.DecryptString(loginParams.getFirst("efsrId"), key);
			log.info("*********Upload Service for text + 4 images started for " +efsrId+"**********");
					
			List<String> queries = generateQueryStringToUpload(loginParams);	
			uploaded = uploadDAO.saveFSRDetail(queries);
			
			
			storeCustImages(loginParams);
			log.info("*********Upload Service for text + 4 images completed for " +efsrId+"**********");
			if(uploaded.equalsIgnoreCase("Success")){
				return sendCompDate;	
			}
			
		  }
		
		
		  catch (Exception e) {
			  e.printStackTrace();
			  log.info("Upload Service : Exception Caught :: "+e.fillInStackTrace());
			  log.info("Upload Service : NO CONNECTION getting Upload");
			  
			  return "FAILED";
		  }
		return uploaded;
					
	}
*/
    /**
     * Save the eFSR Details.
     * @param eFSR Details
     * @return Status of the Upload
     * @throws TafeException
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String  getCompliantDetails(MultivaluedMap<String, String> loginParams) throws TafeException{
    	String uploaded = "FAILED";
		UploadDAO uploadDAO = new UploadDAO();
		
		try{
			String title= loginParams.getFirst("title");
			String details= loginParams.getFirst("details");
			String area= loginParams.getFirst("area");
			String locality= loginParams.getFirst("locality");
			String street= loginParams.getFirst("street");
			String landmark= loginParams.getFirst("landmark");
			String mobile= loginParams.getFirst("mobile");
			String name= loginParams.getFirst("name");
			String address= loginParams.getFirst("address");
			String pincode= loginParams.getFirst("pincode");
			String email= loginParams.getFirst("email");
			String compNo = "Complaint No."+ uploadDAO.getCompCount();
			String status = "Assigned";
			log.info("*********Upload Service -text completed**********");
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Date d = new Date();
			String strDate = dateFormat.format(d);
			String imgComp = loginParams.getFirst("photo1");
			ImageManipulation.stringToImage(imgComp,name+"_"+mobile+"_"+strDate);
			
			String dgQuery = "INSERT INTO TBL_COMPDETAILS (title,details,area," +
					"locality,street,landmark,name,name,address,pincode,email,filename,compNo,status) " +
					"VALUES('"+title+"','"+details+"','"+area+"','"+locality+"',"+street+"," +
							""+landmark+",'"+mobile+"',"+name+",'"+address+"','"+pincode+"','"+email+"','"+name+"_"+mobile+"_"+strDate+"','"+compNo+"','"+status+"')";
			
			
			uploaded = uploadDAO.saveFSRDetail(dgQuery);
			
			
			log.info("*********Upload Service- images completed**********");
			
			return "Success";
			
		  }
		
		
		  catch (Exception e) {
			  e.printStackTrace();
			  log.info("Upload Service : Exception Caught :: "+e.fillInStackTrace());
			  log.info("Upload Service : NO CONNECTION getting Upload");
			  
			  return "FAILED";
		  }
					
	}

    /**
     * Update status as In Progress
     * @param FSR ID
     * @return 
     * @throws TafeException
     */
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public ComplaintDetails fetchComplaintsDetails() throws TafeException{
		ComplaintDetails complaintDetails = null;
		UploadDAO uploadDAO = new UploadDAO();
			
		try{
			log.info("Update Task Status Started................. ");
			
			complaintDetails = uploadDAO.getCompDetails();
			
			log.info("Update Task Status Completed............... ");
		}catch (Exception e) {
			
			log.info("Update Task Status EFSR_ID * Exception Caught :: "+e.fillInStackTrace());
			log.info("Update Task Status EFSR_ID * NO CONNECTION while updating IMEI no and Reg ID in table");
			
		}
		
		return complaintDetails;
    }

	
	
}
