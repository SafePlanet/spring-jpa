/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spi.uvschool.domain.User;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
public interface UserDAO extends JpaRepository<User, Long> {

	User findByEmailAddress(String emailAddress);

	@Query("select u from User u where uuid = ?")
	User findByUuid(String uuid);

	@Query("select u from User u where u in (select user from AuthorizationToken where lastUpdated < ?)")
	List<User> findByExpiredSession(Date lastUpdated);

	@Query("select u from User u where u = (select user from AuthorizationToken where token = ?)")
	User findBySession(String token);

	@Query("select u from User u order by u.firstName, u.lastName")
	List<User> findAllUser();

	@Query("select u from User u where u.role = 'administrator' order by u.firstName, u.lastName")
	List<User> findUsersForSuperAdmin();
	
	@Query("select distinct u from User u where u.role = 'superAdmin' ")
	User findSuperAdmin();
	
}
