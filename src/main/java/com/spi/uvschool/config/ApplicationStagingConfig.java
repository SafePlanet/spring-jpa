/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
@Configuration
@Profile(value = "staging")
@PropertySource({ "classpath:/properties/staging-app.properties" })
public class ApplicationStagingConfig {

	@Autowired
	Environment environment;

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.queryableText(
				environment.getProperty("security.encryptPassword"),
				environment.getProperty("security.encryptSalt"));
	}
}
