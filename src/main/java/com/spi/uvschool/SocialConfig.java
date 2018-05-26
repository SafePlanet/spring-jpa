/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.spi.uvschool.config.ApplicationConfig;
import com.spi.uvschool.dao.SocialUserDAO;
import com.spi.uvschool.dao.UserDAO;
import com.spi.uvschool.user.social.JpaUsersConnectionRepository;

/**
 */
@Configuration
@ComponentScan
public class SocialConfig {

	@Autowired
	ApplicationConfig config;

	@Autowired
	SocialUserDAO socialUserRepository;

	@Autowired
	UserDAO userRepository;

	@Autowired
	TextEncryptor textEncryptor;

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(config
				.getFacebookClientId(), config.getFacebookClientSecret()));
		return registry;
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		JpaUsersConnectionRepository usersConnectionRepository = new JpaUsersConnectionRepository(
				socialUserRepository, userRepository,
				connectionFactoryLocator(), textEncryptor);

		return usersConnectionRepository;
	}
}