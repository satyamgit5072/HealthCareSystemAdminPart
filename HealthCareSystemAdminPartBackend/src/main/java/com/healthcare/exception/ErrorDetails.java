package com.healthcare.exception;

import java.util.Date;
import java.util.List;

public class ErrorDetails 
{
	private Date timestamp;
	private String message;
	private String details;
    private List<String> msgDetails;

    public ErrorDetails()
    {
    	
    }
    
	public ErrorDetails(Date timestamp, String message, List<String> msgDetails,String details) 
	{
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.msgDetails = msgDetails;
		this.details = details;
	}

	public ErrorDetails(Date timestamp, String message, String details) 
	{
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public List<String> getMsgDetails() 
	{
		return msgDetails;
	}
			
	public Date getTimestamp()
	{
		return timestamp;
	}

	public String getMessage() 
	{
		return message;
	}

	public String getDetails() 
	{
		return details;
	}
}
