package com.lti.model;

//added the dependency form student to form

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "tbl_scholarship_form")
public class ScholarshipForm {
	
	@Id
	@SequenceGenerator(name = "formSeq", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formSeq")
	@Column(name = "form_id")
	long formId;
	
	@Column(name = "aadhar_number")
	long aadharNumber;
	
	@Column(name = "religion")
	String religion;
	
	@Column(name = "category")
	String category;
	
	@Column(name = "father_name")
	String fatherName;
	
	@Column(name = "mother_name")
	String motherName;
	
	@Column(name = "family_annual_income")
	double familyAnnualIncome;
	
	@Column(name = "nodal_verification_status")
	String nodalVerificationStatus; //Not Approved/Approved/Rejected
	
	@Column(name = "ministry_verification_status")
	String ministryVerificationStatus;//Not Approved/Approved/Rejected
	
	
	@OneToOne
	@JoinColumn( name = "student_aadhar_number")
	Student student;
	/*
	String instituteName;
	String presentCourse;
	LocalDate presentCourseYear;
	String modeOfStudy;
	LocalDate classStartDate;
	String universityName;
	String previousCourse;
	LocalDate previousPassingYear;
	double previousClassPercentage;
	
	String class10RollNumber;
	String class10BoardName;
	LocalDate class10PassingYear;
	double class10Percentage;
	
	String class12RollNumber;
	String class12BoardName;
	LocalDate class12PassingYear;
	double class12Percentage;
	
	double admissionFee;
	double tutionFee;
	double otherFee;
	
	boolean isDisabled;
	String typeOfDisabilty;
	double percentageOfDisability;
	String maritalStatus;
	String parentProfession;
	*/

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public double getFamilyAnnualIncome() {
		return familyAnnualIncome;
	}

	public void setFamilyAnnualIncome(double familyAnnualIncome) {
		this.familyAnnualIncome = familyAnnualIncome;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getNodalVerificationStatus() {
		return nodalVerificationStatus;
	}

	public void setNodalVerificationStatus(String nodalVerificationStatus) {
		this.nodalVerificationStatus = nodalVerificationStatus;
	}

	public String getMinistryVerificationStatus() {
		return ministryVerificationStatus;
	}

	public void setMinistryVerificationStatus(String ministryVerificationStatus) {
		this.ministryVerificationStatus = ministryVerificationStatus;
	}
	
	
	
	
	

}
