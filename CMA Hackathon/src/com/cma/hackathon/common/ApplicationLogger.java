package com.cma.hackathon.common;

import org.apache.log4j.Logger;


/**
 * @author 251699
 * 
 * 
 * This class Application logger is for the
 * logging purpose.
 * 
 * Copyright 2013 CTS.
 *
 */
public class ApplicationLogger {
	
	// Creation & retrieval methods:
	private Logger logger = null;
	
	public ApplicationLogger(String name){
		logger = Logger.getLogger(name);
	}

	public void info(Object message) {
		logger.info(message);
	}
	
	public void debug(String message, Throwable object) {
		logger.debug(message,object);
	}
}
