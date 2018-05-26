/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spi.uvschool.domain.VerificationToken;

/**
 * @version 1.0
 * @author:
 * 
 */
@Repository
public interface VerificationTokenDAO extends JpaRepository<VerificationToken, Long> {

	@Query("select t from VerificationToken t where uuid = ?")
	VerificationToken findByUuid(String uuid);

	@Query("select t from VerificationToken t where token = ?")
	VerificationToken findByToken(String token);
}
