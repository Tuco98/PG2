package com.lti.test;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.junit.Test;

import com.lti.dao.NspDao;
import com.lti.model.Institute;
import com.lti.model.Nodal;
import com.lti.model.Scheme;
import com.lti.model.ScholarshipForm;
import com.lti.model.Student;

public class NspTest {

	NspDao nspDao = new NspDao();

	@Test
	public void addAScheme() {
		Scheme scheme = new Scheme();
		scheme.setSchemeName("Scholarship 2");
		scheme.setSchemeMinistry("MHRD");
		scheme.setSchemeCourseLevel("Graduation");
		scheme.setScheme10EC(60);
		scheme.setScheme12EC(65);
		scheme.setSchemeCaste("SC");
		scheme.setSchemeAge(18);
		scheme.setSchemeGender("Female");
		scheme.setSchemeFamilyIncome(500000);
		scheme.setSchemeForDisabled(false);
		scheme.setSchemeAmount(50000);
		scheme.setSchemeStatus(true);
		nspDao.addAScheme(scheme);

	}

	@Test
	public void addAnInstitute() {
		Institute institute = new Institute();
		institute.setInstituteCode("100");
		institute.setInstituteDiseCode("1");
		institute.setInstituteName("ABC Institute of Technology");
		institute.setInstituteCategory("A");
		institute.setInstituteState("Maharashtra");
		institute.setInstituteDistrict("Some District");
		institute.setInstituteLocation("location A");
		institute.setInstituteType("Autonomous");
		institute.setInstituteUniversityState("Mumbai");
		institute.setInstituteUniversityName("Mumbai university");
		institute.setInstituteAdmissionYear("1970");
		institute.setInstitutePassword("abc@123");
		institute.setInstituteAddress("Mumbai, Maharashtra");
		institute.setInstitutePrincipalNmae("Dr. John Mathews");
		institute.setInstitutePhoneNumber("1234567895");
		institute.setInstituteEmail("abctech@gmail.com");
		//institute.setInstituteStatus(false);

		nspDao.registerAnInstitute(institute);

	}

	@Test
	public void addAStudent() {
		Student student = new Student();
		student.setStudentAadharNumber(123456123);
		student.setStudentName("James");
		student.setStudentGender("Male");
		student.setStudentDateOfBirth(LocalDate.of(1998, 10, 15));
		student.setStudentMobileNumber("9456123780");
		student.setStudentEmail("james@gmail.com");
		student.setStudentStateOfDomicile("Goa");
		student.setStudentDistrict("Panaji");
		student.setStudentBankName("HDFC");
		student.setStudenBankAccountNumber("11111");
		student.setStudentIfcsCode("16005");
		student.setStudentPassword("james123");
		//student.setStudentStatus("true");
		//student.setInstitute(nspDao.findAnInstitute(1001));
		
		Institute institute = nspDao.findAnInstituteByInstituteCode("100"); 
		
		if(institute != null) {
			student.setInstitute(institute);
			nspDao.registerAStudent(student);
		}
		else {
			System.out.println("Student registration failed");
		}
		
	}
	
	@Test
	public void addANodal() {
		Nodal nodal = new Nodal();
		nodal.setNodalState("Maharashtra");
		nodal.setNodalName("Nodal 1");
		nodal.setNodalEmail("nodal@lti.com");
		nodal.setNodalPhone(326524);
		nodal.setNodalStatus(true);
		nodal.setNodalPassword("nd@123");
		
		nspDao.addANodal(nodal);
	}
	
	
	@Test
	public void findAnInstituteByInstituteCode() {
		Institute institute = nspDao.findAnInstituteByInstituteCode("100");
		if(institute != null) {
			System.out.println(institute.getInstituteId()+" "+institute.getInstituteName());
		}
		else {
			System.out.println("Student registration failed");
		}
	}
	
	@Test
	public void applyForAScheme() {
		ScholarshipForm form = new ScholarshipForm();
		form.setAadharNumber(123456123);
		form.setReligion("Hindu");
		form.setFatherName("Andrew");
		form.setMotherName("Sarah");
		form.setFamilyAnnualIncome(500000);
		
		Student stu = nspDao.findAStudent(123456123);
		form.setStudent(stu);
		
		Nodal nodal = nspDao.findANodal(8);
		
		form.setNodal(nodal);
		
		form.setInstitute(nspDao.findAnInstitute(1021));
		
		Scheme scheme = nspDao.findAScheme(1021);
		
		form.setScheme(scheme);
		//form.setNodalVerificationStatus("Not Approved");
		//form.setMinistryVerificationStatus("Not Approved");
		
		nspDao.applyForAScheme(form);
	}
	
	@Test
	public void viewAllInstitutes() {
		List<Institute> institutes = nspDao.viewAllInstitutes();
		
		for(Institute i: institutes) {
			System.out.println(i.getInstituteId()+" "+i.getInstituteName()+" "+i.getInstituteNodalOfficerApproval()+" "+i.getInstituteMinistryApproval()+" "+i.getInstituteState());
		}
	}
	
	@Test
	public void viewAllStudents() {
		List<Student> students = nspDao.viewAllStudents();
		System.out.println(students.size());
		for(Student s: students) {
			System.out.println(s.getStudentAadharNumber()+" "+s.getStudentName()+" "+s.getStudentStatus()+" "+s.getInstitute().getInstituteId());
		}
	}
	
	@Test
	public void instituteApprovesAStudent() {
		//Student student = nspDao.findAStudent(123456123);
		nspDao.instituteApprovesAStudent(123456123);
	}
	
	@Test
	public void viewFormsSubmittedByStudentsOfParticularInstitute() {
		nspDao.viewUnapprovedFormsOfParticularInstitute(1021);
	}
	
	@Test
	public void viewUnverifiedStudentsOfParticularInstitute() {
		nspDao.viewUnverifiedStudentsOfParticularInstitute(1021);
	}
	
	@Test
	public void instituteRejectsAStudent(){
		//Student student = nspDao.findAStudent(123456123);
		nspDao.instituteRejectsAStudent(123456123);
	}
	
	@Test
	public void instituteLogin(){
		boolean check=nspDao.instituteLogin(1021,"abc@123");
		if(check==true){
			System.out.println("Login Successful");
		}
		else{
			System.out.println("Login failed");
		}
	}
	@Test
	public void studentLogin(){
		boolean check=nspDao.studentLogin(123456123, "james123");
		if(check==true){
			System.out.println("Login successful");
		}
		else{
			System.out.println("Login failed");
		}
	}
	@Test
	public void nodalApprovesAnInstitute(){
		nspDao.nodalApprovesAnInstitute("100");
	}
	@Test
	public void nodalRejectsAnInstitute(){
		nspDao.nodalRejectsAnInstitute("100");
	}
	@Test
	public void ministryApprovesAForm(){
		nspDao.ministryApprovesAForm(1001);
	}
	@Test
	public void ministryRejectsAForm(){
		nspDao.ministryRejectsAForm(1001);
	}
	@Test
	public void ministryApprovesAnInstitute(){
		nspDao.ministryApprovesAnInstitute("100");
	}
	@Test
	public void ministryRejectssAnInstitute(){
		nspDao.ministryRejectsAnInstitute("100");
	}
	@Test
	public void nodalLogin(){
		boolean check=nspDao.nodalLogin(12, "nd@123");
		if(check==true)
			System.out.println("Nodal login succcessful");
		System.out.println("Nodal login failed");
	}
}
