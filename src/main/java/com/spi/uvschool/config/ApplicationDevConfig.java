/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 * @version 1.0
 * @author:
 * 
 */
@Configuration
@Profile(value = { "dev", "local" })
@PropertySource({ "classpath:/properties/dev-app.properties" })
public class ApplicationDevConfig {

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

}
