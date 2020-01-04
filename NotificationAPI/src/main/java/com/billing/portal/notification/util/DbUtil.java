package com.billing.portal.notification.util;

import java.sql.Date;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.billing.portal.notification.entities.Notification;
import com.billing.portal.notification.entities.repos.NotifyRepo;

public class DbUtil {
	
	
	public static void saveRecord(Notification record, NotifyRepo notify2) {
	
		System.out.println("Notify Record");
		System.out.println(record.toString());
		long ms = System.currentTimeMillis();
		Date date = new Date(ms);
		record.setCreationDate(date);
		record.setStatus(true);
		System.out.println(record.toString());
		new DbUtil().saveRecordtoDB(record,notify2);
		
	}
	
	public void saveRecordtoDB(Notification record,NotifyRepo notify2) {
		if(notify2==null) {
			System.out.println("!!!!!!!!!!!!!!! Notify Object is null !!!!!!!!!!!!!!!!!");
			
		}else {
			notify2.save(record);
		}
	}
	
}
