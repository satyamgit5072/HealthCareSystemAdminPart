package com.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dao.HealthCareAdminDAO;
import com.healthcare.dto.Appointment;
import com.healthcare.dto.DiagnosticCenter;
import com.healthcare.dto.Test;
import com.healthcare.dto.User;
import com.healthcare.exception.ResourceNotFoundException;

@Service
public class HealthCareAdminServiceImplem implements HealthCareAdminService 
{
	@Autowired
	HealthCareAdminDAO adminDAO;
	
	@Override
	public String addCenter(DiagnosticCenter diagnosticCenter) 
	{
		return adminDAO.addCenter(diagnosticCenter);
	}

	@Override
	public String addTest(DiagnosticCenter diagnosticCenter,Test test) 
	{
		return adminDAO.addTest(diagnosticCenter,test);
	}

	@Override
	public boolean removeCenter(DiagnosticCenter diagnosticCenter) 
	{
		return adminDAO.removeCenter(diagnosticCenter);
	}

	@Override
	public boolean removeTest(DiagnosticCenter diagnosticCenter,Test test) 
	{
		return adminDAO.removeTest(diagnosticCenter,test);
		
	}

	@Override
	public DiagnosticCenter findByCenterName(String centerName) throws ResourceNotFoundException 
	{
		List<DiagnosticCenter> listOfDiagnosticCenters = adminDAO.listOfDiagnosticCenters();
		boolean isDiagnosticCentPresent = listOfDiagnosticCenters.stream().filter(center->center.getCenterName().equalsIgnoreCase(centerName)).findFirst().isPresent();
		if(!isDiagnosticCentPresent)
			throw new ResourceNotFoundException("Not found center "+centerName);		
	    DiagnosticCenter diagnosticCenter = listOfDiagnosticCenters.stream().filter(center->center.getCenterName().equalsIgnoreCase(centerName)).findFirst().get();
	    return diagnosticCenter;
	}

	@Override
	public Test findByTestName(DiagnosticCenter diagnosticCenter, String testName) throws ResourceNotFoundException 
	{
		boolean isTestPresent = diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().isPresent();
		if(!isTestPresent)
			throw new ResourceNotFoundException(testName+" test does not exist in the "+diagnosticCenter.getCenterName()+" center");
		Test test = diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().get();
		return test;
	}

	@Override
	public boolean isCenterNameExist(String centerName)
	{
		List<DiagnosticCenter> listOfDiagnosticCenters = adminDAO.listOfDiagnosticCenters();
		if(!listOfDiagnosticCenters.stream().filter(center->center.getCenterName().equalsIgnoreCase(centerName)).findFirst().isPresent())
			return false;
		return true;
	}

	@Override
	public boolean isTestNameExist(DiagnosticCenter diagnosticCenter, String testName) 
	{
		return diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().isPresent();
	}
	
	@Override
	public String createUser(User user)
	{
		return adminDAO.createUser(user);
	}
	
	@Override
	public User findByUserId(int userId)
	{
		return adminDAO.findByUserId(userId);
	}
	
	@Override
	public String makeAppointment(Appointment appointment)
	{
		return adminDAO.makeAppointment(appointment);
	}

	@Override
	public boolean isAppointmentIdExist(DiagnosticCenter diagnosticCenter,int appointmentId)
	{
		return diagnosticCenter.getAppointmentList().stream().filter(a->String.valueOf(a.getAppointmentId()).equalsIgnoreCase(String.valueOf(appointmentId))).findFirst().isPresent();
	}
	
	@Override
	public Appointment findByAppointmentId(int appointmentId)
	{
		return adminDAO.findByAppointmentId(appointmentId);
	}

	@Override
	public String approveAppointment(Appointment appointment) 
	{
		return adminDAO.approveAppointment(appointment);
	}

	@Override
	public Appointment viewAppointment(int userId) 
	{
		User user = findByUserId(userId);
		int appointmentId = user.getAppointment().getAppointmentId();
		Appointment appointment = findByAppointmentId(appointmentId);
		String appointmentInfo = "center: "+appointment.getCenter().getCenterName()+" test:"+appointment.getTest().getTestName()+" isApproves"+appointment.isApproved();
		System.out.println(appointmentInfo);
		return appointment;
	}

	@Override
	public List<DiagnosticCenter> getAllDiagnosticCenters() 
	{
		return adminDAO.getAllDiagnosticCenters();
	}

	@Override
	public List<Test> getListOfTests(String centerName) 
	{
		return adminDAO.getListOfTests(centerName);
	}
	
	@Override
	public List<Appointment> getCenterAppointmentList(int centerId)
	{
		return adminDAO.getCenterAppointmentList(centerId);
	}
	
}
