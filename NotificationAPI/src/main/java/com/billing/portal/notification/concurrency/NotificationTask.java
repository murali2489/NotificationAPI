package com.billing.portal.notification.concurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.billing.portal.notification.contract.NotificationService;
import com.billing.portal.notification.entities.Notification;
import com.billing.portal.notification.entities.Profile;
import com.billing.portal.notification.entities.repos.NotifyRepo;
import com.billing.portal.notification.util.DbUtil;
import com.billing.portal.notification.util.MailUtil;

public class NotificationTask implements Runnable{
	
	private Profile profile;
	private NotifyRepo notify;
	 public NotificationTask() {
		// TODO Auto-generated constructor stub
	}
	
	 public NotificationTask(Profile profile) {
	
		this.profile=profile;
	}
	
	
	public NotificationTask(Profile profile2, NotifyRepo notify) {
		// TODO Auto-generated constructor stub
		this.profile=profile2;
		this.notify=notify;
		
	}

	@Override
	public void run() {
		System.out.println("Inside Run Method");
		
		try {
			MailUtil.sendEmail(this.profile);
			DbUtil.saveRecord(new Notification(profile.getEmailID()),this.notify);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
