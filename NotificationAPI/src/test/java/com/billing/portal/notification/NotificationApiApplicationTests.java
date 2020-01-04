package com.billing.portal.notification;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.catalina.connector.InputBuffer;

import com.billing.portal.notification.datastructure.AVLTreeRecursive;
import com.billing.portal.notification.entities.Notification;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

class NotificationApiApplicationTests {

	 
	static Session session;
	
	public static void main(String[] args) throws Exception {


		String PROJECT_ID = "hale-photon-262018";
		String PATH_TO_JSON_KEY = "C://jsonkey/gcp.json";
		String BUCKET_NAME = "saimuga";
		String OBJECT_NAME = "maildata5.csv";

		StorageOptions options = StorageOptions.newBuilder()
		            .setProjectId(PROJECT_ID)
		            .setCredentials(GoogleCredentials.fromStream(
		                    new FileInputStream(PATH_TO_JSON_KEY))).build();

		Storage storage = options.getService();
		Blob blob = storage.get(BUCKET_NAME, OBJECT_NAME);
		ReadChannel r = blob.reader();
		
		BufferedReader br = new BufferedReader(Channels.newReader(r, "UTF-8"));
		ArrayList accountList = new ArrayList();
        StringBuilder s = new StringBuilder();
		AVLTreeRecursive av = new AVLTreeRecursive();
		int i=0;
		List<Notification> notifyRecords = new ArrayList<>();
		while(br.read()!=-1) {
		//	accountList.in
		    List<String> split= Arrays.asList(br.readLine().split("\\s*,\\s*"));
		                                      //str.split(",[ ]*")
			notifyRecords.add(new Notification(split.get(0),split.get(1)));	
			//av.insert(br.readLine());
		}
		
		
		System.out.println("Total no of records ::: "+notifyRecords.size());
	}

}
