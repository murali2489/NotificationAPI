package com.billing.portal.notification.entities.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billing.portal.notification.entities.Profile;

public interface ProfileRepo extends JpaRepository <Profile, Integer> {

}
