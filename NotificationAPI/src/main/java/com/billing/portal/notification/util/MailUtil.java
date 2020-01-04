package com.billing.portal.notification.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.billing.portal.notification.entities.Profile;

public class MailUtil {

	final static Logger logger = Logger.getLogger(MailUtil.class);
	
	static Session session;
	static Message message = null;
	static Transport transport=null;
	static {
		try {
			
			logger.info("*************** Inside static block ******************");
			String host = "smtp.gmail.com";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			logger.info("cxf2");

			final String username = "developer2490mailtest@gmail.com";// change accordingly
			final String password = "Gaga#2k19";// change accordingly

	        session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
	        message = new MimeMessage(session);
	        transport = session.getTransport();
	        transport.connect();

		} catch (Exception e) {

			logger.info("Exception in Static Block");
			e.printStackTrace();
		}
	}


	
    public static void main(String[] args) throws Exception {
		
		logger.info("Email ID");
		logger.info("saisaimuga@mailinator.com");
	    String to = "saisaimuga@mailinator.com";

	      // Sender's email ID needs to be mentioned
	      String from = "developer2489mailtest@gmail.com";
	      final String username = "dveloper2489mailtest@gmail.com";//change accordingly
	      final String password = "Gaga#2k19";//change accordingly
	      logger.info("cxf1");
	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	      logger.info("cxf2");
	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });
	      logger.info("cxf3");
	      try {
		   // Create a default MimeMessage object.
		   
		   logger.info("cxf4");
		   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));
		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		
		   // Set Subject: header field
		   message.setSubject("Profile Notification Test");
		
		   // Now set the actual message
		   message.setText("Hello, this is sample for to check send " +
			"email using JavaMailAPI ");

		   // Send message
		   logger.info("cxf5");
		   Transport.send(message);
		   logger.info("cxf6");

		   logger.info("Sent message successfully....");

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	      catch (Exception e) {
	    	  throw new Exception (e);
	      }
	   }
		

	
	public static void sendEmail(Profile profile) throws Exception {
		logger.info("Email ID");
		logger.info(profile.getEmailID());
	    String to = profile.getEmailID();

	      // Sender's email ID needs to be mentioned
	      String from = "developer2489mailtest@gmail.com";
	      logger.info("cxf1");
	      // Assuming you are sending email through relay.jangosmtp.net
	      logger.info("cxf2");
	      // Get the Session object.
	      logger.info("cxf3");
	      try {
		   // Create a default MimeMessage object.
		   logger.info("cxf4");
		   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));
		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		
		   // Set Subject: header field
		   message.setSubject("Profile Notification Test");
		
		   // Now set the actual message
		   message.setText("Hello, this is sample for to check send " +
			"email using JavaMailAPI ");

		   // Send message
		   logger.info("cxf5");
		   transport.send(message);
		   logger.info("cxf6");

		   logger.info("Sent message successfully....");

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	      catch (Exception e) {
	    	  
	    	  throw new Exception (e);
	      }
	   }
		
		
	}

