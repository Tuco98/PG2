package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.model.Institute;
import com.lti.model.Nodal;
import com.lti.model.Scheme;
import com.lti.model.ScholarshipForm;
import com.lti.model.Student;

public class NspDao {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction tx;

	public NspDao() {
		emf = Persistence.createEntityManagerFactory("pu");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	public void registerAnInstitute(Institute institute) {

		institute.setInstituteStatus(false);
		institute.setInstituteNodalOfficerApproval("Not Approved");
		institute.setInstituteMinistryApproval("Not Approved");

		tx.begin();
		em.merge(institute);
		tx.commit();
		System.out.println("Institute added");
	}

	public void addAScheme(Scheme scheme) {
		tx.begin();
		em.merge(scheme);
		tx.commit();
		System.out.println("Scheme Added");
	}

	public void registerAStudent(Student student) {

		student.setStudentStatus("Not Approved");

		tx.begin();
		em.merge(student);
		tx.commit();
		System.out.println("Student Added");
	}
	
	public void addANodal(Nodal nodal) {

		tx.begin();
		em.merge(nodal);
		tx.commit();
		System.out.println("Nodal Added");
	}

	public Institute findAnInstitute(long instituteId) {
		Institute institute = em.find(Institute.class, instituteId);
		return institute;
	}

	public Student findAStudent(long studentId) {
		Student student = em.find(Student.class, studentId);
		return student;
	}
	
	public Nodal findANodal(int Nodal_UID) {
		Nodal nodal = em.find(Nodal.class, Nodal_UID);
		return nodal;
	}
	
	public Scheme findAScheme(long schemeUID) {
		Scheme scheme = em.find(Scheme.class, schemeUID);
		return scheme;
	}

	public void applyForAScheme(ScholarshipForm form) {
		
		form.setInstituteVerificationStatus("Not Approved");
		form.setNodalVerificationStatus("Not Approved");
		form.setMinistryVerificationStatus("Not Approved");
		form.setStatus("Not Approved");
		
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Form added");
	}

	public Institute findAnInstituteByInstituteCode(String instituteCode) {
		Institute institute = new Institute();

		String jpql = "select i from Institute i where i.instituteCode=:ic";
		Query query = em.createQuery(jpql, Institute.class);
		query.setParameter("ic", instituteCode);

		institute = (Institute) query.getSingleResult();

		return institute;
	}

	public List<Institute> viewAllInstitutes() {

		String jpql = "select i from Institute i";
		Query query = em.createQuery(jpql, Institute.class);

		List<Institute> institutes = query.getResultList();

		return institutes;
	}

	public List<Student> viewAllStudents() {

		String jpql = "select s from Student s";
		Query query = em.createQuery(jpql, Student.class);

		List<Student> students = query.getResultList();

		return students;
	}

	public void instituteApprovesAStudent(Student student) {
		student.setStudentStatus("Approved");
		tx.begin();
		em.merge(student);
		tx.commit();
		System.out.println("Student Approved");
	}
	
	public void instituteApprovesAForm(Student student) {
		ScholarshipForm form = student.getForm();
		form.setInstituteVerificationStatus("Approved");
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Student Approved");
	}
	
	public void viewUnapprovedFormsOfParticularInstitute(long instituteId) {
		String jpql = "select f from ScholarshipForm f where institute_id=:id and f.instituteVerificationStatus=:na";
		Query query = em.createQuery(jpql, ScholarshipForm.class);
		query.setParameter("id", instituteId);
		query.setParameter("na", "Not Approved");
		
		List<ScholarshipForm> forms = query.getResultList();
		
		for(ScholarshipForm f: forms) {
			System.out.println(f.getFormId()+" "+f.getStudent().getStudentAadharNumber()+" "+f.getInstitute().getInstituteCode()+" "+f.getInstituteVerificationStatus());
		}
		
	}
	
	public void viewUnverifiedStudentsOfParticularInstitute(long instituteId) {
		String jpql = "select s from Student s where student_status=:na and institute_code=:id";
		Query query = em.createQuery(jpql, Student.class);
		query.setParameter("id", instituteId);
		query.setParameter("na", "Not Approved");
		
		List<Student> students = query.getResultList();
		
		for(Student stu: students) {
			System.out.println(stu.getStudentAadharNumber()+" "+stu.getStudentName()+" "+stu.getStudentStatus());
		}		
	}

}
