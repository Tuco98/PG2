package com.lti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_Nodal")
public class Nodal {
	
	@Id
	@GeneratedValue
	@Column(name="Nodal_UID")
	int nodalUid;
	
	@Column(name="Nodal_State")
	String nodalState;
	
	@Column(name="Nodal_Name")
	String nodalName;
	
	@Column(name="Nodal_Email")
	String nodalEmail;
	
	@Column(name="Nodal_Phone")
	int nodalPhone;
	
	@Column(name="Nodal_Status")
	Boolean nodalStatus;
	
	@OneToOne( mappedBy = "nodal")
	ScholarshipForm form;

	public int getNodalUid() {
		return nodalUid;
	}

	public void setNodalUid(int nodalUid) {
		this.nodalUid = nodalUid;
	}

	public String getNodalState() {
		return nodalState;
	}

	public void setNodalState(String nodalState) {
		this.nodalState = nodalState;
	}

	public String getNodalName() {
		return nodalName;
	}

	public void setNodalName(String nodalName) {
		this.nodalName = nodalName;
	}

	public String getNodalEmail() {
		return nodalEmail;
	}

	public void setNodalEmail(String nodalEmail) {
		this.nodalEmail = nodalEmail;
	}

	public int getNodalPhone() {
		return nodalPhone;
	}

	public void setNodalPhone(int nodalPhone) {
		this.nodalPhone = nodalPhone;
	}

	public Boolean getNodalStatus() {
		return nodalStatus;
	}

	public void setNodalStatus(Boolean nodalStatus) {
		this.nodalStatus = nodalStatus;
	}

	public ScholarshipForm getForm() {
		return form;
	}

	public void setForm(ScholarshipForm form) {
		this.form = form;
	}

	
	
}
