/**
 * 
 */
package com.cma.hackathon.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import com.cma.hackathon.common.ApplicationLogger;
import com.cma.hackathon.common.DBConnection;
import com.cma.hackathon.common.TafeException;
import com.cma.hackathon.entity.ComplaintDetails;

/**
 * @author 251699
 *
 */
public class UploadDAO {

	 Connection dbConnection = null;
	 Statement stmt = null;
	 ResultSet resultSet = null;
	 private static final ApplicationLogger log = new ApplicationLogger(UploadDAO.class.getName());
	
	public String saveFSRDetail(List<String> queries) throws TafeException {
		
		try {
			dbConnection = DBConnection.getDataSourceConnection();
			dbConnection.setAutoCommit(false);
			stmt = dbConnection.createStatement();
			
			
			log.info("Started Inserting Systems Data...");
			
			for (Iterator<String> iterator = queries.iterator(); iterator.hasNext();) {
				String query = (String) iterator.next();
				stmt.addBatch(query); 
				log.info("*********QUERY **********"+query);
			}
			int[] updateCount = stmt.executeBatch();
			
			dbConnection.commit();
			log.info("Data inserted Successfully : "+updateCount);
			
		} /*catch (BatchUpdateException e){
			try {
				System.out.println("Batch update exception message : " + e.getMessage());
				System.out.println("Batch update exception error code : " + e.getErrorCode());
				System.out.println("Batch update exception Localised Message : " + e.getLocalizedMessage());
				System.out.println("Batch update exception SQLState : " + e.getSQLState());
				dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			finally {
				return "Success";
			}
			
		}*/
		
		 catch (SQLException e) {
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			log.debug(e.getMessage(), e.fillInStackTrace());
			throw new TafeException(e.getMessage(), e.fillInStackTrace());
		}catch (Exception ex) {
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			log.debug(ex.getMessage(), ex.fillInStackTrace());
			throw new TafeException(ex.getMessage(), ex.fillInStackTrace());
		}finally{
			try {
				DBConnection.close(dbConnection, stmt, null);
			} catch (Exception e) {
				log.debug(e.getMessage(), e.fillInStackTrace());
				throw new TafeException(e.getMessage(), e.fillInStackTrace());
			}
		}
		
		return "Success";
	}

	public int getMaxId() throws TafeException {
		ResultSet resultSet = null;
		int surResId = 0;
		try {
			
			dbConnection = DBConnection.getDataSourceConnection();
			stmt = dbConnection.createStatement();
			
			if (dbConnection != null) {
			
		        log.info("Fetch the MAX OF SURVEY RESPONSES ");
		        resultSet = stmt.executeQuery("select max(RESP_ID) from TBL_FSR_SURVEY_RESPONSES");
				while (resultSet.next()){
					surResId = resultSet.getInt(1);
					log.info("Fetched the MAX  SURVEY RESPONSES : "+surResId);
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage(), e.fillInStackTrace());
		}finally{
			try {
				DBConnection.close(dbConnection, stmt, resultSet);
			} catch (Exception e) {
				log.debug(e.getMessage(), e.fillInStackTrace());
			}
		}
		return surResId;
	}
	
	public String getTaskStatus(String efsrID) {
		String compDate = "NOTCOMPLETED";
	try{
		dbConnection = DBConnection.getDataSourceConnection();
		stmt = dbConnection.createStatement();
		
		if (dbConnection != null) {
			log.info("Get Task Status using EFSR_ID : "+efsrID);
	        
			resultSet=stmt.executeQuery("SELECT TO_CHAR(FSR_DATE_TM_COMPLETED,'DD-MON-YYYY HH24:MI:SS') COMPDATE FROM TBL_FSRDETAIL WHERE FSR_ID = '"+efsrID+"' AND FSR_STATUS = 'COMPLETED'");
			while (resultSet.next()){
				compDate = resultSet.getString(1);
				log.info("Get Task Status : "+compDate+" using EFSR_ID : "+efsrID);
			}
		}
	  }catch (Exception e) {
		  log.debug(e.getMessage(), e.fillInStackTrace());
		  log.info("EFSR_ID: "+efsrID+" * NO CONNECTION while get task status using EFSR_ID");	
	}finally{
		try {
			DBConnection.close(dbConnection, stmt, resultSet);
		} catch (Exception e) {
			log.debug(e.getMessage(), e.fillInStackTrace());
		}
	}
	
	return compDate;				

}

	public String saveFSRDetail(String dgQuery) {
		// TODO Auto-generated method stureturn null;

		
		try {
			dbConnection = DBConnection.getDataSourceConnection();
			dbConnection.setAutoCommit(false);
			stmt = dbConnection.createStatement();
			
			
			log.info("Started Inserting Systems Data...");
			
			
				log.info("*********QUERY **********"+dgQuery);
			
			int updateCount = stmt.executeUpdate(dgQuery);
			
			dbConnection.commit();
			log.info("Data inserted Successfully : "+updateCount);
			
		} 
		
		 catch (SQLException e) {
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			log.debug(e.getMessage(), e.fillInStackTrace());
		}catch (Exception ex) {
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			log.debug(ex.getMessage(), ex.fillInStackTrace());
		}finally{
			try {
				DBConnection.close(dbConnection, stmt, null);
			} catch (Exception e) {
				log.debug(e.getMessage(), e.fillInStackTrace());
			}
		}
		
		return "Success";
	
	}

	public ComplaintDetails getCompDetails() {
		ComplaintDetails compDet = null;
	try{
		dbConnection = DBConnection.getDataSourceConnection();
		stmt = dbConnection.createStatement();
		
		if (dbConnection != null) {
			log.info("Get Task Status using EFSR_ID : ");
	        
			resultSet=stmt.executeQuery("SELECT title,details,area,locality,street,landmark,name,name,address,pincode,email,filename,compNo,status FROM TBL_COMPDETAILS");
			while (resultSet.next()){
				compDet = new ComplaintDetails();
				compDet.setTitle(resultSet.getString(1));
				compDet.setDetails(resultSet.getString(2));
				compDet.setArea(resultSet.getString(3));
				compDet.setLocality(resultSet.getString(4));
				compDet.setStreet(resultSet.getString(5));
				compDet.setLandmark(resultSet.getString(6));
				compDet.setMobile(resultSet.getString(7));
				compDet.setName(resultSet.getString(8));
				compDet.setAddress(resultSet.getString(9));
				compDet.setPincode(resultSet.getString(10));
				compDet.setFilename(resultSet.getString(11));
				compDet.setCompNo(resultSet.getString(12));
				compDet.setStatus(resultSet.getString(13));
				
				log.info("Get Task Status : "+compDet+" using EFSR_ID : ");
			}
		}
	  }catch (Exception e) {
		  log.debug(e.getMessage(), e.fillInStackTrace());
		  log.info("EFSR_ID:  * NO CONNECTION while get task status using EFSR_ID");	
	}finally{
		try {
			DBConnection.close(dbConnection, stmt, resultSet);
		} catch (Exception e) {
			log.debug(e.getMessage(), e.fillInStackTrace());
		}
	}
	
	return compDet;				

}

	public int getCompCount() {
		int count = 0;
	try{
		dbConnection = DBConnection.getDataSourceConnection();
		stmt = dbConnection.createStatement();
		
		if (dbConnection != null) {
	        
			resultSet=stmt.executeQuery("SELECT count(mobile) from TBL_COMPDETAILS");
			while (resultSet.next()){
				count = resultSet.getInt(1);
				log.info("Get Task Status : "+count+" using EFSR_ID : ");
			}
			count = count + 1;
		}
	  }catch (Exception e) {
		  log.debug(e.getMessage(), e.fillInStackTrace());
		  log.info("EFSR_ID: * NO CONNECTION while get task status using EFSR_ID");	
	}finally{
		try {
			DBConnection.close(dbConnection, stmt, resultSet);
		} catch (Exception e) {
			log.debug(e.getMessage(), e.fillInStackTrace());
		}
	}
	
	return count;				

}

	

	
}
