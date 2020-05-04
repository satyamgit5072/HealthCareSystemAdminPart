package com.healthcare.dao;

import com.healthcare.dto.Test;
import com.healthcare.dto.User;

import javassist.NotFoundException;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.healthcare.dto.Appointment;
import com.healthcare.dto.DiagnosticCenter;

public interface HealthCareAdminDAO
{
    public String addCenter(DiagnosticCenter diagnosticCenter);

	public String addTest(DiagnosticCenter diagnosticCenter,Test test);
	
	public boolean removeCenter(DiagnosticCenter diagnosticCenter);
	
	public boolean removeTest(DiagnosticCenter diagnosticCenter,Test test);
	
	public List<DiagnosticCenter> listOfDiagnosticCenters();
	
	public String createUser(User user);
	
	public User findByUserId(int userId);
	
	public String makeAppointment(Appointment appointment);
	
	public Appointment findByAppointmentId(int appointmentId);

	public String approveAppointment(Appointment appointment);

	public List<DiagnosticCenter> getAllDiagnosticCenters();

	public List<Test> getListOfTests(String centerName);

	public List<Appointment> getCenterAppointmentList(int centerId);
	
}
