package com.cma.hackathon.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.cma.hackathon.rest.ImportService;


/**
 * @author 251699
 * 
 * 
 * This class DBConnection is responsible for
 * creating a database connection.
 * 
 * Copyright 2013 CTS.
 *
 */
public class DBConnection {
	
	public static final Logger log = Logger.getLogger(DBConnection.class
			.getName());
	
	/**
	 * Gets a connection reference using data source.
	 * @return a connection object
	 * @throws Exception when there is an issue in connecting
	 * to the database
	 */
	public static Connection getDataSourceConnection() throws Exception {
		Connection connection =null;
			  
			try{ 
				InitialContext context = new InitialContext();
				DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/tafeDB");
				connection = dataSource.getConnection();
			    
				//Class.forName("com.mysql.jdbc.Driver");
				//Connection conn = null;
				//connection = DriverManager.getConnection("jdbc:mysql://10.11.97.27:3306/cmacomp","root", "root");
				
				
			    log.info("Created connection to database.");
			  } catch (SQLException ex) {
				  log.info(""+ex.fillInStackTrace());
				  log.info("Couldn't create connection." + 
			    ex.getMessage());
			    throw new 
			      TafeException("Couldn't open connection to database: "
			    +ex.getMessage());
			  }
			return connection;  
	}
	
	/**
	 * Close the database connection
	 * @param dbConnection the connection
	 * @param stmt the statement
	 * @param rset the result set
	 * @throws Exception throws when there is an issue
	 */
	public static void close(Connection dbConnection, Statement stmt, ResultSet rset) throws Exception {
		if (dbConnection != null) {
			try {
				if (rset != null){
					rset.close();	
				}
				if (stmt != null) {
					stmt.close();
				}
				dbConnection.close();
				log.info("Connection Closed Successfully");
			} catch (SQLException e) {
				 log.info("Couldn't open connection to database:" + 
						    e.getMessage());
				throw new 
			      TafeException("Couldn't open connection to database: "
			    +e.getMessage());
			}
		}
	}
}