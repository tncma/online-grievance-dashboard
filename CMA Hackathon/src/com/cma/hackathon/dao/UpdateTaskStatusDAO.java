/**
 * 
 */
package com.cma.hackathon.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cma.hackathon.common.ApplicationLogger;
import com.cma.hackathon.common.DBConnection;
import com.cma.hackathon.common.TafeException;

/**
 * @author 376731
 *
 */
public class UpdateTaskStatusDAO {
	
	Connection dbConnection = null;
	Statement stmt = null;
	ResultSet resultSet = null;
	private static final ApplicationLogger log = new ApplicationLogger(UpdateTaskStatusDAO.class.getName());

	 public String updateTaskStatus(String efsrID) throws TafeException{
		   			
			try{
				dbConnection = DBConnection.getDataSourceConnection();
				stmt = dbConnection.createStatement();
					
				log.info("Update Task Status using EFSR_ID : "+efsrID);
				
				resultSet=stmt.executeQuery("UPDATE TBL_FSRDETAIL SET FSR_STATUS = 'INPROGRESS' WHERE FSR_ID = '"+efsrID+"'");
				
				log.info("Updated Task Status using EFSR_ID : "+efsrID);
			
			   // updateStatus = resultSet.rowUpdated();
								
				
			  }catch (Exception e) {
				  log.debug(e.getMessage(), e.fillInStackTrace());
				  log.info("EFSR_ID: "+efsrID+" * NO CONNECTION while updating task status using EFSR_ID");	
			}finally{
				try {
					DBConnection.close(dbConnection, stmt, resultSet);
				} catch (Exception e) {
					log.debug(e.getMessage(), e.fillInStackTrace());
				}
			}
			
			return "SUCCESS";				
		}
}
