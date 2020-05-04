package com.healthcare.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="test2")
public class Test 
{
	
	@NotNull(message = "test Id must not be empty")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int testId;
	
	@NotEmpty(message = "test name must not be empty")
	@Column(nullable=false)
	private String testName;
	
	@OneToMany(mappedBy = "test",cascade = CascadeType.ALL)
	private List<Appointment>appointmentList;
	
	public Test()
	{
		
	}
	
	public Test(String testName)
	{
		super();
		this.testName = testName;
	}

	public int getTestId() 
	{
		return testId;
	}

	public void setTestId(int testId)
	{
		this.testId = testId;
	}

	public String getTestName() 
	{
		return testName;
	}

	public void setTestName(String testName)
	{
		this.testName = testName;
	}


}
