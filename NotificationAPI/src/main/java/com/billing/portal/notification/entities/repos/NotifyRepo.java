package com.billing.portal.notification.entities.repos;


import org.springframework.data.repository.CrudRepository;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.billing.portal.notification.entities.Notification;

public interface NotifyRepo extends CrudRepository<Notification, Integer> {

}
