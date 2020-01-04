package com.billing.portal.notification.implementation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.Channels;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.billing.portal.notification.concurrency.NotificationTask;
import com.billing.portal.notification.contract.NotificationService;
//import com.billing.portal.notification.datastructure.AVLTreeRecursive;
import com.billing.portal.notification.entities.Notification;
import com.billing.portal.notification.entities.Profile;
import com.billing.portal.notification.entities.repos.NotifyRepo;
import com.billing.portal.notification.entities.repos.ProfileRepo;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
@ComponentScan({"com.billing.portal.notification"})
public class NotificationServiceImple implements NotificationService {

	@Autowired
	NotifyRepo notify;

	@Autowired
	ProfileRepo profile;

	@Override
	public List<Notification> NotifyAPI() {
		// TODO Auto-generated method stub
		List<Notification> findAll = null;
		System.out.println(findAll);
		return findAll;
	}

	@Override
	public Response Notify(Notification notifyObject) {
		long ms = System.currentTimeMillis();
		Date date = new Date(ms);
		notifyObject.setCreationDate(date);
		System.out.println(notifyObject.toString());
		notify.save(notifyObject);
		return Response.ok("200").build();
	}

	@Override
	public Response invokeBatch() {
		

		String PROJECT_ID = "hale-photon-262018";
		String PATH_TO_JSON_KEY = "C://jsonkey/gcp.json";
		String BUCKET_NAME = "saimuga";
		String OBJECT_NAME = "maildata8.csv";

		StorageOptions options;
		try {
			options = StorageOptions.newBuilder()
			            .setProjectId(PROJECT_ID)
			            .setCredentials(GoogleCredentials.fromStream(
			                    new FileInputStream(PATH_TO_JSON_KEY))).build();

		Storage storage = options.getService();
		Blob blob = storage.get(BUCKET_NAME, OBJECT_NAME);
		ReadChannel r = blob.reader();
		
		BufferedReader br = new BufferedReader(Channels.newReader(r, "UTF-8"));
		ArrayList accountList = new ArrayList();
        StringBuilder s = new StringBuilder();
		//AVLTreeRecursive av = new AVLTreeRecursive();
		int i=0;
		List<Notification> notifyRecords = new ArrayList<>();
		while(br.read()!=-1) {
		//	accountList.in
		    List<String> split= Arrays.asList(br.readLine().split("\\s*,\\s*"));
		                                      //str.split(",[ ]*")
			notifyRecords.add(new Notification(split.get(0),split.get(1)));	
			//av.insert(br.readLine());
		}
		List<Notification> notifyRecords1 = new ArrayList<>();	
		int total=notifyRecords.size();
		System.out.println("Total records are  "+total);
		int limit=5000;
		int start=0;
		int rem=0;
		int j=0;
		int k=0;
		boolean check=true;
		if(total>5000) {
			rem = total-limit;
			while(check) {
				    System.out.println("Start ::: "+start
				    		+" limit ::: "+limit
				    		+" rem ::: "+rem
				    		 );
					notifyRecords1 = notifyRecords.subList(start,limit);
					System.out.println("********************* Batch Insert Started from "+start+" to "+limit +"************************");
					notify.saveAll(notifyRecords1);
					System.out.println("Batch Insert Completed");
					if(limit==total) {
						break;
					}
					start=limit;
					rem=rem-5000;
					limit+=5000;	
					if(rem<=0) {
                    	limit=total;
                    }
                    
				}
			}else {
                
				System.out.println("Batch insert started, total less than "+limit);
				notify.saveAll(notifyRecords);
			}


		System.out.println("Total no of records in the original file::: "+notifyRecords.size());
		System.out.println("Total no of records in the temp file::: "+notifyRecords1.size());
		notify.saveAll((notifyRecords1));
		return Response.accepted(200).build();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Response.accepted(200).build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.accepted(200).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.accepted(200).build();
			
		}
		
		
		/*
		 * ExecutorService service = Executors.newFixedThreadPool(8); try {
		 * List<Profile> profiles = profile.findAll();
		 * System.out.println("Email Size "+profiles.size()); for (int i = 0; i <
		 * profiles.size(); i++) { service.submit(new
		 * NotificationTask(profiles.get(i),notify)); } service.shutdown(); return
		 * Response.ok(200).build(); } catch (Exception e) { e.printStackTrace();
		 * service.shutdown(); return Response.serverError().build(); } finally {
		 * service.shutdown();
		 * 
		 * }
		 */
	}

}
