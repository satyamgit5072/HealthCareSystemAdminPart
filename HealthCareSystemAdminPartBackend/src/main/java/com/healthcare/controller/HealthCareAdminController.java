package com.healthcare.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.Appointment;
import com.healthcare.dto.DiagnosticCenter;
import com.healthcare.dto.Test;
import com.healthcare.dto.User;
import com.healthcare.exception.BadRequestException;
import com.healthcare.exception.ResourceNotFoundException;
import com.healthcare.service.HealthCareAdminService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HealthCareAdminController 
{
	@Autowired
	HealthCareAdminService adminService;

	@PostMapping(value="HealthCareSystem/add-center", consumes = {"application/json"})
	public String addCenter(@Valid @RequestBody DiagnosticCenter diagnosticCenter) throws BadRequestException
	{
		if(adminService.isCenterNameExist(diagnosticCenter.getCenterName()))
		{
			throw new BadRequestException(diagnosticCenter.getCenterName()+" center name already exits");
		}
		return adminService.addCenter(diagnosticCenter);
	}
	
	@PostMapping(value="HealthCareSystem/center/add-test/{centerName}", consumes = {"application/json"})
	public String addTest(@PathVariable String centerName, @Valid @RequestBody Test test) throws BadRequestException, ResourceNotFoundException
	{
		DiagnosticCenter diagnosticCenter = adminService.findByCenterName(centerName);
		if(adminService.isTestNameExist(diagnosticCenter, test.getTestName()))
			throw new BadRequestException(test.getTestName()+" test name already exits in the "+centerName+" center");
		return adminService.addTest(diagnosticCenter,test);
	} 
	
	@DeleteMapping(value="HealthCareSystem/remove-center/{centerName}")
	public void deleteCenter(@PathVariable String centerName) throws ResourceNotFoundException
	{
		DiagnosticCenter diagnosticCenter = adminService.findByCenterName(centerName);
	    adminService.removeCenter(diagnosticCenter);
	}

	@DeleteMapping(value="HealthCareSystem/remove-test/{centerName}/{testName}")
	public void deleteTest(@PathVariable String centerName, @PathVariable String testName) throws ResourceNotFoundException
	{
		DiagnosticCenter diagnosticCenter = adminService.findByCenterName(centerName);
		Test test = adminService.findByTestName(diagnosticCenter,testName);
		adminService.removeTest(diagnosticCenter,test);
	}
	
	@PostMapping(value="HealthCareSystem/make-appointment/{centerName}/{testName}/{userId}", consumes = {"application/json"})
	public String makeAppointment(@PathVariable String centerName, @PathVariable String testName, @PathVariable int userId, @RequestBody Appointment appointment) throws ResourceNotFoundException
	{
		DiagnosticCenter diagnosticCenter = adminService.findByCenterName(centerName);
		Test test = adminService.findByTestName(diagnosticCenter, testName);
		User user = adminService.findByUserId(userId);
		appointment.setCenter(diagnosticCenter);
		appointment.setTest(test);
		appointment.setUser(user);
		return adminService.makeAppointment(appointment);
	} 
	
	@PutMapping(value="HealthCareSystem/approve-appointment/{centerName}", consumes = {"application/json"})
	public String approveAppointment(@PathVariable String centerName,@RequestBody Appointment appointment) throws ResourceNotFoundException
	{
		DiagnosticCenter diagnosticCenter = adminService.findByCenterName(centerName);	
		if(!adminService.isAppointmentIdExist(diagnosticCenter,appointment.getAppointmentId()))
			throw new ResourceNotFoundException(appointment.getAppointmentId()+" appointmentId does not exist in the "+centerName+" center");
		Appointment appointmentb = adminService.findByAppointmentId(appointment.getAppointmentId());
		appointmentb.setApproved(appointment.isApproved());
		return adminService.approveAppointment(appointmentb);
	}
	
	@PostMapping(value="HealthCareSystem/create-user", consumes = {"application/json"})
	public String createUser(@Valid @RequestBody User user)
	{
		return adminService.createUser(user);
	}
	
	@GetMapping(value="HealthCareSystem/View Appointment/{userId}")
	public Appointment viewAppointment(@PathVariable int userId)
	{
		return adminService.viewAppointment(userId);
	}

	@GetMapping(value="HealthCareSystem/DiagnosticCenters")
	public List<DiagnosticCenter> getAllDiagnosticCenters()
	{
		return adminService.getAllDiagnosticCenters();
	}
	
	@GetMapping(value="HealthCareSystem/center/Tests/{centerName}")
	public List<Test> getListOfTests(@PathVariable String centerName)
	{
		return adminService.getListOfTests(centerName);
	}
	
	@GetMapping(value="HealthCareSystem/center/AppointmentList/{centerId}")
	public List<Appointment> getCenterAppointmentList(@PathVariable int centerId)
	{
		return adminService.getCenterAppointmentList(centerId);
	}
	
	@GetMapping(value="HealthCareSystem/checkCenterName/{centerName}")
	public boolean isCenterNameExist(@PathVariable String centerName)
	{
		return adminService.isCenterNameExist(centerName);
	}
		
}