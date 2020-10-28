package com.lti.dao;

import java.util.List;

import com.lti.model.Institute;
import com.lti.model.Nodal;
import com.lti.model.Scheme;
import com.lti.model.ScholarshipForm;
import com.lti.model.Student;

public interface NspInterface {

	public void registerAnInstitute(Institute institute);

	public void addAScheme(Scheme scheme);

	public void registerAStudent(Student student);

	public void addANodal(Nodal nodal);

	public Institute findAnInstitute(long instituteId);

	public Student findAStudent(long studentId);

	public Nodal findANodal(int Nodal_UID);

	public Scheme findAScheme(long schemeUID);

	public ScholarshipForm findAScholarshipForm(long form_id);

	public void applyForAScheme(ScholarshipForm form);

	public Institute findAnInstituteByInstituteCode(String instituteCode);

	public List<Institute> viewAllInstitutes();

	public List<Student> viewAllStudents();

	public void instituteApprovesAStudent(long studentId);

	public void instituteApprovesAForm(long id);

	public List<ScholarshipForm> viewUnapprovedFormsOfParticularInstitute(long instituteId);

	public List<Student> viewUnverifiedStudentsOfParticularInstitute(long instituteId);

	public void instituteRejectsAStudent(long studentId);

	public boolean instituteLogin(long userId, String password);

	public List<Institute> viewAllUnapprovedInstitutes();

	public List<ScholarshipForm> viewAllInstituteApprovedForms();

	public void nodalApproveAForm(long form_id);

	public void nodalRejectAForm(long form_id);

	public List<ScholarshipForm> viewAllNodalApprovedForms();

	public List<Institute> viewAllNodalApprovedInstitutes();

	public boolean studentLogin(long aadhar, String password);

	public void instituteRejectsAForm(long id);

	public void nodalApprovesAnInstitute(String code);

	public void nodalRejectsAnInstitute(String code);

	public void ministryApprovesAForm(long id);

	public void ministryRejectsAForm(long id);

	public void ministryApprovesAnInstitute(String code);

	public void ministryRejectsAnInstitute(String code);

	public boolean nodalLogin(int userId, String password);

}