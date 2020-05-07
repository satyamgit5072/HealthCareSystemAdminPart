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
	
	/*The below method takes a center object and calls the addCenter method defined
	 * in HealthCareAdminDAO and that method take care of persisting the object into the database*/
	@Override
	public String addCenter(DiagnosticCenter diagnosticCenter)
	{
		return adminDAO.addCenter(diagnosticCenter);
	}
	
	/*The below method takes a center object, test object and calls the addTest method defined
	 * in HealthCareAdminDAO and that method take care of persisting the object into the database*/
	@Override
	public String addTest(DiagnosticCenter diagnosticCenter,Test test) 
	{
		return adminDAO.addTest(diagnosticCenter,test);
	}

	/*The below method takes a center object and calls the removeCenter method defined in HealthCareAdminDAO
	 * and that method take care of deleting the object from the database*/
	@Override
	public boolean removeCenter(DiagnosticCenter diagnosticCenter) 
	{
		return adminDAO.removeCenter(diagnosticCenter);
	}
	
	/*The below method takes a center object, test object and calls the removeTest method defined in 
	 *  HealthCareAdminDAO and that method take care of deleting the object from the database*/
	@Override
	public boolean removeTest(DiagnosticCenter diagnosticCenter,Test test) 
	{
		return adminDAO.removeTest(diagnosticCenter,test);
		
	}

	/*The below method takes a center name and checks if the center name exits and retrieves its 
	 * center object. If the center name given is not there it throws the ResourceNotFoundException
	 * with message Not found center */
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

	/*The below method takes a center object, test name and checks if the test name exits in that given
	 * center and retrieves its test object. If the test name given is not there in that given 
	 * center it throws the ResourceNotFoundException with message test does not exist in the given center. */
	@Override
	public Test findByTestName(DiagnosticCenter diagnosticCenter, String testName) throws ResourceNotFoundException 
	{
		boolean isTestPresent = diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().isPresent();
		if(!isTestPresent)
			throw new ResourceNotFoundException(testName+" test does not exist in the "+diagnosticCenter.getCenterName()+" center");
		Test test = diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().get();
		return test;
	}

	/*The below method takes a center name and checks if the center name exits in the list of centers
	 *  and returns true if the center name exists otherwise it returns false if does not exist*/
	@Override
	public boolean isCenterNameExist(String centerName)
	{
		List<DiagnosticCenter> listOfDiagnosticCenters = adminDAO.listOfDiagnosticCenters();
		if(!listOfDiagnosticCenters.stream().filter(center->center.getCenterName().equalsIgnoreCase(centerName)).findFirst().isPresent())
			return false;
		return true;
	}
	
	/*The below method takes a center object, test name and checks if the test name exits in the given
	 * center and returns true if the test name exists in the given center object otherwise it returns 
	 * false if does not exist*/
	@Override
	public boolean isTestNameExist(DiagnosticCenter diagnosticCenter, String testName) 
	{
		return diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().isPresent();
	}
	
	/*The below method takes a user object and calls the createUser method defined in HealthCareAdminDAO 
	 * and that method take care of persisting the object into the database*/
	@Override
	public String createUser(User user)
	{
		return adminDAO.createUser(user);
	}
	
	/*The below method takes a user id and calls the findByUserId method defined in HealthCareAdminDAO
	 * which retrieves the user object related to that userId.*/
	@Override
	public User findByUserId(int userId)
	{
		return adminDAO.findByUserId(userId);
	}
	
	/*The below method takes an appointment object and calls the makeAppointment method defined
	 * in HealthCareAdminDAO and that method take care of persisting the object into the database*/
	@Override
	public String makeAppointment(Appointment appointment)
	{
		return adminDAO.makeAppointment(appointment);
	}
	
	/*The below method takes a center object, appointmentId and checks if the given appointmentId 
	 * exits in the given center object and returns true if the given appointmentId exists otherwise 
	 * it returns false if does not exist*/
	@Override
	public boolean isAppointmentIdExist(DiagnosticCenter diagnosticCenter,int appointmentId)
	{
		return diagnosticCenter.getAppointmentList().stream().filter(a->String.valueOf(a.getAppointmentId()).equalsIgnoreCase(String.valueOf(appointmentId))).findFirst().isPresent();
	}
	
	/*The below method takes an appointmentId and calls the findByAppointmentId method defined in 
	 * HealthCareAdminDAO which retrieves the appointment object related to that appointmentId.*/
	@Override
	public Appointment findByAppointmentId(int appointmentId)
	{
		return adminDAO.findByAppointmentId(appointmentId);
	}

	/*The below method takes an appointment object and calls the approveAppointment method defined in 
	 * HealthCareAdminDAO and that method is going to update the appointment object with approved status.*/
	@Override
	public String approveAppointment(Appointment appointment) 
	{
		return adminDAO.approveAppointment(appointment);
	}

	/*The below method takes an userId and retrieves the appointment object associated with that userId*/
	@Override
	public Appointment viewAppointment(int userId) 
	{
		User user = findByUserId(userId);
		int appointmentId = user.getAppointment().getAppointmentId();
		Appointment appointment = findByAppointmentId(appointmentId);
		return appointment;
	}

	/*The below method calls the getAllDiagnosticCenters method defined in the HealthCareAdminDAO 
	 * and retrieves all the centers present.*/
	@Override
	public List<DiagnosticCenter> getAllDiagnosticCenters() 
	{
		return adminDAO.getAllDiagnosticCenters();
	}

	/*The below method takes the center name and calls the getListOfTests method defined in the HealthCareAdminDAO 
	 * and retrieves all the tests present in the given center.*/
	@Override
	public List<Test> getListOfTests(String centerName) 
	{
		return adminDAO.getListOfTests(centerName);
	}
	
	/*The below method takes the center Id and calls the getCenterAppointmentList method defined in the HealthCareAdminDAO 
	  and retrieves the appointments associated with that centerId.*/
	@Override
	public List<Appointment> getCenterAppointmentList(int centerId)
	{
		return adminDAO.getCenterAppointmentList(centerId);
	}
	
}
