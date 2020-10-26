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
	
	public ScholarshipForm findAScholarshipForm(long form_id) {
		ScholarshipForm form = em.find(ScholarshipForm.class, form_id);
		return form;
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

	public void instituteApprovesAStudent(long studentId) {
		Student student  = findAStudent(studentId);
		student.setStudentStatus("Approved");
		tx.begin();
		em.merge(student);
		tx.commit();
		System.out.println("Student Approved");
	}
	
	public void instituteApprovesAForm(long id) {
		ScholarshipForm form=findAScholarshipForm(id);
		form.setInstituteVerificationStatus("Approved");
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Student Approved");
	}
	
	public List<ScholarshipForm> viewUnapprovedFormsOfParticularInstitute(long instituteId) {
		String jpql = "select f from ScholarshipForm f where institute_id=:id and f.instituteVerificationStatus=:na";
		Query query = em.createQuery(jpql, ScholarshipForm.class);
		query.setParameter("id", instituteId);
		query.setParameter("na", "Not Approved");
		
		List<ScholarshipForm> forms = query.getResultList();
		
//		for(ScholarshipForm f: forms) {
//			System.out.println(f.getFormId()+" "+f.getStudent().getStudentAadharNumber()+" "+f.getInstitute().getInstituteCode()+" "+f.getInstituteVerificationStatus());
//		}
		
		return forms;
		
	}
	
	public List<Student> viewUnverifiedStudentsOfParticularInstitute(long instituteId) {
		String jpql = "select s from Student s where student_status=:na and institute_code=:id";
		Query query = em.createQuery(jpql, Student.class);
		query.setParameter("id", instituteId);
		query.setParameter("na", "Not Approved");
		
		List<Student> students = query.getResultList();
		
//		for(Student stu: students) {
//			System.out.println(stu.getStudentAadharNumber()+" "+stu.getStudentName()+" "+stu.getStudentStatus());
//		}
		
		return students;
	}
	
	public void instituteRejectsAStudent(long studentId){
		Student student = findAStudent(studentId);
		student.setStudentStatus("Rejected");
		tx.begin();
		em.merge(student);
		tx.commit();
		System.out.println("Student Rejected");

	}
	public boolean instituteLogin(long userId, String password){
		String jpql="select i from Institute i where i.instituteId=:id and i.institutePassword=:psw";
		Query query=em.createQuery(jpql, Institute.class);
		query.setParameter("id", userId);
		query.setParameter("psw", password);
		Institute ins=(Institute)query.getResultList().stream().findFirst().orElse(null);
		if(ins!=null)
			return true;
		return false;
	}
	
	public List<Institute> viewAllUnapprovedInstitutes() {
		String jpql="select i from Institute i where i.instituteNodalOfficerApproval=:fn";
		Query query=em.createQuery(jpql, Institute.class);
		query.setParameter("fn", "Not Approved");
		
		List<Institute> institutes = query.getResultList();
		
		return institutes;
		
	}
	
	public List<ScholarshipForm> viewAllInstituteApprovedForms(){
		String jpql="select f from ScholarshipForm f where f.instituteVerificationStatus=:ivs and f.nodal_verification_status:nvs";
		Query query = em.createQuery(jpql, ScholarshipForm.class);
		query.setParameter("ivs", "Approved");
		query.setParameter("nvs", "Not Approved");
		
		List<ScholarshipForm> forms = query.getResultList();
		
		return forms;
		
	}
	
	public void nodalApproveAForm(long form_id) {
		ScholarshipForm form = findAScholarshipForm(form_id);
		form.setNodalVerificationStatus("Approved");
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Scholarship form is Approved by Nodal Officer");
	}
	
	public void nodalRejectAForm(long form_id) {
		ScholarshipForm form = findAScholarshipForm(form_id);
		form.setNodalVerificationStatus("Rejected");
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Scholarship form is Rejected by Nodal Officer");
	}
	
	public List<ScholarshipForm> viewAllNodalApprovedForms() {
		String jpql="select f from ScholarshipForm f where f.nodal_verification_status:nvs and f.ministryVerificationStatus=:mvs";
		Query query = em.createQuery(jpql, ScholarshipForm.class);
		query.setParameter("nvs", "Approved");
		query.setParameter("mvs", "Not Approved");
		
		List<ScholarshipForm> forms = query.getResultList();
		
		return forms;
	}
	
	public List<Institute> viewAllNodalApprovedInstitutes(){
		String jpql="select i from Institute i where i.instituteNodalOfficerApproval=:fn and i.instituteMinistryApproval=:ims";
		Query query=em.createQuery(jpql, Institute.class);
		query.setParameter("fn", "Approved");
		query.setParameter("ims", "Not Approved");
		
		List<Institute> institutes = query.getResultList();
		
		return institutes;
	}
	public boolean studentLogin(long aadhar, String password){
		String jpql="select s from Student s where s.studentAadharNumber=:aad and s.studentPassword=:psw";
		Query query=em.createQuery(jpql, Student.class);
		query.setParameter("aad", aadhar);
		query.setParameter("psw", password);
		Student student=(Student) query.getSingleResult();
		if(student!=null)
			return true;
		return false;
	}
	
	public void instituteRejectsAForm(long id) {
		ScholarshipForm form=findAScholarshipForm(id);
		form.setInstituteVerificationStatus("Rejected");
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Student Application Rejected");
	}
	public void nodalApprovesAnInstitute(String code){
		Institute institute=findAnInstituteByInstituteCode(code);
		institute.setInstituteNodalOfficerApproval("Approved");
		tx.begin();
		em.merge(institute);
		tx.commit();
		System.out.println("Nodal approved the institute");
	}
	public void nodalRejectsAnInstitute(String code){
		Institute institute=findAnInstituteByInstituteCode(code);
		institute.setInstituteNodalOfficerApproval("Rejected");
		tx.begin();
		em.merge(institute);
		tx.commit();
		System.out.println("Nodal rejected the institute");
	}
	public void ministryApprovesAForm(long id){
		ScholarshipForm form=findAScholarshipForm(id);
		form.setMinistryVerificationStatus("Approved");
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Ministry approved a form");
	}
	public void ministryRejectsAForm(long id){
		ScholarshipForm form=findAScholarshipForm(id);
		form.setMinistryVerificationStatus("Rejected");
		tx.begin();
		em.merge(form);
		tx.commit();
		System.out.println("Ministry rejected a form");
	}
	public void ministryApprovesAnInstitute(String code){
		Institute institute=findAnInstituteByInstituteCode(code);
		institute.setInstituteMinistryApproval("Approved");
		tx.begin();
		em.merge(institute);
		tx.commit();
		System.out.println("Ministry approved the institute");
	}
	public void ministryRejectsAnInstitute(String code){
		Institute institute=findAnInstituteByInstituteCode(code);
		institute.setInstituteMinistryApproval("Rejected");
		tx.begin();
		em.merge(institute);
		tx.commit();
		System.out.println("Ministry rejected the institute");
	}

}
