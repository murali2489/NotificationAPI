package com.billing.portal.notification.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Notification")
@Table(name = "Notification")
public class Notification {

	public Notification() {

	}

	public Notification(String emailId) {
		this.emailID = emailId;
	}

	public Notification(String accountNumber, String emailId) {
		this.emailID = emailId;
		this.accountNumber = accountNumber;
		this.status = false;
		long ms = System.currentTimeMillis();
		Date date = new Date(ms);
		this.setCreationDate(date);
		date = null;
	}

	@Id
	@SequenceGenerator(name = "notification_notificationid_seq", sequenceName = "notification_notificationid_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_notificationid_seq")
	private int notificationID;
	private String emailID;
	private boolean status;
	private Date creationDate;
	private String accountNumber;

	public int getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String toString() {

		return this.accountNumber + ":::" + this.emailID + ":::" + this.notificationID + ":::" + this.status + ":::"
				+ this.creationDate;

	}

}
