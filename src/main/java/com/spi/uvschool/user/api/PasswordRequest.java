/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.user.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
@XmlRootElement
public class PasswordRequest {

	@Length(min = 8, max = 30, message="{password.length}")
	private String password;

	public PasswordRequest() {
	}

	public PasswordRequest(final String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
