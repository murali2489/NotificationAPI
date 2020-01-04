package com.billing.portal.notification.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileID;
	private String emailID;

	
	
	/**
	 * @return the profileID
	 */
	public int getProfileID() {
		return profileID;
	}
	/**
	 * @param profileID the profileID to set
	 */
	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}
	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	

}
