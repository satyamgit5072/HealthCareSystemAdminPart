package com.healthcare.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="user2")
public class User 
{
	@NotNull(message="user Id should not be empty")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@NotEmpty(message="user name should not be empty")
	@Column(nullable=false)
	private String userName;
	
	@NotEmpty(message="user password should not be empty")
	@Column(nullable=false)
	private String userPassword;
	
	@NotEmpty(message="Email should not be empty")
	@Email(message="email must be a valid one")
	@Column(nullable=false)
	private String emailId;
	
	@NotEmpty(message="contact number should not be empty")
	@Column(nullable=false)
	@Pattern(regexp="^[0-9]{10}$",message="phone number must be a 10 digit number")
	private String contactNo;
	
	@OneToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL,mappedBy = "user")
	private Appointment appointment;

	public User()
	{
		
	}
	
	public User(String userName, String userPassword, String emailId, String contactNo)
	{
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.emailId = emailId;
		this.contactNo = contactNo;
	}

	public int getUserId() 
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPassword() 
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId) 
	{
		this.emailId = emailId;
	}

	public String getContactNo() 
	{
		return contactNo;
	}

	public void setContactNo(String contactNo) 
	{
		this.contactNo = contactNo;
	}

	public Appointment getAppointment()
	{
		return appointment;
	}

	public void setAppointment(Appointment appointment) 
	{
		this.appointment = appointment;
	}
	
}
