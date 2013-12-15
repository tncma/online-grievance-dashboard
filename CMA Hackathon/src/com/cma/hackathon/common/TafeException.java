package com.cma.hackathon.common;

public final class TafeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TafeException() { super(); }
	  public TafeException(String message) { super(message); }
	  public TafeException(String message, Throwable cause) { super(message, cause); }
	  public TafeException(Throwable cause) { super(cause); }

}
