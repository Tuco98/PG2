package com.lti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_ministry")
public class Ministry {
	
	@Id
	@GeneratedValue
	@Column(name="MinistryUID")
	int MinistryUID;
	
	@Column(name="MinistryPassword")
	String MinistryPassword;
	
	@Column(name="MinistryState")
	String MinistryState;
	
	@Column(name="MinistryStatus")
	Boolean MinistryStatus = true;
	
	
	public int getMinistry_UID() {
		return MinistryUID;
	}
	public void setMinistry_UID(int ministry_UID) {
		MinistryUID = ministry_UID;
	}
	public String getMinistry_Password() {
		return MinistryPassword;
	}
	public void setMinistry_Password(String ministry_Password) {
		MinistryPassword = ministry_Password;
	}
	public String getMinistry_state() {
		return MinistryState;
	}
	public void setMinistry_state(String ministry_state) {
		MinistryState = ministry_state;
	}
	public Boolean getMinistry_Status() {
		return MinistryStatus;
	}
	public void setMinistry_Status(Boolean ministry_Status) {
		MinistryStatus = ministry_Status;
	}
}
