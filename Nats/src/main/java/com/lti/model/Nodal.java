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
	int Nodal_UID;
	
	@Column(name="Nodal_State")
	String Nodal_State;
	
	@Column(name="Nodal_Name")
	String Nodal_Name;
	
	@Column(name="Nodal_Email")
	String Nodal_Email;
	
	@Column(name="Nodal_Phone")
	int Nodal_Phone;
	
	@Column(name="Nodal_Status")
	Boolean Nodal_Status;
	
	@OneToOne( mappedBy = "nodal")
	ScholarshipForm form;

	public int getNodal_UID() {
		return Nodal_UID;
	}

	public void setNodal_UID(int nodal_UID) {
		Nodal_UID = nodal_UID;
	}

	public String getNodal_State() {
		return Nodal_State;
	}

	public void setNodal_State(String nodal_State) {
		Nodal_State = nodal_State;
	}


	public String getNodal_Name() {
		return Nodal_Name;
	}

	public void setNodal_Name(String nodal_Name) {
		Nodal_Name = nodal_Name;
	}

	public String getNodal_Email() {
		return Nodal_Email;
	}

	public void setNodal_Email(String nodal_Email) {
		Nodal_Email = nodal_Email;
	}

	public int getNodal_Phone() {
		return Nodal_Phone;
	}

	public void setNodal_Phone(int nodal_Phone) {
		Nodal_Phone = nodal_Phone;
	}

	public Boolean getNodal_Status() {
		return Nodal_Status;
	}

	public void setNodal_Status(Boolean nodal_Status) {
		Nodal_Status = nodal_Status;
	}
	
}
