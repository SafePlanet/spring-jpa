/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.dao;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import com.spi.uvschool.domain.SocialUser;
import com.spi.uvschool.domain.User;

@Repository
@Transactional
public interface SocialUserDAO extends JpaRepository<SocialUser, Long> {

	List<SocialUser> findAllByUser(User user);

	List<SocialUser> findByUserAndProviderId(User user, String providerId);

	List<SocialUser> findByProviderIdAndProviderUserId(String providerId, String providerUserId);

	// TODO will need a JPA Query here
	List<SocialUser> findByUserAndProviderUserId(User user, MultiValueMap<String, String> providerUserIds);

	@Query("Select providerId from SocialUser where providerId = :providerId")
	Set<String> findByProviderIdAndProviderUserId(@Param("providerId")String providerId);

	SocialUser findByUserAndProviderIdAndProviderUserId(User user, String providerId, String providerUserId);

}
