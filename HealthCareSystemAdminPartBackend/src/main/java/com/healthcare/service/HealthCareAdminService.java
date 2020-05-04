package com.healthcare.service;

import java.util.List;
import com.healthcare.dto.Appointment;
import com.healthcare.dto.DiagnosticCenter;
import com.healthcare.dto.Test;
import com.healthcare.dto.User;
import com.healthcare.exception.ResourceNotFoundException;

public interface HealthCareAdminService 
{
	    public String addCenter(DiagnosticCenter diagnosticCenter);
	    
	    public String addTest(DiagnosticCenter diagnosticCenter,Test test);
		
		public boolean removeCenter(DiagnosticCenter diagnosticCenter);
		
		public boolean removeTest(DiagnosticCenter diagnosticCenter,Test test);
		
		public DiagnosticCenter findByCenterName(String centerName) throws ResourceNotFoundException;
		
		public Test findByTestName(DiagnosticCenter diagnosticCenter, String testName) throws ResourceNotFoundException;
		
		public boolean isCenterNameExist(String centerName);
		
		public boolean isTestNameExist(DiagnosticCenter diagnosticCenter, String testName);
		
		public String createUser(User user);

		public User findByUserId(int userId);

		public String makeAppointment(Appointment appointment);
		
		public boolean isAppointmentIdExist(DiagnosticCenter diagnosticCenter, int appointmentId);
		
		public Appointment findByAppointmentId(int appointmentId);
		
		public String approveAppointment(Appointment appointment);

		public Appointment viewAppointment(int userId);

		public List<DiagnosticCenter> getAllDiagnosticCenters();  
		
		public List<Test> getListOfTests(String centerName);

		public List<Appointment> getCenterAppointmentList(int centerId);
}
