package com.lti.dao;

import java.util.List;

import com.lti.model.Institute;
import com.lti.model.Nodal;
import com.lti.model.Scheme;
import com.lti.model.ScholarshipForm;
import com.lti.model.Student;

public interface NspInterfaces {

	void registerAnInstitute(Institute institute);

	void addAScheme(Scheme scheme);

	void registerAStudent(Student student);

	void addANodal(Nodal nodal);

	Institute findAnInstitute(long instituteId);

	Student findAStudent(long studentId);

	Nodal findANodal(int Nodal_UID);

	Scheme findAScheme(long schemeUID);

	ScholarshipForm findAScholarshipForm(long form_id);

	void applyForAScheme(ScholarshipForm form);

	Institute findAnInstituteByInstituteCode(String instituteCode);

	List<Institute> viewAllInstitutes();

	List<Student> viewAllStudents();

	void instituteApprovesAStudent(long studentId);

	void instituteApprovesAForm(long id);

	List<ScholarshipForm> viewUnapprovedFormsOfParticularInstitute(long instituteId);

	List<Student> viewUnverifiedStudentsOfParticularInstitute(long instituteId);

	void instituteRejectsAStudent(long studentId);

	boolean instituteLogin(long userId, String password);

	List<Institute> viewAllUnapprovedInstitutes();

	List<ScholarshipForm> viewAllInstituteApprovedForms();

	void nodalApproveAForm(long form_id);

	void nodalRejectAForm(long form_id);

	List<ScholarshipForm> viewAllNodalApprovedForms();

	List<Institute> viewAllNodalApprovedInstitutes();

	boolean studentLogin(long aadhar, String password);

	void instituteRejectsAForm(long id);

	void nodalApprovesAnInstitute(String code);

	void nodalRejectsAnInstitute(String code);

	void ministryApprovesAForm(long id);

	void ministryRejectsAForm(long id);

	void ministryApprovesAnInstitute(String code);

	void ministryRejectsAnInstitute(String code);

	boolean nodalLogin(int userId, String password);

}