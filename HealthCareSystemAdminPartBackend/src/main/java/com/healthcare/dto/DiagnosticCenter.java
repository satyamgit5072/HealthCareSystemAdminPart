package com.healthcare.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="center2")
public class DiagnosticCenter 
{
	@NotNull(message="center Id should not be empty")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int centerId;
	
	@NotEmpty(message="center name should not be empty")
	@Column(nullable=false)
	private String centerName;
	
	@NotEmpty(message="contact number should not be empty")
	@Column(nullable=false)
	@Pattern(regexp="^[0-9]{10}$",message="phone number must be a 10 digit number")
	private String contactNumber;
	
	@NotEmpty(message="address should not be empty")
	@Column(nullable=false)
	private String address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="centerId")	
	private List<Test> listOfTests;
	
	@OneToMany(mappedBy = "center",cascade = CascadeType.ALL)
	List<Appointment> appointmentList;
	
	public int getCenterId() 
	{
		return centerId;
	}

	public void setCenterId(int centerId)
	{
		this.centerId = centerId;
	}

	public String getCenterName() 
	{
		return centerName;
	}

	public void setCenterName(String centerName) 
	{
		this.centerName = centerName;
	}

	public String getContactNumber() 
	{
		return contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	
	public List<Appointment> getAppointmentList()
	{
		return appointmentList;
	}

	
	public void setAppointmentList(List<Appointment> appointmentList) 
	{
		this.appointmentList = appointmentList;
	}

	public List<Test> getListOfTests() 
	{
		return listOfTests;
	}

	public void setListOfTests(List<Test> listOfTests) 
	{
		this.listOfTests = listOfTests;
	}

}
