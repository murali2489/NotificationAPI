package com.billing.portal.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NotificationApiApplication {
	final static Logger logger = LoggerFactory.getLogger(NotificationApiApplication.class);
			
	public static void main(String[] args) {
		System.out.println("API Started");
		logger.info("API Started");
		logger.debug("Api Started");
		SpringApplication.run(NotificationApiApplication.class, args);
	}

}
