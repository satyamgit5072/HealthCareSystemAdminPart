package com.healthcare.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.healthcare.dto.Appointment;
import com.healthcare.dto.DiagnosticCenter;
import com.healthcare.dto.Test;
import com.healthcare.dto.User;

@Transactional
@Repository
public class HealthCareAdminDAOImplem implements HealthCareAdminDAO
{
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public String addCenter(DiagnosticCenter diagnosticCenter)
	{
		List<Test> tests = new ArrayList<Test>();
		tests.add(new Test("blood group"));
		tests.add(new Test("blood sugar"));
		tests.add(new Test("blood pressure"));
        diagnosticCenter.setListOfTests(tests);	
		entityManager.persist(diagnosticCenter);
		return "center added successfully";
	}

	@Override
	public String addTest(DiagnosticCenter diagnosticCenter, Test test) 
	{
		entityManager.persist(test);
		diagnosticCenter.getListOfTests().add(test);
		return "test added sucessfully";
	}

	@Override
	public boolean removeCenter(DiagnosticCenter diagnosticCenter)
	{
		entityManager.remove(diagnosticCenter);
		return true;
	}

	@Override
	public boolean removeTest(DiagnosticCenter diagnosticCenter,Test test)
	{
		int testId = test.getTestId();
		diagnosticCenter.getListOfTests().removeIf(testObj->testObj.getTestId()==testId);
		entityManager.remove(test);
		return true;
	}
	
	@Override
	public List<DiagnosticCenter> listOfDiagnosticCenters()
	{
		String queryStr = "select diagnosticCenter from DiagnosticCenter diagnosticCenter";
		TypedQuery<DiagnosticCenter> query = entityManager.createQuery(queryStr,DiagnosticCenter.class);
		return query.getResultList();
	}
	
	@Override
	public String createUser(User user)
	{
		entityManager.persist(user);
		return "user created";
	}

	@Override
	public User findByUserId(int userId) {
		String queryStr = "select user from User user where user.userId=:userId";
		TypedQuery<User> query = entityManager.createQuery(queryStr,User.class);
		User user = query.setParameter("userId", userId).getSingleResult();
		return user;
	}

	@Override
	public String makeAppointment(Appointment appointment)
	{
		entityManager.persist(appointment);
		return "appointment added";
	}

	@Override
	public Appointment findByAppointmentId(int appointmentId) 
	{
		String queryStr = "select appointment from Appointment appointment where appointment.appointmentId=:appointmentId";
		TypedQuery<Appointment> query = entityManager.createQuery(queryStr,Appointment.class);
		Appointment appointment = query.setParameter("appointmentId", appointmentId).getSingleResult();
		return appointment;
	}
		
	@Override
	public String approveAppointment(Appointment appointment)
	{
//		appointment.setApproved(true);
		entityManager.merge(appointment);
		System.out.println(appointment.getAppointmentId()+" "+appointment.getUser().getUserName()+" "+appointment.getCenter().getCenterName()+" "+appointment.getTest().getTestName()+appointment.isApproved());
		return "appointment approved";
	}
	
	@Override
	public List<DiagnosticCenter> getAllDiagnosticCenters()
	{
		String queryStr = "select diagnosticCenter from DiagnosticCenter diagnosticCenter";
		TypedQuery<DiagnosticCenter> query = entityManager.createQuery(queryStr,DiagnosticCenter.class);
		return query.getResultList();
	}
	
	@Override
	public List<Test> getListOfTests(String centerName)
	{
		String queryStr = "select diagnosticCenter from DiagnosticCenter diagnosticCenter where diagnosticCenter.centerName=:centerName";
		TypedQuery<DiagnosticCenter> query = entityManager.createQuery(queryStr,DiagnosticCenter.class);
		DiagnosticCenter diagnosticCenter = query.setParameter("centerName", centerName).getSingleResult();
		return diagnosticCenter.getListOfTests();
	}

	@Override
	public List<Appointment> getCenterAppointmentList(int centerId) {
		String queryStr = "select diagnosticCenter from DiagnosticCenter diagnosticCenter where diagnosticCenter.centerId=:centerId";
		TypedQuery<DiagnosticCenter> query = entityManager.createQuery(queryStr,DiagnosticCenter.class);
		DiagnosticCenter diagnosticCenter = query.setParameter("centerId", centerId).getSingleResult();
		return diagnosticCenter.getAppointmentList();
	}
	
}
